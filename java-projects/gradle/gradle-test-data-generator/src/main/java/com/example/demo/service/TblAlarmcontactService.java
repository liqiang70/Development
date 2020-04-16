package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.TblAlarmcontactMapper;
import com.example.demo.model.TblAlarmcontact;

@Service
public class TblAlarmcontactService {
	@Autowired
	TblAlarmcontactMapper tblAlarmcontactMapper;

	int insert(TblAlarmcontact tblAlarmcontact) {
		return tblAlarmcontactMapper.insert(tblAlarmcontact);
	}
}
