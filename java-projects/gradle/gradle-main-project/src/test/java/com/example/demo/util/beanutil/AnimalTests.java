package com.example.demo.util.beanutil;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月21日 上午10:18:29
 */

public class AnimalTests {

	/**
	 * BeanUtils
	 */
	@Test
	public void test1() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "tuantuan");
		map.put("age", 3);
		map.put("color", "black");
		map.put("sex", "female");

		// 将map数据拷贝到javabean中
		Animal a1 = new Animal();
		BeanUtils.populate(a1, map);
		System.out.println(a1); // Animal [name=tuantuan, age=3, color=black, sex=female]

		// 对象的拷贝
		Animal a2 = new Animal();
		BeanUtils.copyProperties(a2, a1);
		System.out.println(a2);// Animal [name=tuantuan, age=3, color=black, sex=female]

		// 拷贝指定的属性
		Animal a3 = new Animal();
		BeanUtils.copyProperty(a3, "name", "yuanyuan");
		System.out.println(a3); // Animal [name=yuanyuan, age=0, color=null, sex=null]

		// 设置指定的属性
		BeanUtils.setProperty(a3, "sex", "male");
		System.out.println(a3); // Animal [name=yuanyuan, age=0, color=null, sex=male]

		// 克隆对象
		Object object = BeanUtils.cloneBean(a3);
		System.out.println(object); // Animal [name=yuanyuan, age=0, color=null, sex=male]

		// 异构对象的拷贝
		Animal_new a_new = new Animal_new();
		BeanUtils.copyProperties(a_new, a1);
		System.out.println(a_new);// Animal [name=tuantuan, age=3, color=black, sex=female]

		// 异构对象的拷贝2
		Zoo zoo = new Zoo();
		zoo.setName("QingDao ZOO");
		zoo.setCity("QingDao");
		a1.setFromZoo(zoo);

		Animal_new a_new2 = new Animal_new();
		BeanUtils.copyProperties(a_new2, a1);
		System.out.println(a_new2);// Animal [name=tuantuan, age=3, color=black, sex=female]

	}
}
