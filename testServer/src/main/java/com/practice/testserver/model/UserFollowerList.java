package com.practice.testserver.model;

import lombok.Data;

import java.util.List;
@Data
public class UserFollowerList {
    private Integer id;
    private List<String> followerList;
    private Integer followerCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getFollowList() {
        return followerList;
    }

    public void setFollowList(List<String> followList) {
        this.followerList = followList;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
}
