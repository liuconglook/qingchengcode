# qingchengcode
## 青橙电商系统项目笔记

一起学项目！

链接：https://pan.baidu.com/s/1rqnWIxHWftSLYWiKJY4K4Q 
提取码：`jjig`

### 一、走进电商

#### 1、技术特点

- 技术新、技术广。
- 分布式。
- 高并发、集群、负载均衡、高可用。
- 海量数据、业务复杂。
- 系统安全。

#### 2、电商模式

- `B2B`（Business to Business），商家与商家。
  - 比如：阿里巴巴，提供技术给其他公司。
- `C2C`（Customer to Customer），消费者个人间的电子商务行为。
  - 比如：淘宝，有很多店铺。
- `B2C`（Business to Customer），商对客，面向消费者销售产品。
  - 比如：青橙电商系统，网上购物。
- `B2B2C`，第一个B指的是商品或服务的供应商，第二个B指的是从事电子商务的企业，C则是消费者。
  - 比如：京东商城、天猫商城。
- `C2B`（Consumer to Business）即消费者到企业，新的商业模式。
  - 是一种消费者贡献价值（Create Value），企业和机构消费价值（Consume Value）。
  - 与之相反的供需模式（Demand Supply Model，`DSM`）。
    - 消费者根据自身需求定制产品和价格，主动参与产品设计、生产和定价等彰显消费者的个性需求。
    - 生产企业进行定制化生产。
    - 比如：海尔商城。
- `O2O`（`Online to Offline`）在线离线/线上线下。
  - 是指将线下的商务机会与互联网结合，让互联网成为线下交易的平台，这个概念早来源于美国。
  - 比如：美团、饿了吗。
- `F2C`（Factory to Customer），即从厂商到消费者的电子商务模式。

### 二、系统架构

#### 1、架构图

- `common`（父公共模块）
  - `common_service`（服务公共模块）
    - `service_goods`（商品服务模块）
    - `service_order`（订单服务模块）
    - `service_xxx`（...服务模块）
  - `common_web`（web公共模块）
    - `web_manager`（管理后台模块）
    - `common_cas`（`CAS`公共模块）
      - `web_portal`（前台主流程模块）
      - `web_center`（前台用户中心模块）

#### 2、技术栈

- spring
  - spring-context
  - spring-beans
  - spring-web
  - `spring-webmvc`
  - `spring-jdbc`
  - spring-aspects # 切面编程
  - `spring-jms` # Java 消息服务
  - spring-context-support # Spring context的扩展支持，用于`MVC`方面。
- security  # 安全框架，权限管理
  - spring-security-web
  - `spring-security-config`
  - `spring-security-taglibs`
  - `spring-security-cas`
- `Dubbo`
  - `dubbo` # 微服务框架
  - `zookeeper` # 服务注册中心
  - `zkclient` # `zookeeper`客户端
- test
  - spring-test
  - `junit`
- `JSON`
  - `jackson-core`
  - `jackson-databind`
  - `jackson-annotations`
  - `fastjson`
- XML
  - `dom4j`
  - `xml-apis` # Java的XML解析
- `javassist` # 低开销操作字节码的类库
- `kaptcha` # Google开源的生成验证码类库
- `commons-codec` # Apache开源的编解码包，如`MD5`
- `commons-fileupload` # Apache开源的文件上传组件
- `MyBatis`
  - `mybatis`
  - `mybatis-spring`
  - `mybatis-paginator` # 一个`GitHub`开源项目，用于Java后台获取分页数据、列表组件`mmgrid`前端展示。
  - `pagehelper` # 一个`GitHub`开源项目，用于数据库物理分页。
  - mapper # 通用mapper
  - `persistence-api` # 对象与表的映射（`ORM`）

- `MySQL`
  - `mysql-connector-java`
  - druid # Java中最好的连接池

- `Redis`
  - `jedis`
  - `spring-data-redis`
- `httpclient` # Apache开源，调HTTP接口的类库
- `aliyun-sdk-oss` # 支持阿里云对象存储的类库
- `elasticsearch` # 分布式的开源搜索和分析引擎
  - `elasticsearch-rest-high-level-client` # Java高级别REST客户端
- `RabbitMQ` # `AMQR`高级消息队列协议的实现
  - spring-rabbit

### 总结回顾

#### 1、细节问题

>maven打包jar

视频上使用：`Plugins`->`install:install`（会报错）

使用：`Lifecycle`->`install`

> maven运行服务

`Plugins`->`tomcat7:run`

> 配置文件放错地方

报错：找不到，未注入。

~~~
Unsatisfied dependency expressed through field 'brandMapper'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.qingcheng.dao.BrandMapper' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
~~~

我把`applicationContext-dao.xml`放到了web公共模块。

原本应该放在service公共模块的。

所以，导致缺少配置文件，扫描不到`brandMapper`。

>install没用

将`applicationContext-dao.xml`放回service公共模块后执行`parent`的`install:install`。

成功运行后，访问却报错：显示web公共模块下的`applicationContext-dao.xml`...，还是之前的错。

说明install无效，并没有覆盖之前的包。

所以再次install之前，最好先clean下。

#### 2、IDEA快捷键

>实现接口方法

选中实现类，Alt+Enter。

> 生成代码（getter、setter等）

Alt+Insert

> 生成返回变量

光标在分号后，Alt+Enter。

>代码行移动

Shift+Ctrl+上下键

> 搜索

按两下Shift

#### 3、通用Mapper

- 命名规律
  - insert、delete、update、select。
  - Selective # 忽略null
    - 例如：`insertSelective`，null值忽略，填充默认值，而不是覆盖。
    - 例如：`updateSelective`，null值忽略，还是原来的值，而不是覆盖。
  - Example # 条件Where
    - `Example example = new Example(Brand.class);`
    - `Example.Criteria criteria = example.createCriteria();`









21









































