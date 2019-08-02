package cn.cescforz.commons.lang.bean.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
  ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐
  │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐
  └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘
  ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐
  │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │
  ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤
  │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │
  ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │
  │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  │               │ 4 │ 5 │ 6 │   │
  ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤
  │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │
  ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││
  │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│
  └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘

                                 Are you keyboard man ?
 */


/**
 * <p>©2019 Cesc. All Rights Reserved.</p>
 * <p>Description: 分页实体</p>
 *
 * @author cesc
 * @version v1.0
 * @date Create in 2019-07-24 15:51
 */
@Data
@NoArgsConstructor
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 7179869349259717991L;

    /** 记录列表 */
    private List<T> pageData;
    /** 总页数 */
    private Integer pageCount;
    /** 总记录数目 */
    private Long recordCount;
    /** 第几页 */
    private Integer pageIndex;
    /** 分页大小 */
    private Integer pageSize;


    public Page(List<T> page, Integer pageCount, Long recordCount, Integer pageIndex, Integer pageSize) {
        this.makePageList(page, pageCount, recordCount, pageIndex, pageSize);
    }

    @SuppressWarnings("unchecked")
    public Page(List<T> page, Long recordCount, Integer pageIndex, Integer pageSize) {
        if (recordCount == 0) {
            this.makePageList(new ArrayList(), 0, 0, 0, pageSize);
        } else {
            if (pageSize <= 0) {
                pageSize = 10;
            }
            int totalCount = Math.toIntExact(recordCount / pageSize);
            if (recordCount % pageSize != 0) {
                totalCount++;
            }
            pageIndex = pageIndex < 1 ? 1 : pageIndex;
            pageIndex = pageIndex > totalCount ? totalCount : pageIndex;
            this.makePageList(page, totalCount, recordCount, pageIndex, pageSize);
        }
    }


    /**
     * <p>Description: 根据分页索引、分页大小自动将完整列表分割成分页列表</p>
     * @param list 完整列表
     * @param pageIndex 分页索引
     * @param pageSize 分页大小
     */
    @SuppressWarnings("unchecked")
    public Page(List<T> list, Integer pageIndex, Integer pageSize) {
        // 获取总条数
        int totalSize = list.size();
        if (recordCount == 0) {
            this.makePageList(new ArrayList(), 0, 0, 0, pageSize);
        } else {
            // 非法pageSize 默认取10条
            if (pageSize <= 0) {
                pageSize = 10;
            }
            // 获取总页数
            int totalCount = totalSize / pageSize;
            if (totalSize % pageSize != 0) {
                totalCount++;
            }
            // 判断起始页是否合法
            pageIndex = pageIndex < 1 ? 1 : pageIndex;
            pageIndex = pageIndex > totalCount ? totalCount : pageIndex;
            int skip = pageSize * (pageIndex - 1);
            // - 1
            int fromIndex = (skip <= 0) ? 0 : skip;
            int count = ((totalSize - skip) / pageSize == 0) ? (totalSize - skip) : pageSize;
            List realList = list.subList(fromIndex, fromIndex + count);
            this.makePageList(realList, totalCount, totalSize, pageIndex, pageSize);
        }
    }

    @SuppressWarnings("unchecked")
    public void makePageList(List<T> pageData, int pageCount, long recordCount, int pageIndex, int pageSize) {
        if (pageData == null) {
            pageData = new ArrayList();
        }
        this.pageData = pageData;
        this.pageCount = pageCount;
        this.recordCount = recordCount;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }


    public int getPrevPageIndex() {
        int idx = pageIndex - 1;
        if (idx < 0) {
            idx = 0;
        }
        return idx;
    }

    public int getNextPageIndex() {
        int idx = pageIndex + 1;
        if (idx >= pageCount) {
            idx = pageCount - 1;
        }
        if (idx < 0) {
            idx = 0;
        }
        return idx;
    }

    private boolean hasNext() {
        return pageIndex + 1 < pageCount;
    }

    private boolean hasPrevious() {
        return pageIndex > 0;
    }

    public boolean hasPage() {
        return CollectionUtils.isNotEmpty(pageData);
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean isFirst() {
        return !hasPrevious();
    }
}
