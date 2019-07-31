package cn.cescforz.commons.lang.toolkit.tool;


import cn.cescforz.commons.lang.enums.ResponseEnum;
import cn.cescforz.commons.lang.exception.CustomRtException;

/*

    ┏┳━━━━━━━━━┓
    ┃┃█████████┃
    ┣┫████┏━━┓█┃
    ┃┃████┃ 辟 ┃█┃
    ┣┫████┃ 谱 ┃█┃
    ┃┃████┃ 剑 ┃█┃
    ┣┫████┃ 谱 ┃█┃
    ┃┃████┗━━┛█┃
    ┣┫█████████┃
    ┃┃█████████┃
    ┗┻━━━━━━━━━┛

 */



/**
 * <p>Description: 分布式系统唯一ID(纯数字)生成器</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-07-23 19:04
 */
public class KeyGenerator {

    /*
        snowflake 算法
        snowflake 是 Twitter 开源的分布式 ID 生成算法，结果是一个 long 型的 ID。其核心思想是：使用 41bit 作为毫秒数，10bit 作为机器的 ID
        （5 个 bit 是数据中心，5 个 bit 的机器 ID），12bit 作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是 0。

        消息可能会重复，所以消费端需要做幂等

        为了达到业务的幂等，必须要有这样一个 id 存在，需要满足下面几个条件：

        同一业务场景要全局唯一。
        该 id 必须是在消息的发送方进行产生发送到 MQ。
        消费端根据该 id 进行判断是否重复，确保幂等。
        在那里产生，和消费端进行判断等和这个 id 没有关系，这个 id 的要求就是局部唯一或者全局唯一即可，
        由于这个 id 是唯一的，可以用来当数据库的主键，

        那么该 id 需要有 2 个特性：

        1.局部、全局唯一。
        2.趋势递增。

     */

    /** 开始时间截 (2019-01-01) */
    private static final Long TWEPOCH = 1546272000000L;

    /** 机器id所占的位数 */
    private static final Long WORK_ERID_BITS = 5L;

    /** 数据标识id所占的位数 */
    private static final Long DATACENTER_ID_BITS = 5L;

    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private static final Long MAX_WORKER_ID = ~(-1L << WORK_ERID_BITS);

    /** 支持的最大数据标识id，结果是31 */
    private static final Long MAX_DATA_CENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    /** 序列在id中占的位数 */
    private static final Long SEQUENCE_BITS = 12L;

    /** 机器ID向左移12位 */
    private static final Long WORKER_ID_SHIFT = SEQUENCE_BITS;

    /** 数据标识id向左移17位(12+5) */
    private static final Long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORK_ERID_BITS;

    /** 时间截向左移22位(5+5+12) */
    private static final Long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORK_ERID_BITS + DATACENTER_ID_BITS;

    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private static final Long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);

    /** 工作机器ID(0~31) */
    private Long workerId;

    /** 数据中心ID(0~31) */
    private Long datacenterId;

    /** 毫秒内序列(0~4095) */
    private Long sequence = 0L;

    /** 上次生成ID的时间截 */
    private Long lastTimestamp = -1L;


    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public KeyGenerator(long workerId, long datacenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATA_CENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized Long nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new CustomRtException(ResponseEnum.SERVICE_EXCEPTION,
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - TWEPOCH) << TIMESTAMP_LEFT_SHIFT)
                | (datacenterId << DATACENTER_ID_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }


    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private Long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private Long timeGen() {
        return System.currentTimeMillis();
    }


}
