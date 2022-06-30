package com.practice.testhttpclient.model;

import lombok.Data;

import java.util.Map;
@Data
public class UserTag {
    private String name;
    private Map<String, Integer> tagAndAgree;
    private String nikeName;

    public UserTag(User user) {
        this.name = user.getName();
        this.tagAndAgree = user.getTagAndAgree();
        this.nikeName = user.getNikeName();
    }

    @Override
    public String toString() {
        return "UserTag{" +
                "name='" + name + '\'' +
                ", tagAndAgree=" + tagAndAgree.toString() +
                ", nikeName='" + nikeName + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getTagAndAgree() {
        return tagAndAgree;
    }

    public void setTagAndAgree(Map<String, Integer> tagAndAgree) {
        this.tagAndAgree = tagAndAgree;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }
}
