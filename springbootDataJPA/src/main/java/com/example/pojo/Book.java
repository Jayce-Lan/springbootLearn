package com.example.pojo;

import javax.persistence.*;

//该注解表示该类为一个实体类，启动项目时会自动生成一张表，表的名称为注解中的"name"的值，如果不设定，那表明即为实体类名
@Entity(name = "t_book")
public class Book {
    @Id //说明该属性为表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自动生成
    private Integer id;
    @Column(nullable = false)   //定制属性，注解中的 name 可以设置对应数据库表的名称，不设置则为默认属性，nullable 表示字段非空
    private String name;
    private String author;
    private Float price;
    @Transient  //表示建表时忽略该属性
    private String description;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
