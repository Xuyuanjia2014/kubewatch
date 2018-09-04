### 注意事项
1. springboot方式启动，在watch包下；
2. 普通maven项目；
3. 初始化工作再SimpleWatchAddition类中，也在watch包下，通过定义新的类可以控制watch的类型；
4. 数据库需要创建bocloud用户，数据库配置在resources下的Application.properties文件中。
5. 注意不暴露真的IP。
6. 需要修改本地数据库，建立上述bocloud用户，才能完成数据库配置，数据库文件在resources的sql下；
7. SimpleWatchAddition和AllInit初始化块里面，需要修改为真实的IP和端口。