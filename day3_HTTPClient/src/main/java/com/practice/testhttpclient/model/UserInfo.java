package com.practice.testhttpclient.model;

import lombok.Data;

@Data
public class UserInfo {
    private Integer id;
    private String name;
    private Integer followerCount;
    private String nikeName;

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

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public UserInfo(User u) {
        this.setId(u.getId());
        this.setFollowerCount(u.getFollowerCount());
        this.setName(u.getName());
        this.setNikeName(u.getNikeName());
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", followerCount=" + followerCount +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }
}
