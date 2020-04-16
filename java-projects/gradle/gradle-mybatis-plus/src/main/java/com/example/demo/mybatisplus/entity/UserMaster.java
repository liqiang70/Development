package com.example.demo.mybatisplus.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月24日 下午10:41:25
 */

// for level-1
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_user")
public class UserMaster {
	private Long id;
	private String name;
	private Integer age;
	private String email;
	private LocalDateTime createTime;
}
