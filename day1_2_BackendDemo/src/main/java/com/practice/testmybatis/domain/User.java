package com.practice.testmybatis.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor //全参构造函数
@NoArgsConstructor  //无参构造函数
public class User {
    private Integer iduser;
    private String name;
    private String pswd;
    private Boolean isdel;
    private Date createtime;
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Boolean getIsdel() {
        return isdel;
    }

    @Override
    public String toString() {
        return "User{" +
                "iduser=" + iduser +
                ", name='" + name + '\'' +
                ", isdel=" + isdel +
                ", pswd='" + pswd + '\'' +
                '}';
    }

    public void setIsdel(Boolean isdel) {
        this.isdel = isdel;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

