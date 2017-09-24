## kafka java demo

#### kafka server 环境
本地为了开发测试方便，使用了`docker` `docker-compose`，如果本地现在还没有，那么可以采用常规方式，官方下载`kafka`，启动服务。

以下是`docker-compose`的方式，依赖的`zookeeper`/`kafka`两个镜像，既不用关心下载和配置，也不用担心测试数据污染主机。

`docker-compose.yml`内容如下:
```yml
version: '2'
services:
    zookeeper-server:
        image: wurstmeister/zookeeper:latest
        ports:
            - "2181:2181"
    kafka-server:
        image: wurstmeister/kafka:latest
        links:
            - zookeeper-server
        ports:
            - "9092:9092"
        environment:
            KAFKA_ZOOKEEPER_CONNECT: zookeeper-server:2181
            KAFKA_ADVERTISED_HOST_NAME: 172.17.0.1
```
注意其中`172.17.0.1`是我`docker0`网卡的ip地址。

启动，在`docker-compose.yml`目录执行
```bash
# 初次启动
$ docker-compse up -d

# 停止的再次启动
$ docker-compose -d --no-recreate

# 具体可以查看帮助
$ docker-compose up --help
```


#### demos
* [队列模式/广播模式](src/main/java/org/orh/t1/readme.md)