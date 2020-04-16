package com.example.demo.service;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.TblAlarmconnector;
import com.example.demo.model.TblAlarmcontact;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TblAlarmconnectorServiceTests {

	@Autowired
	private TblAlarmconnectorService tblAlarmconnectorService;
	@Autowired
	private TblAlarmcontactService tblAlarmcontactService;

	@Test
	public void getUser() {
		TblAlarmconnector tblAlarmconnector = tblAlarmconnectorService
				.selectByUidUserid("fff7-94c26b8af61aaa478a7-auto-00001");
		Assert.assertThat(tblAlarmconnector.getStrusername(), is("rgl"));
		TblAlarmconnector tblAlarmconnector2 = tblAlarmconnectorService.selectById(4958L);
		Assert.assertThat(tblAlarmconnector2.getStrusername(), is("rgl"));
	}

	// @Test
	public void inserTestUsers() throws CloneNotSupportedException {
		TblAlarmconnector tblAlarmconnector = tblAlarmconnectorService.selectByUidUserid("fff7-94c26b8af61aaa478a7");
		Assert.assertThat(tblAlarmconnector.getStrusername(), is("rgl"));
		for (int i = 0; i < 10; i++) {
			String raw = "00000" + i;
			String index = raw.substring(raw.length() - 5);
			TblAlarmconnector tblAlarmconnector2 = tblAlarmconnector.clone();
			tblAlarmconnector2.setUiduserid(tblAlarmconnector2.getUiduserid() + "-auto-" + index);
			tblAlarmconnector2.setTid(tblAlarmconnector2.getTid() + 1000 + i);
			tblAlarmconnectorService.insert(tblAlarmconnector2);
		}

		// delete from tbl_alarmconnector t where t.uiduserid like '%auto%'

	}

	// @Test
	public void insertUserContact_mobile() {
		TblAlarmcontact tblAlarmcontact = new TblAlarmcontact();

		for (int i = 0; i < 20000; i++) {
			String raw = "00000" + i;
			String index = raw.substring(raw.length() - 5);

			tblAlarmcontact.setUiduserid("ece7-4ec8c3dbc61d9a07b72-auto-" + index);
			tblAlarmcontact.setIcontacttype(4);
			tblAlarmcontact.setStrcontactnumber("1881003443" + "-auto-" + index);

			tblAlarmcontactService.insert(tblAlarmcontact);
			System.out.println("step1: " + index);
		}

		// select count(*) from tbl_alarmcontact t where t.strcontactnumber like
		// '%auto%'
	}

	// @Test
	public void insertUserContact_mail() {
		TblAlarmcontact tblAlarmcontact = new TblAlarmcontact();

		for (int i = 0; i < 20000; i++) {
			String raw = "00000" + i;
			String index = raw.substring(raw.length() - 5);

			tblAlarmcontact.setUiduserid("ece7-4ec8c3dbc61d9a07b72-auto-" + index);
			tblAlarmcontact.setIcontacttype(3);
			tblAlarmcontact.setStrcontactnumber("rgl@jianq.com" + "-auto-" + index);

			tblAlarmcontactService.insert(tblAlarmcontact);
			System.out.println("step2: " + index);
		}

		// select count(*) from tbl_alarmcontact t where t.strcontactnumber like
		// '%auto%'
	}

	// @Test
	public void insertUserContact_phone() {
		TblAlarmcontact tblAlarmcontact = new TblAlarmcontact();

		for (int i = 0; i < 20000; i++) {
			String raw = "00000" + i;
			String index = raw.substring(raw.length() - 5);

			tblAlarmcontact.setUiduserid("ece7-4ec8c3dbc61d9a07b72-auto-" + index);
			tblAlarmcontact.setIcontacttype(5);
			tblAlarmcontact.setStrcontactnumber("0755-2287912" + "-auto-" + index);

			tblAlarmcontactService.insert(tblAlarmcontact);
			System.out.println("step3: " + index);
		}

		// select count(*) from tbl_alarmcontact t where t.strcontactnumber like
		// '%auto%'
	}
}
