# mini-dubbo
实现一个迷你dubbo
1. 项目中的Request与Response 类似于Dubbo中的invoker 封装了接口名方法响应结果等并实现了序列化接口；
2. 使用TCP连接，服务端调用Netty暴露服务并监听处理,使用netty编解码；
3. 消费端通过Netty发送Request，服务端通过JDK动态代理执行后返回结果Response；
