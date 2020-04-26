# tomato
番茄：基于微服务构建的C2C平台



1. 注册中心 / 配置中心：nacos-server-1.2.1	修改端口号为：18080

   请求 http://localhost:18080/nacos/index.html 用户名/密码：nacos/nacos

2. 用户认证服务
    使用模拟数据（内存数据）测试获取token  
    postman 测试：http://localhost:10020/oauth/token?grant_type=password&username=user&password=123456

3. 登录
 ![登录](https://i.loli.net/2020/04/26/VrihDJbyEMaAUpj.png)
