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

#### 2.2 方法二: 基于 Java 注解

- 配置类: `@Configuration` + `@Bean`
  - **Bean-ID**: 默认是把这个这个方法的返回类型的首字母小写来当做 **ID**，也可以显示声明: `@Bean(name="bean-id")`
- 启动类: 使用 `AnnotationConfigApplicationContext` 来查找这个配置类

#### 2.3 方法三: 基于 XML

- 配置 XML 文件: 使用 `XML` 文件来提供 `Bean` 的初始化
- 启动类: 使用 `ClassPathXmlApplicationContext` 来查找这个配置文件

```java
public class CDPlayer implements MediaPlayer {

    private CompactDisc compactDisc;

    // 硬依赖选择构造器注入
    @Autowired
    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    // 可选依赖选择方法注入
    @Autowired
    public void setCompactDisc(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    public void play() {
        compactDisc.play();
    }
}
```

### 3. Advanced wiring

#### 3.1 生产环境和开发环境的切换与配置

- `@Profile(dev)` + `ActiveProfile(dev)` 配合使用
- `profile="dev"` + `<param-value>dev</param-value>` 配合使用

#### 3.2 条件 `Bean` - 只在满足某些条件的情况下才配置这个 `Bean`

- `@Bean` + `@Conditional(Condition.class)`

#### 3.3 解决冲突

当使用 `autowiring` 来注入 `Bean` 的时候，假设某个接口有多个实现，这个时候 `Spring` 就不知道选择哪一个 `Bean` 了。

- `@Primary` 注解声明选择这个 `Bean`
- `@Qualifier` 可以添加多个注解，来描述符合你想要的特征的类

默认情况下，`Bean` 的声明周期是单例，但有些情况下，你可能并不想要这种模式。`Spring` 为你提供了四种 `Bean` 的生命周期:

- `Singleton`: 只创建一次
- `Prototype`: 每次都创建
- `Session`: 每一个 `Session` 仅创建一次
- `Request`: 每一个请求创建一次

使用 `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)` 来声明这种生命周期

#### 3.4 请求和会话 `Bean`

#### 3.5 运行时注入

- **值从外部获得**: `@PropertySource("classpath:/com/zk/app.properties")` + 从 `Environment.getProperty` 来获取字段对应的值

**Spring 3** 引入了 **Spring Expression Language**:

- 常量表达式:

```
#{3.14159}
#{'Hello'}
#{false}
```

- 应用其他 `Bean`:

```
#{otherBeanId}
#{otherBeanId.property}
#{otherBeanId.method()}
#{otherBeanId.method()?.toUpperCase()}
```

其中 `?.` 表示如果不为 `null`，那么就转为大写，否则就直接返回 `method()` 的值

- 类型:

```
T(java.lang.Math)
T(java.lang.Math).PI
T(java.lang.Math).random()
#{disc.title ?: 'Rattle and Hum'}
```

`?:` 是用来检查是否为 `null` 的，如果不为 `null` 返回 `disc.title`，如果为 `null`，直接返回 `'Rattle and Hum`。 另外也支持很丰富的逻辑表达式之类的东西

- 正则:

```
#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}
```

- 集合:

```
#{jukebox.songs[4].title}
#{'This is a test'[3]}
#{jukebox.songs.?[artist eq 'Aerosmith']}
#{jukebox.songs.^[artist eq 'Aerosmith']}
#{jukebox.songs.![title]}
```

`.?[artist eq 'Aerosmith']` 是用来过滤这个集合的，将满足条件的元素过滤出来

`.^[artist eq 'Aerosmith']` 是用来筛选出第一个匹配的元素

`.![title]` 把这个集合的所有 `title` 属性值取出来，然后重新组合成一个新的集合
