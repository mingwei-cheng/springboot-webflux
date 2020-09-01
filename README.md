# 基于反应式编程实现的数据获取

### common模块
存放了基础的复用类，如pojo、service接口、controller

同时以缓存的方式，实现了简单的CRUD

---
### r2dbc模块
使用r2dbc方式实现了响应式的数据获取
 
 - controller包，继承了common模块中的controller，实现注入
 - service.impl包，继承common模块中的service接口，实现对controller的通用返回
 - config包，配置了响应式jdbc的配置，r2dbc
 
Spring data目前无法提供响应式的jpa，原因：
1. SpringDataJpa是基于JDBC实现对数据库的操作，JDBC中提供的方法是阻塞的。
2. Mysql等关系型数据库是BIO模型，客户端的Connection对于数据库来说，是一个线程。

对于以上问题，首先需要能够以NIO的方式调用数据库（如R2DBC），其次需要将数据库的模型由BIO改为NIO（如国产数据库TiDB）。

当然我们也可以使用中间件进行代理方式访问。比如Sharding Sphere提供了Sharding Proxy，可以基于NIO的方式实现。

 
---
### redis模块
 使用redis方式实现了响应式的数据获取

 - controller包，继承了common模块中的controller，实现注入
 - service.impl包，实现common模块中的service接口，实现对controller的通用返回
 - config包，关于redisTemplate的相关配置
