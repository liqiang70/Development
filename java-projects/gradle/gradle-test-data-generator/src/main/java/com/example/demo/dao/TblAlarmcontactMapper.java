package com.example.demo.dao;

import com.example.demo.model.TblAlarmcontact;

public interface TblAlarmcontactMapper {
    int insert(TblAlarmcontact record);

    int insertSelective(TblAlarmcontact record);
}