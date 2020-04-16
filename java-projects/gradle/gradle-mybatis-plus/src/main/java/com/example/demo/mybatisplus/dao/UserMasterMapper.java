package com.example.demo.mybatisplus.dao;

import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.mybatisplus.entity.UserMaster;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月24日 下午10:42:25
 */

@DS("master")
public interface UserMasterMapper extends BaseMapper<UserMaster> {
	List<UserMaster> selectAll();
}
