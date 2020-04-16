package com.example.demo.model;

public class TblAlarmcontact {
    private String uiduserid;

    private Integer icontacttype;

    private String strcontactnumber;

    public String getUiduserid() {
        return uiduserid;
    }

    public void setUiduserid(String uiduserid) {
        this.uiduserid = uiduserid == null ? null : uiduserid.trim();
    }

    public Integer getIcontacttype() {
        return icontacttype;
    }

    public void setIcontacttype(Integer icontacttype) {
        this.icontacttype = icontacttype;
    }

    public String getStrcontactnumber() {
        return strcontactnumber;
    }

    public void setStrcontactnumber(String strcontactnumber) {
        this.strcontactnumber = strcontactnumber == null ? null : strcontactnumber.trim();
    }
}