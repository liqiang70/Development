package com.example.demo.dao;

import com.example.demo.model.TblAlarmconnector;

public interface TblAlarmconnectorMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(TblAlarmconnector record);

    int insertSelective(TblAlarmconnector record);

    TblAlarmconnector selectByPrimaryKey(Long tid);

    int updateByPrimaryKeySelective(TblAlarmconnector record);

    int updateByPrimaryKey(TblAlarmconnector record);

	TblAlarmconnector selectByUidUserid(String uiduserid);
}