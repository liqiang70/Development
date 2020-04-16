package com.example.demo;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.mybatisplus.dao.UserMasterMapper;
import com.example.demo.mybatisplus.entity.UserMaster;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月24日 下午10:44:03
 */

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SampleMasterTest {

	@Autowired
	private UserMasterMapper UserMasterMapper;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<UserMaster> UserMasterList = UserMasterMapper.selectList(null);
		Assertions.assertEquals(5, UserMasterList.size());
		UserMasterList.forEach(System.out::println);

		System.out.println(("----- insert method test ------"));
		LocalDateTime t = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
		UserMaster UserMaster = new UserMaster(6L, "cathy", 23, "test6@baomidou.com", t);
		UserMasterMapper.insert(UserMaster);

		UserMasterList = UserMasterMapper.selectList(null);
		Assertions.assertEquals(6, UserMasterList.size());
		UserMasterList.forEach(System.out::println);

		System.out.println(("----- select page method test ------"));

		QueryWrapper<UserMaster> wrapper = new QueryWrapper<UserMaster>();
		wrapper.like("email", "com").lt("age", 40);

		Page<UserMaster> page = new Page<UserMaster>(1, 3);
		// 如果不需要总记录数，则创建page对象时传入第三个参数为false即可。已验证。
		// Page<UserMaster> page = new Page<>(1,2,false);

		IPage<UserMaster> mapIPage = UserMasterMapper.selectPage(page, wrapper);

		System.out.println("总页数" + mapIPage.getPages());
		System.out.println("总记录数" + mapIPage.getTotal());
		List<UserMaster> records = mapIPage.getRecords();
		records.forEach(System.out::println);

	}

	@Test
	public void testSelectByWrapper2() {
		System.out.println(("----- selectByWrapper2 method test ------"));

		QueryWrapper<UserMaster> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("create_time", "2019-02-14").inSql("id", "select id from t_user where name like 'To%'");
		List<UserMaster> UserMasterList = UserMasterMapper.selectList(queryWrapper);
		UserMasterList.forEach(System.out::println);
	}

	@Test
	public void testSelectDefined() {
		System.out.println(("----- selectDefined method test ------"));

		QueryWrapper<UserMaster> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("create_time", "2019-02-14").inSql("id", "select id from t_user where name like 'To%'");
		List<UserMaster> UserMasterList = UserMasterMapper.selectAll();
		UserMasterList.forEach(System.out::println);
	}

	@Test
	public void lambdaSelect() {
		System.out.println(("----- lambdaSelect method test ------"));

		LambdaQueryWrapper<UserMaster> lambdaQueryWrapper = Wrappers.lambdaQuery();
		lambdaQueryWrapper.like(UserMaster::getName, "Sa").lt(UserMaster::getAge, 24);

		List<UserMaster> UserMasterList = UserMasterMapper.selectList(lambdaQueryWrapper);
		UserMasterList.forEach(System.out::println);

		List<UserMaster> UserMasterList2 = UserMasterMapper.selectList(null);
		UserMasterList2.forEach(System.out::println);
	}

	@Test
	public void updateTest1() {
		System.out.println(("----- Update1 method test ------"));
		UserMaster UserMaster = new UserMaster();
		UserMaster.setId(1L);
		UserMaster.setEmail("update1@email");
		int rows = UserMasterMapper.updateById(UserMaster);
		System.out.println(rows);

		List<UserMaster> UserMasterList = UserMasterMapper.selectList(null);
		UserMasterList.forEach(System.out::println);
	}

	@Test
	public void updateTest2() {
		System.out.println(("----- Update2 method test ------"));

		UpdateWrapper<UserMaster> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("name", "Tom").eq("age", 28);

		UserMaster UserMaster = new UserMaster();
		UserMaster.setEmail("update2@email");
		int rows = UserMasterMapper.update(UserMaster, updateWrapper);
		System.out.println(rows);

		List<UserMaster> UserMasterList = UserMasterMapper.selectList(null);
		UserMasterList.forEach(System.out::println);
	}

	@Test
	public void updateTest3() {
		System.out.println(("----- Update3 method test ------"));

		UpdateWrapper<UserMaster> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("name", "Tom").eq("age", 28).set("email", "update3@email.com");
		UserMasterMapper.update(null, updateWrapper);

		List<UserMaster> UserMasterList = UserMasterMapper.selectList(null);
		UserMasterList.forEach(System.out::println);
	}

	@Test
	public void updateByLambda() {
		System.out.println(("----- updateByLambda method test ------"));

		LambdaUpdateWrapper<UserMaster> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
		lambdaUpdateWrapper.eq(UserMaster::getName, "Billie").eq(UserMaster::getAge, 24).set(UserMaster::getAge, 27);
		UserMasterMapper.update(null, lambdaUpdateWrapper);

		List<UserMaster> UserMasterList = UserMasterMapper.selectList(null);
		UserMasterList.forEach(System.out::println);
	}
}
