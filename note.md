## 单聊

- A和B客户端登录 服务器保存A和B的连接
- A发消息到服务器 服务器识别到发给B 找到B的连接 转发给B

## 群聊

- A B C 登录 服务器保存多人连接
- A 发起和B C群聊 服务器生成聊天室 A B C
- A 发送群聊消息 服务器识别聊天室id 转发给聊天室每一个人

## BIO实现的问题

- 线程资源受限
- 线程切换效率低
- BIO面向字节

## NIO

- 通常会有两个线程 两个selector 一个线程监听连接并把新连接注册到selector上 一个线程轮询连接
- 数据读写面向Buffer

## 为什么使用NIO

- NIO 概念复杂 编程不友好
- 简单的自定义协议拆包都要自己实现
- nio bug
- Netty封装了NIO 使用方便

Netty 是一个异步事件驱动的网络应用框架，用于快速开发可维护的高性能服务器和客户端。


## NIO - Netty



## Netty核心概念
- NioEventLoopGroup 可以看做线程池
- ServerBootstrap 引导类
- boss 对应于 NIO 的接收连接线程
- worker 对应于负责读取数据和业务处理的线程

### 引导类

SeverBootstrap.option()给服务端channel设置一些属性
- serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024)
表示系统用于临时存放已完成三次握手的请求的队列的最大长度
https://www.jianshu.com/p/e6f2036621f4


SeverBootstrap.childOption()
可以给每条连接设置一些TCP底层相关属性
- ChannelOption.SO_KEEPALIVE 表示是否开启TCP底层心跳机制，true为开启
- ChannelOption.TCP_NODELAY 是否开启 nagle 算法 
    - 如果要求高实时性 打开
    - 如果想要减少网络发送次数 就关闭
    
## Bytebuf
```java
// 保存读指针
buffer.markReaderIndex();
// 恢复读指针
buffer.resetReaderIndex();
```

分配内存的时候可以调用分配堆内存的方法，ByteBufAllocator.heapBuffer()

也可以申请堆外内存，手工释放。



    


