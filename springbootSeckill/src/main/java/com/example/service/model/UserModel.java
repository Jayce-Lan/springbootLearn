package com.example.service.model;

/*
DataObject是对数据库的映射，而model是处理逻辑的模型
 */
public class UserModel {
    private Integer id;
    private String name;
    private Byte gender;
    private Integer age;
    private String telphone;
    private String registerMode;
    private String thiedPartyId;
    private String encrptPassword;

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

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getThiedPartyId() {
        return thiedPartyId;
    }

    public void setThiedPartyId(String thiedPartyId) {
        this.thiedPartyId = thiedPartyId;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", telphone='" + telphone + '\'' +
                ", registerMode='" + registerMode + '\'' +
                ", thiedPartyId='" + thiedPartyId + '\'' +
                ", encrptPassword='" + encrptPassword + '\'' +
                '}';
    }
}
