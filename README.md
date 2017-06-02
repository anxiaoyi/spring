# spring

### 1. Spring in Action

几种常用的加载 `Bean` 的 `Context`:

- `FileSystemXmlApplicationContext`
- `ClassPathXmlApplicationContext`
- `AnnotationConfigApplicationContext`
- `XmlWebApplicationContext`
- `AnnotationConfigWebApplicationContext`

### 2. Wiring beans

#### 2.1 方法一: 自动扫描

- 配置类: `@ComponentScan` + `@Configuration`
  - **指定扫描的包**: `@ComponentScan("soundsystem")`，指定多个包: `@ComponentScan(basePackages={"soundsystem", "video"})`，指定类所在的包: `@ComponentScan(basePackageClasses={CDPlayer.class, DVDPlayer.class})`
- 等待实现的接口类: `@Autowired`
  - **避免异常**: 默认找不到的情况下，会直接抛出异常。使用 `@Autowired(required=false)` 来避免抛出异常
  - **声明位置**: 既可以是字段，也可以是 `setter` 方法
- 实现了接口的类: `@Component`
  - **Bean-ID**: 默认是把这个 `Component` 的类名的首字母小写来当做 **ID**，也可以显示声明: `@Component("bean-id")`

#### 2.2 方法二: 基于 Java

- 配置类: `@Configuration` + `@Bean`
  - **Bean-ID**: 默认是把这个这个方法的返回类型的首字母小写来当做 **ID**，也可以显示声明: `@Bean(name="bean-id")`
- 启动类: 使用 `AnnotationConfigApplicationContext` 来查找这个配置类

#### 2.3 方法三: 基于 XML

- 配置 XML 文件: 使用 `XML` 文件来提供 `Bean` 的初始化
- 启动类: 使用 `ClassPathXmlApplicationContext` 来查找这个配置文件
