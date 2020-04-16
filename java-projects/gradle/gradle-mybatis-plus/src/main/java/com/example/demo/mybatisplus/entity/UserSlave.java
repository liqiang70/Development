package com.example.demo.mybatisplus.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月24日 下午10:41:25
 */

//for level-2
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("user")
public class UserSlave extends Model<UserSlave> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3880967579761220331L;
	@TableId(type = IdType.NONE)
	private Long id;
	@TableField(condition = SqlCondition.LIKE)
	private String name;
	private Integer age;
	private String email;
	private Long managerId;
	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	@Version
	private Integer version;
	@TableLogic
	@TableField(select = false)
	private Integer deleted;
}