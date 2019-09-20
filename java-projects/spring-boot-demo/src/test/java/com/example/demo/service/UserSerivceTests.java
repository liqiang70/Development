package com.example.demo.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.*;

import com.example.demo.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSerivceTests {

	@Autowired
    private UserService userService;
	
    @Test
    public void getUser(){
        User user=userService.selectById(1);
        Assert.assertThat(user.getUsername(), is( "zhangsan" ) );
    }

}
