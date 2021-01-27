package com.example.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "userlist")
public class Users {
    private List<User> users;

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }

    public List<User> getUserList() {
        return users;
    }

    public void setUserList(List<User> users) {
        this.users = users;
    }
}
