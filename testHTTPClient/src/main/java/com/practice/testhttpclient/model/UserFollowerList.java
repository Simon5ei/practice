package com.practice.testhttpclient.model;

import lombok.Data;

import java.util.List;
@Data
public class UserFollowerList {
    private Integer id;
    private List<String> followList;
    private Integer followerCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getFollowList() {
        return followList;
    }

    public void setFollowList(List<String> followList) {
        this.followList = followList;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public UserFollowerList(User u) {
        this.setId(u.getId());
        this.setFollowerCount(u.getFollowerCount());
        this.setFollowList(u.getFollowerList());
    }

    @Override
    public String toString() {
        return "UserFollowerList{" +
                "id=" + id +
                ", followList=" + followList.toString() +
                ", followerCount=" + followerCount +
                '}';
    }
}
