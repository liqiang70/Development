package com.example.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad
 * Date:2020年3月23日 下午6:58:05
 */

/**
 * @author whh
 * @date 2019/3/22 13:59
 */
// 添加下面这个注解就可以运行测试了
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Junit5BaseTest {

	static String result;

	/**
	 * 只执行一次，执行时机是在所有测试和 @BeforeEach 注解方法之前。
	 */
	@BeforeAll
	static void beforeAll() {
		result = "beforeAll";
		System.out.println("第一次初始化，result = " + result);
	}

	/**
	 * 只执行一次，执行时机是在所有测试和 @AfterEach 注解方法之后。
	 */
	@AfterAll
	static void afteraAll() {
		System.out.println("执行结束后 result= " + result);
	}

	/**
	 * 在每个测试执行之前执行
	 */
	@BeforeEach
	void beforeEach() {
		result = "beforeEach";
		System.out.println("测试执行之前，result = " + result);
	}

	/**
	 * 在每个测试执行之后执行
	 */
	@AfterEach
	void afterEach() {
		result = "afterEach";
		System.out.println("测试结束后 result= " + result);
	}

	/**
	 * 测试案例，可以写增删改查的测试案例
	 */
	@Test
	void runTest() {
		
		System.out.println("测试案例：result = " + result);
	}
	
}
