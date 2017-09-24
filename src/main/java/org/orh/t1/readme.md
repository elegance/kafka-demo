## 运行步骤

#### 1. 简单的一个生产者 一个消费者 消费
* 分别运行 `FishProducer.java` 与 `CatConsumer.java` ，无先后顺序，从`消费者`控制台分别输入 `fishConsumer1`/`group1`回车
* 可以重复的从`生产者`控制台输入信息并回车，可以看到`消费者`控制台能及时的得到消息

#### 2. 基于上一步基础，往group1中新增一个消费者
* 运行`CatConsumer.java`，从`消费者`控制台分别输入 `fishConsumer2`/`group1`回车
* 回到`生产者控制台`, 重复的进行信息输入与回车，可以得到以下效果：
    * **形成了队列模式，即`fish-topic`只会有一个消费者会消费消息 (因为消费者都是在同一个group1中，只会有一个消费者消费到消息)**

#### 3. 基于以上步骤基础，新增消费者并且指定其为group2
* 运行`CatConsumer.java`，从`消费者`控制台分别输入 `fishConsumer3`/`group2`回车
* 回到`生产者控制台`, 重复的进行信息输入与回车，可以得到以下效果：
    * **形成广播模式，`fishConsumer2`/`fishConsumer3`都能得到消息**
    * **`fishConsumer3` 会把之前打历史消息都获取到，也就是偏移量 offset 从头开始**