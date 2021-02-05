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



### 重要文件目录

main/java

- com.example.config【存储工具类】
  - `GsonConfig.java`----------【Gson字符串的实现工具类】
  - `MyFastJsonConfig.java`----------【FastJson字符串的实现工具类】
  - `MyWebConfig.java`----------【读取静态资源的工具类】
- com.example.controller【存储web层接口】
  - `BookController.java`----------【读取Json数据的web接口】
  - `FileUploadController.java`----------【实现文件上传的接口】
- com.example.pojo【存储实体类】
- `SpringbootWebApplication.java`----------【项目启动类】

main/resources

- static【存放测试静态资源】
  - `img.jpg`----------【用于测试的图片】
  - `upload.html`----------【单个文件上传的网页】
  - `uploadfiles.html`----------【多个文件上传的网页】
- templates
- `application.properties`----------【配置文件】



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



### 静态资源访问

在配置文件中配置静态资源访问

```properties
spring.mvc.static-path-pattern=/static/**
spring.web.resources.static-locations=classpath:/static/
```

创建Java类进行访问

```java
package com.example.config;

import ...

@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
```



以上两种方式均可以实现访问，在加载后，只需要访问 "http://localhost:8080/static/img.jpg" ，即可访问 *static* 目录下的 *img.jpg*



### 文件上传

在 *spring-boot-starter-web* 中已经有默认的单文件上传的依赖



### @ControllerAdvice

> Controller的增强版，常用于处理全局数据，一般搭配@ExceptionHandler、@ModelAttribute以及@InitBinder使用



#### 全局异常处理

与`@ExceptionHandler` 搭配使用，可以进行全局异常处理

> 实例

```java
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ModelAndView uploadException(IOException e) throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", "上传文件大小超出限制！");
        mav.setViewName("error");

        return mav;
    }
}
```



#### 添加全局数据

与`@ModelAttribute` 搭配使用可以添加全局数据



#### 请求参数预处理

与`@ModelAttribute` 以及`@InitBinder` 搭配使用，可以进行参数的预处理



## springbootError

### 自定义错误页

#### 4xx与5xx页面

类似于404/500等的页面，我们只需要在`static` 文件夹下放入一个 `error` 文件夹，并且在其内部写入对应参数的html页面即可



#### 动态错误页

由于在 `static` 中定义的错误页为静态页面，因此我们可以导入*thymeleaf*，使得错误页灵活展现



### 继承DefaultErrorAttributes的类

```java
@Component
public class MyErrorAttribute extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        errorAttributes.put("custommsg", "出错了！");
        errorAttributes.remove("error");

        return errorAttributes;
    }
}
```



## springbootCORS

> *CORS*是W3C制定的一种跨域资源共享标准，目的是解决前端跨域请求

如下面的*controller*类

```java
@RestController
@RequestMapping("/book")
public class BookController {
    @PostMapping("/")
    public String addBook(String name) {
        return "receive:" + name;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return String.valueOf(id);
    }
}
```

该类中提供了添加和删除两个接口

通常情况下，跨域传输有两个地方可以配置



### 注解配置

**@CrossOrigin**

value：表示支持的域

maxAge：请求的有效期，默认属性1800秒，请求发起后探测请求不需要每次都发送

allowedHeaders：请求头，"*"表示所有请求头都会被允许

```java
@PostMapping("/")
@CrossOrigin(value = "http://localhost:8081", maxAge = 1800, allowedHeaders = "*")
public String addBook(String name) {
    return "receive:" + name;
}
```

注解配置可以精确控制到方法



### 全局配置

全局配置需要实现 `WebMvcConfigurer` 接口

```java
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/book/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("http://localhost:8081");
    }
}
```



## springbootConfigure

### 项目主要目录

main/java

- com.example.config【存储工具类】
  - `Beans.java`----------【读取配置文件的类】
  - `WebMvcConfig.java`----------【拦截器设置类】
- com.example.controller【存储web层接口】
  - `HelloController.java`----------【Hello类的web接口】
- com.example.hello
  - `hello.java`----------【一个被注册进入容器的工具类】
- com.example.handler
  - `MyInterceptor1.java`----------【拦截器，实现HandlerInterceptor接口】
- `SpringbootConfigureApplication.java`----------【项目启动类】

main/resources

- static【存放测试静态资源】
- templates
- `application.properties`----------【配置文件】
- `beans.xml`----------【Spring配置文件】



### 配置类与XML配置

> Spring Boot 推荐使用Java来完成相关的配置工作。在项目中，不建议将所有的配置都放在一个配置类中

#### 如需使用*xml*进行配置，那么需要以下三步：

##### 定义一个类并且写入这个类的方法

```java
public class Hello {
    public String sayHello(String name) {
        return "Hello," + name;
    }
}
```



##### 配置bean文件，将定义的类注册进入IoC容器

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="hello" class="com.example.hello.Hello"/>
</beans>
```



##### 定义一个配置类Beans，将配置文件引入

```java
@Configuration
@ImportResource("classpath:beans.xml")  //导入资源
public class Beans {
}
```

如果省略该步骤，则会出现Spring无法加载bean，那么，Hello类也就无法被加载使用



在完成上述三步之后，Hello类就可以在Spring容器中被使用

```java
@RestController
public class HelloController {
    @Autowired
    Hello hello;

    @GetMapping("/hello")
    public String hello() {
        return hello.sayHello("Jayce");
    }
}
```



### 注册拦截器

拦截器中的方法按照 *preHandle* >>> *Controller* >>> *postHandle* >>> *afterCompletion* 的顺序执行

> 注意事项

- 只有*preHandle* 的方法返回值为 true 才会执行下面的方法
- 当存在多个拦截器链时，*postHandle* 才会执行，该方法只有拦截器链内的所有拦截器返回成功才会调用
- *afterCompletion* 只有 *preHandle* 返回 true 才会被调用，拦截器链内的任意 *preHandle* 返回 false ，后面的方法都不会被执行



#### 拦截器主要步骤

##### 定义一个实现 *HandlerInterceptor* 接口的拦截器类

> 该类主要写明拦截器在每个步骤中需要做的事情/方法

```java
public class MyInterceptor1 implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1 >>> preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1 >>> postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1 >>> afterCompletion");
    }
}
```



##### 定义一个实现 *WebMvcConfigurer* 接口的拦截器工具类

> 该类定义拦截器拦截的内容

```java
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * addPathPatterns表示拦截路径
     * excludePathPatterns表示排除的路径
     *
     * @param registry 被拦截的内容
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor1())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello");
    }
}
```



完成以上配置之后，整个项目中除了 /hello 这个路径下的所有路径，被访问时都会被拦截器拦截



## springbootWeb2

### 整合 *Servlet* 、*Filter* 和 *Listener*

配置完成三个工具类之后，需要在启动类中加入注解`@ServletComponentScan`

```java
@SpringBootApplication
@ServletComponentScan
public class SpringbootWeb2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWeb2Application.class, args);
	}

}
```

