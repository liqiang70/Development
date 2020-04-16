package com.example.demo.model;

import java.util.Date;

public class TblAlarmconnector implements Cloneable {
	private Long tid;

	private String uiduserid;

	private String strpwd;

	private String struserdes;

	private Long iupdatetime;

	private String strusername;

	private String uidroleid;

	private String token;

	private Integer imodifydeptbyclient;

	private Integer sstatus;

	private String strmemo;

	private String stremail;

	private Integer iispwdautosent;

	private String strunit;

	private String strlocation;

	private Integer iispwssentsuc;

	private Integer imodifypwdfirst;

	private Date dtmodifypwdtime;

	private Date dtendtime;

	private Integer imaxbindingmaccount;

	private String strdn;

	private String struserposition;

	private Integer ifirstloginflag;

	private String strusernamespell;

	private String ldappwdlastset;

	private Integer iorder;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	public String getUiduserid() {
		return uiduserid;
	}

	public void setUiduserid(String uiduserid) {
		this.uiduserid = uiduserid == null ? null : uiduserid.trim();
	}

	public String getStrpwd() {
		return strpwd;
	}

	public void setStrpwd(String strpwd) {
		this.strpwd = strpwd == null ? null : strpwd.trim();
	}

	public String getStruserdes() {
		return struserdes;
	}

	public void setStruserdes(String struserdes) {
		this.struserdes = struserdes == null ? null : struserdes.trim();
	}

	public Long getIupdatetime() {
		return iupdatetime;
	}

	public void setIupdatetime(Long iupdatetime) {
		this.iupdatetime = iupdatetime;
	}

	public String getStrusername() {
		return strusername;
	}

	public void setStrusername(String strusername) {
		this.strusername = strusername == null ? null : strusername.trim();
	}

	public String getUidroleid() {
		return uidroleid;
	}

	public void setUidroleid(String uidroleid) {
		this.uidroleid = uidroleid == null ? null : uidroleid.trim();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
	}

	public Integer getImodifydeptbyclient() {
		return imodifydeptbyclient;
	}

	public void setImodifydeptbyclient(Integer imodifydeptbyclient) {
		this.imodifydeptbyclient = imodifydeptbyclient;
	}

	public Integer getSstatus() {
		return sstatus;
	}

	public void setSstatus(Integer sstatus) {
		this.sstatus = sstatus;
	}

	public String getStrmemo() {
		return strmemo;
	}

	public void setStrmemo(String strmemo) {
		this.strmemo = strmemo == null ? null : strmemo.trim();
	}

	public String getStremail() {
		return stremail;
	}

	public void setStremail(String stremail) {
		this.stremail = stremail == null ? null : stremail.trim();
	}

	public Integer getIispwdautosent() {
		return iispwdautosent;
	}

	public void setIispwdautosent(Integer iispwdautosent) {
		this.iispwdautosent = iispwdautosent;
	}

	public String getStrunit() {
		return strunit;
	}

	public void setStrunit(String strunit) {
		this.strunit = strunit == null ? null : strunit.trim();
	}

	public String getStrlocation() {
		return strlocation;
	}

	public void setStrlocation(String strlocation) {
		this.strlocation = strlocation == null ? null : strlocation.trim();
	}

	public Integer getIispwssentsuc() {
		return iispwssentsuc;
	}

	public void setIispwssentsuc(Integer iispwssentsuc) {
		this.iispwssentsuc = iispwssentsuc;
	}

	public Integer getImodifypwdfirst() {
		return imodifypwdfirst;
	}

	public void setImodifypwdfirst(Integer imodifypwdfirst) {
		this.imodifypwdfirst = imodifypwdfirst;
	}

	public Date getDtmodifypwdtime() {
		return dtmodifypwdtime;
	}

	public void setDtmodifypwdtime(Date dtmodifypwdtime) {
		this.dtmodifypwdtime = dtmodifypwdtime;
	}

	public Date getDtendtime() {
		return dtendtime;
	}

	public void setDtendtime(Date dtendtime) {
		this.dtendtime = dtendtime;
	}

	public Integer getImaxbindingmaccount() {
		return imaxbindingmaccount;
	}

	public void setImaxbindingmaccount(Integer imaxbindingmaccount) {
		this.imaxbindingmaccount = imaxbindingmaccount;
	}

	public String getStrdn() {
		return strdn;
	}

	public void setStrdn(String strdn) {
		this.strdn = strdn == null ? null : strdn.trim();
	}

	public String getStruserposition() {
		return struserposition;
	}

	public void setStruserposition(String struserposition) {
		this.struserposition = struserposition == null ? null : struserposition.trim();
	}

	public Integer getIfirstloginflag() {
		return ifirstloginflag;
	}

	public void setIfirstloginflag(Integer ifirstloginflag) {
		this.ifirstloginflag = ifirstloginflag;
	}

	public String getStrusernamespell() {
		return strusernamespell;
	}

	public void setStrusernamespell(String strusernamespell) {
		this.strusernamespell = strusernamespell == null ? null : strusernamespell.trim();
	}

	public String getLdappwdlastset() {
		return ldappwdlastset;
	}

	public void setLdappwdlastset(String ldappwdlastset) {
		this.ldappwdlastset = ldappwdlastset == null ? null : ldappwdlastset.trim();
	}

	public Integer getIorder() {
		return iorder;
	}

	public void setIorder(Integer iorder) {
		this.iorder = iorder;
	}

	@Override
	public TblAlarmconnector clone() throws CloneNotSupportedException {
		return (TblAlarmconnector) super.clone();
	}
}