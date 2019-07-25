
#### 部署snapshot快照库
mvn clean deploy -Dmaven.test.skip -Dmaven.javadoc.skip=true -e

#### 部署release发布库（跳过javadoc和test)
1. 把version标签里的SNAPSHOT改为RELEASE或者不要SNAPSHOT
2. 执行以下命令
mvn clean deploy -P release -Dmaven.test.skip -Dmaven.javadoc.skip=true -e
3. 查看settings.xml读取的是哪个
mvn -X
4. 运行命令时，手动指定 setting.xml
mvn clean deploy -P release -Dmaven.test.skip -Dmaven.javadoc.skip=true -e -s D:\config\maven\sonatype\settings.xml

mvn clean deploy -P release -Dmaven.test.skip -Dmaven.javadoc.skip=true -e -s /soft/data/config/maven/sonatype/settings.xml

> 在使用 maven 过程中，我们在开发阶段经常性的会有很多公共库处于不稳定状态，随时需要修改并发布，
  可能一天就要发布一次，遇到 bug 时，甚至一天要发布 N 次。我们知道，maven 的依赖管理是基于版本管理的，
  对于发布状态的 artifact，如果版本号相同，即使我们内部的镜像服务器上的组件比本地新，maven 也不会主动下载的。
  如果我们在开发阶段都是基于正式发布版本来做依赖管理，那么遇到这个问题，就需要升级组件的版本号，可这样就明显不符合要求和实际情况了。
  但是，如果是基于快照版本，那么问题就自热而然的解决了，而 maven 已经为我们准备好了这一切。

> maven 中的仓库分为两种，snapshot 快照仓库和 release 发布仓库。snapshot 快照仓库用于保存开发过程中的不稳定版本，release 正式仓库
  则是用来保存稳定的发行版本。定义一个组件 / 模块为快照版本，只需要在 pom 文件中在该模块的版本号后加上 -snapshot 即可
  
> maven2 会根据模块的版本号 (pom 文件中的 version) 中是否带有 -snapshot 来判断是快照版本还是正式版本。
  如果是快照版本，那么在 mvn deploy 时会自动发布到快照版本库中，而使用快照版本的模块，在不更改版本号的情况下，直接编译打包时，
  maven 会自动从镜像服务器上下载最新的快照版本。如果是正式发布版本，那么在 mvn deploy 时会自动发布到正式版本库中，而使用正式版本的模块，
  在不更改版本号的情况下，编译打包时如果本地已经存在该版本的模块则不会主动去镜像服务器上下载。所以，我们在开发阶段，可以将公用库的版本设置为快照版本，
  而被依赖组件则引用快照版本进行开发，在公用库的快照版本更新后，我们也不需要修改 pom 文件提示版本号来下载新的版本，直接 mvn 执行相关编译、
  打包命令即可重新下载最新的快照库了，从而也方便了我们进行开发。  

-h,--help                              Display help information

-am,--also-make                        构建指定模块，同时构建指定模块依赖的其他模块；

-amd,--also-make-dependents            构建指定模块，同时构建依赖于指定模块的其他模块；

-B,--batch-mode                        以批处理 (batch) 模式运行；

-C,--strict-checksums                  检查不通过，则构建失败；(严格检查)

-c,--lax-checksums                     检查不通过，则警告；(宽松检查)

-D,--define <arg>                      Define a system property

-e,--errors                            显示详细错误信息

-emp,--encrypt-master-password <arg>   Encrypt master security password

-ep,--encrypt-password <arg>           Encrypt server password

-f,--file <arg>                        使用指定的 POM 文件替换当前 POM 文件

-fae,--fail-at-end                     最后失败模式：Maven 会在构建最后失败（停止）。如果 Maven refactor 中一个失败了，Maven 会继续构建其它项目，并在构建最后报告失败。

-ff,--fail-fast                        最快失败模式： 多模块构建时，遇到第一个失败的构建时停止。

-fn,--fail-never                       从不失败模式：Maven 从来不会为一个失败停止，也不会报告失败。

-gs,--global-settings <arg>            替换全局级别 settings.xml 文件 (Alternate path for the global settings file)

-l,--log-file <arg>                    指定输出日志文件

-N,--non-recursive                     仅构建当前模块，而不构建子模块 (即关闭 Reactor 功能)。

-nsu,--no-snapshot-updates             强制不更新 SNAPSHOT (Suppress SNAPSHOT updates)

-U,--update-snapshots                  强制更新 releases、snapshots 类型的插件或依赖库 (否则 maven 一天只会更新一次 snapshot 依赖)

-o,--offline                           运行 offline 模式，不联网进行依赖更新

-P,--activate-profiles <arg>           激活指定的 profile 文件列表 (用逗号 [,] 隔开)

-pl,--projects <arg>                   手动选择需要构建的项目，项目间以逗号分隔；A project can be specified by [groupId]:artifactId or by its relative path.

-q,--quiet                             安静模式，只输出 ERROR

-rf,--resume-from <arg>                从指定的项目 (或模块) 开始继续构建

-s,--settings <arg>                    替换用户级别 settings.xml 文件 (Alternate path for the user settings file)

-T,--threads <arg>                     Thread count, for instance 2.0C where C is core multiplied

-t,--toolchains <arg>                  Alternate path for the user toolchains file

-V,--show-version                      Display version information WITHOUT stopping build

-v,--version                           Display version information

-X,--debug                             输出详细信息，debug 模式。
