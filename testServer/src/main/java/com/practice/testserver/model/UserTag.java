package com.practice.testserver.model;

import lombok.Data;

import java.util.Map;
@Data
public class UserTag {
    private String name;
    private Map<String, Integer> tagAndAgree;
    private String nikeName;

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
