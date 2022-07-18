# Pracitce

## demo练习
### day1&2
SpringBoot速览，Mybatis整合，Redis整合，SpringMVC注释
### day3
Json解析，HttpClient应用
### day4
编解码操作
### day5
文件校验，AOP编程概念，自定义注解，easyExcel
### day6
线程池操作，RocketMQ概念、消息生产与监听消费和设定Delay消息
### day7
策略模式编程
### day8
JWT整合,logback整合

## 业务相关
### day9
项目代码阅读
### day10
项目文档阅读
### day11
修改BigDecimal除法相关BUG

使用了策略模式封装代码。
### day12
项目技术理解（RocketMQ相关）
### day13&day14
项目技术理解（ActivitiWorkFlow相关）

工作流引擎提供每一个步骤的入口和出口，所有的步骤实现一个service接口

在每一个步骤执行时Activiti会调用controller的类，该类会生产MQ消息

这个消息会带一个Json，里面存了特定service的serviceCode

消费端会根据得到的serviceCode传入一个service接口的实现类

通过从getBean(clazz)获取该类的实例，并执行该类中的execute方法来完成这一业务步骤

失败的操作会存到流程表里，客户可以选择重新提交（生产端重新发送消息）使消费端继续消费

这里MQ的作用是可以使操作异步提高效率，并保存消息，如果服务器宕机，重启时也可以继续消费

### day15
项目逻辑理解

假如是用户任务：

工作流引擎调用对应的controller接口添加任务，然后给对应的表添加相关字段

将当前的的Task进行指派，并传入Redis做Cache，使用zset，zset的作用是记录录入操作的次数

如果录入操作失误，score值就会+1，同时有一个定时任务处理大于1的录入操作并将其再次放入进行指派的任务

如果score值大于3，就会清理该条cache，指派任务成功也会删除该cache

随后程序会更改相关数据表的值，任务指派就完成了，指派完成之后用户便可以在业务系统中看到其被分配的业务

假如是系统任务：

工作流引擎会调用对应的controller接口，然后给RocketMQ发送消息

一个类型的Task对应service接口的一个实现类，给MQ发送的消息会携带一个实现类

然后使用getbean在applicationcontext中注入依赖，并且执行这个类中实现的方法

同时将更改的信息存入数据表，如果失败，也可以在表中查询到相关信息，并重新提交这类任务

### day16
理解学习IO模型，了解Tomcat的NIO机制