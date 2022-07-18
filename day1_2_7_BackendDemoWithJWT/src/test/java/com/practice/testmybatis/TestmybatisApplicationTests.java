package com.practice.testmybatis;

import com.practice.testmybatis.domain.User;
import com.practice.testmybatis.mapper.UserAddMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TestmybatisApplicationTests {
    @Autowired
    UserAddMapper userAddMapper;
    @Test
    @Transactional
    void contextLoads() {
        userAddMapper.addUserMapper(new User("qwert1","qwert"));

        userAddMapper.addUserMapper(new User("qwert2","qwert"));

        userAddMapper.addUserMapper(new User("qwert3","qwert"));

        userAddMapper.addUserMapper(new User("qwert4","qwert"));

        userAddMapper.addUserMapper(new User("qwert1","qwert"));
//
//        userAddMapper.addUserMapper(new User("qwert5","qwert"));
//
//        userAddMapper.addUserMapper(new User("qwert6","qwert"));
//
//        userAddMapper.addUserMapper(new User("qwert7","qwert"));

    }

    void contextLoads2() {
        contextLoads();
    }

}
