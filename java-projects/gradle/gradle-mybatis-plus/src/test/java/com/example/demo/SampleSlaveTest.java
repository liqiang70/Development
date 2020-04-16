package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.mybatisplus.dao.UserSlaveMapper;
import com.example.demo.mybatisplus.entity.UserSlave;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月25日 下午7:00:42
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SampleSlaveTest {

	@Autowired
	private UserSlaveMapper userSlaveMapper;

	@Test
	public void deleteById() {
		userSlaveMapper.deleteById(4566L);
	}

	@Test
	public void selectIgnoreDeleteTest() {
		UserSlave user = userSlaveMapper.selectById(3456L);
		System.out.println(user.toString());
	}

    @Test
    public void testLock(){
        int version = 1;
        UserSlave user = new UserSlave();
        user.setEmail("wtf@163.com");
        user.setAge(34);
        user.setId(2345L);
        user.setManagerId(1234L);
        user.setVersion(version);
        userSlaveMapper.updateById(user);
    }
}
