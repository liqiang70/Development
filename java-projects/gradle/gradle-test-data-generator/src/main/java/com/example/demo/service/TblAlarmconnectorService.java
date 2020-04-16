package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TblAlarmconnectorMapper;
import com.example.demo.model.TblAlarmconnector;

@Service
public class TblAlarmconnectorService {
	@Autowired
	TblAlarmconnectorMapper tblAlarmconnectorMapper;

	public TblAlarmconnector selectById(Long id) {
		return tblAlarmconnectorMapper.selectByPrimaryKey(id);
	}

	public TblAlarmconnector selectByUidUserid(String uiduserid) {
		return tblAlarmconnectorMapper.selectByUidUserid(uiduserid);
	}

	int insert(TblAlarmconnector tblAlarmconnector) {
		return tblAlarmconnectorMapper.insert(tblAlarmconnector);
	}
}
