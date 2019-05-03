### 注意事项
1. springboot方式启动，在watch包下；
2. 普通maven项目；
3. 初始化工作再SimpleWatchAddition类中，也在watch包下，通过定义新的类可以控制watch的类型；
4. 数据库需要创建bocloud用户，数据库配置在resources下的Application.properties文件中。
5. 注意不暴露真的IP。
6. 需要修改本地数据库，建立上述bocloud用户，才能完成数据库配置，数据库文件在resources的sql下；
7. SimpleWatchAddition和AllInit初始化块里面，需要修改为真实的IP和端口。

### 实验目的

1. 学习java基础及spring-boot框架的开发规范；
2. 学习Kubernetes的使用概念和相关实体对象；
3. 学习kubernetes watch接口的原理，理解isdream框架对事件的封装；
4. 自定义事件处理流程，总结有状态服务和无状态对象的生命周期；
5. 探索deployment-replicationset-pod三者的关联、依赖关系，讨论现有接口的不足；
6. 根据生命周期和关联依赖关系，探索可行的事件处理加速方法。