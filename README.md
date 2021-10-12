## 项目介绍

`pmApi`是Project Management Api的简称，采用SpringBoot + MyBatis实现。

### 组织结构

``` lua
com.pm.pmapi
├── common -- 通用代码
    ├── api -- 通用返回码、返回对象
    ├── exception -- 通用异常处理
    ├── utils -- 工具类
├── config -- 配置类
├── controller -- 业务调度与跳转，采用RestFul化api接口
├── dao -- 自定义数据库操作抽象接口
├── dto -- 自定义数据类
├── mybatis -- MyBatisGenerator生成的数据库操作代码
├── service -- 功能服务
    ├── impl -- 服务接口的具体实现
```
