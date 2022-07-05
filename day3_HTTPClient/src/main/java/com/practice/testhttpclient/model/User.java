package com.practice.testhttpclient.model;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class User {
    private Integer id;
    private String  name;
    private Map<String,Integer> tagAndAgree;
    private List<String> followerList;
    private Integer followerCount;
    private String nikeName;
}
/* TestData
    {
    "id":1231,
    "name":"simon5ei",
    "followerCount":3,
    "tagAndAgree":{
        "宅":1,
        "乐":3,
        "典":1
    },
    "followerList":[ "wsm1", "wsm2", "wsm3" ],
    "nikeName":"zvezdi"
}
*/

