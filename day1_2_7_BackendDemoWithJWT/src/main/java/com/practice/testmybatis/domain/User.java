package com.practice.testmybatis.domain;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String getToken(User user) {
        String token="";
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        token= JWT.create()
                .withHeader(map)
                .withClaim("iduser",user.getIduser())
                .withClaim("name",user.getName())
                .withClaim("pswd",user.getPswd())
                .withAudience(user.getName())
                .sign(Algorithm.HMAC256("1234"));
        return token;
    }
    public static String getIduserToken(String token) throws JWTVerificationException {
        Algorithm algorithm=Algorithm.HMAC256("1234");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
//        System.out.println("iduser:"+JWT.decode(token).getClaims().get("name").asString());
//        System.out.println(verifier.verify(token));
        DecodedJWT jwt =verifier.verify(token);
        Map<String,Claim> claims=jwt.getClaims();
        Claim claim = claims.get("name");
        return claim.asString();
    }
//    /**
//     * 根据Token获取user_id
//     *
//     * @param token
//     * @return user_id
//     */
//    public static String getAppUID(String token) throws NullPointerException{
//        Claim iduser = verifyToken(token);
//        System.out.println(iduser.asString());
//        if (null == iduser|| StringUtils.isEmpty(iduser.asString())) {
//            System.out.println("token verify fail");
//        }
//        return iduser.asString();
//    }
}

