package com.example.demo.mybatisplus.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.mybatisplus.entity.UserSlave;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ThinkPad Date:2020年3月24日 下午10:42:25
 */

@DS("slave")
public interface UserSlaveMapper extends BaseMapper<UserSlave> {
}
