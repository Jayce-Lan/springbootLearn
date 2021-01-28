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



### 更改配置文件方案

> 在主配置文件 *application.properties* 当中指明使用哪一套配置文件

```properties
spring.profiles.active=dev
```

文件夹下有 *application-dev.properties* 以及 *application-prod.properties* 两套配置文件，如果使用上述语句，则使用 *application-dev.properties* 来进行项目配置



## springbootView

> spring boot 整合视图层

- thymeleaf

- freemarker



## springbootWeb

> spring boot 整合web开发

### 返回JSON数据

#### 默认转换

##### @ResponseBody

> 默认实现方式

在 spring boot 的 web 依赖中，默认加入了 *jackson-databind* 作为 JSON处理器，因此不需要额外的JSON处理器即可返回JSON数据，只需要在*Controller*类中加入`@ResponseBody`注解即可

> 实现实例

```java
@GetMapping("/book")
@ResponseBody
public Book book() {
    Book book = new Book();
    book.setName("围城");
    book.setAuthor("钱钟书");
    book.setPrice(30f);
    book.setPublicationDate(new Date());
    return book;
}
```



##### @RestController

> 声明该类下的所有属性默认均转换为JSON数据

如果一个*controller*类中的方法均需要转为JSON数据，那么，只需要在类的头部加入此注解即可，这样方法顶部可以省略掉`@ResponseBody`注解

> 实例

```java
@RestController
public class BookController {
    @GetMapping("/book")
    public Book book() {
        ...
    }
}
```





##### @JsonIgnore

> 在JSON数据中隐藏该属性



#####  @JsonFormat(pattern = "yyyy-MM-dd")

> 声明属性显示的格式



> 实例

```java
private String name;
private String author;
@JsonIgnore		//规定了该属性不在JSON数据中展示
private Float price;
@JsonFormat(pattern = "yyyy-MM-dd")		//规定了日期显示的格式
private Date publicationDate;
```



#### 自定义转换器

##### Gson

导入Gson依赖

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.5</version>
</dependency>
```

添加一个JSON转换类`GsonConfig`，并且自定义方法`gsonHttpMessageConverter`

```java
@Configuration
public class GsonConfig {
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd");
        builder.excludeFieldsWithModifiers(Modifier.PROTECTED);
        Gson gson = builder.create();

        converter.setGson(gson);
        return converter;
    }
}
```

在转换中，如果我们定义的实体类属性不希望被看到，那么我们可以将其属性设置为`protected`

```java
private String name;
private String author;
protected Float price;  //在Gson中，使用protected关键字类似于使用了@JsonIgnore注解
private Date publicationDate;
```



##### fastjson

引入依赖

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.73</version>
</dependency>
```

自定义工具类

```java
@Configuration
public class MyFastJsonConfig {
    @Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setDateFormat("yyyy-MM-dd");
        config.setSerializerFeatures(
                SerializerFeature.WriteClassName,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullStringAsEmpty
        );
        converter.setFastJsonConfig(config);
        return converter;
    }
}
```

在配置文件中完成响应编码，防止中文乱码

```properties
server.servlet.encoding.force-request=true
```

