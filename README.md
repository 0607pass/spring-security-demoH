<!-- Improved README Banner -->
<div align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.2.5-green?style=for-the-badge&logo=springboot" alt="Spring Boot Version">
  <img src="https://img.shields.io/badge/Spring%20Security-6.x-blue?style=for-the-badge&logo=springsecurity" alt="Spring Security Version">
  <img src="https://img.shields.io/badge/JWT-JsonWebToken-yellow?style=for-the-badge&logo=jsonwebtokens" alt="JWT">
  <img src="https://img.shields.io/badge/License-MIT-red?style=for-the-badge" alt="License">
</div>

<h1 align="center">🔐 Spring Security JWT 权限认证系统</h1>

<p align="center">
  基于 Spring Boot 3.x、Spring Security 6.x 和 JWT 实现的现代化无状态认证与权限控制系统
</p>

<p align="center">
  <a href="#特性"><strong>特性</strong></a> •
  <a href="#技术栈"><strong>技术栈</strong></a> •
  <a href="#快速开始"><strong>快速开始</strong></a> •
  <a href="#api接口"><strong>API接口</strong></a> •
  <a href="#架构设计"><strong>架构设计</strong></a>
</p>

---

## 🌟 特性

🔒 **JWT 无状态认证** - 基于 JWT 的无状态认证机制，适用于分布式系统  
🔄 **Token 自动续期** - 当 Token 快过期时自动续期，提升用户体验  
🛑 **Token 黑名单机制** - 支持强制用户下线，增强安全控制  
🎯 **方法级权限控制** - 使用 [@PreAuthorize](file:///E:/code/spring-security-demoH/src/main/java/com/example/demo/config/SecurityConfig.java#L27-L27) 注解实现细粒度权限控制  
🚫 **统一异常处理** - 全局异常处理器，统一 JSON 格式错误响应  
🔗 **MyBatis + MySQL** - 完整的用户数据持久化方案  

## ⚙️ 技术栈

| 技术 | 说明 | 版本 |
|------|------|------|
| [Spring Boot](file:///E:/code/spring-security-demoH/src/main/java/com/example/demo/SecurityDemoApplication.java#L3-L3) | 核心框架 | 3.2.5 |
| [Spring Security](file:///E:/code/spring-security-demoH/src/main/java/com/example/demo/config/SecurityConfig.java#L12-L12) | 安全框架 | 6.x |
| JWT | Token 认证机制 | 0.11.5 |
| [MyBatis](file:///E:/code/spring-security-demoH/src/main/resources/application.yml#L18-L19) | ORM 框架 | 3.0.3 |
| [MySQL](file:///E:/code/spring-security-demoH/src/main/resources/application.yml#L12-L12) | 关系型数据库 | 8.x |
| Maven | 项目构建管理 | 3.x |

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- MySQL 8.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone https://github.com/your-username/spring-security-demo.git
   cd spring-security-demo
   ```

2. **配置数据库**
   修改 [application.yml](file:///E:/code/spring-security-demoH/src/main/resources/application.yml) 文件中的数据库连接信息：
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/security_demo?useSSL=false&serverTimezone=Asia/Shanghai
       username: your_username
       password: your_password
   ```

3. **初始化数据库**
   执行 [schema.sql](file:///E:/code/spring-security-demoH/schema.sql) 脚本创建表结构和初始数据

4. **启动项目**
   ```bash
   mvn spring-boot:run
   ```

## 📡 API接口

### 认证相关

| 接口 | 方法 | 描述 |
|------|------|------|
| `/auth/login` | POST | 用户登录，获取 JWT Token |
| `/auth/logout` | POST | 用户登出，Token 加入黑名单 |

### 示例请求

**用户登录**
```bash
curl -X POST http://localhost:8080/auth/login \
  -d "username=admin&password=123456"
```

响应示例：
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

**访问受保护资源**
```bash
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9..." \
  http://localhost:8080/api/hello
```

## 🏗️ 架构设计

### 认证流程

```mermaid
sequenceDiagram
    participant Client
    participant Server
    participant JWTUtil
    
    Client->>Server: POST /auth/login (用户名+密码)
    Server->>Server: 验证用户凭据
    Server->>JWTUtil: 生成JWT Token
    JWTUtil-->>Server: 返回Token
    Server-->>Client: 返回Token
    
    Client->>Server: GET /api/** (携带Token)
    Server->>JWTUtil: 解析并验证Token
    JWTUtil-->>Server: 返回解析结果
    Server-->>Client: 返回业务数据
```

### 核心组件

1. **JwtAuthenticationFilter** - JWT 认证过滤器，拦截请求进行 Token 验证
2. **JwtUtils** - JWT 工具类，负责 Token 的生成、解析和续期判断
3. **TokenBlacklist** - Token 黑名单管理，处理用户登出逻辑
4. **SecurityConfig** - Spring Security 配置类，定义安全策略

## 📄 License

本项目采用 MIT 协议开源，详情请查看 [LICENSE](file:///E:/code/spring-security-demoH/LICENSE) 文件。