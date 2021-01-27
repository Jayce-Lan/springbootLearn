# SpringBoot学习记录

## hellospringboot

### 配置文件的读取顺序

> 由高到低排序

- 根目录下的 *config* 文件夹
- 根目录下直接存放
- *resources* 文件夹下的 *config* 文件夹
- *resources* 文件夹下直接存放



### 用于读取配置文件的注解

> 在一个类上标明注解，可以读取配置文件中的配置

```java
@Component
@ConfigurationProperties(prefix = "my")
public class User {
    private String name;
    private String address;
    
    /**getter and setter*/
    
}
```



#### @Component

声明该类为组件类



#### @ConfigurationProperties(prefix = "xxx")

读取配置文件中的 xxx 属性

例如上面的例子中，配置文件`application.yml`中有如下属性：

```yaml
my:
  name: test
  address: China
```

在声明了读取"my"属性之后，*user*的*name*与*address*两个属性就会被赋值