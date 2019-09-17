package com.lxjj.wo.entity;

import java.util.Date;

public class OrderMain {
    private String fid;

    private String cname;

    private String cphone;

    private String caddress;

    private Date sendTime;

    private String status;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    private String version;

    private String remarks;

    public OrderMain(String fid, String cname, String cphone, String caddress, Date sendTime, String status, Date createTime, String createPerson, Date updateTime, String updatePerson, String version, String remarks) {
        this.fid = fid;
        this.cname = cname;
        this.cphone = cphone;
        this.caddress = caddress;
        this.sendTime = sendTime;
        this.status = status;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
        this.version = version;
        this.remarks = remarks;
    }

    public OrderMain() {
        super();
    }

    /**
     * @return fid
     */
    public String getFid() {
        return fid;
    }

    /**
     * @param fid
     */
    public void setFid(String fid) {
        this.fid = fid;
    }

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return cphone
     */
    public String getCphone() {
        return cphone;
    }

    /**
     * @param cphone
     */
    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    /**
     * @return caddress
     */
    public String getCaddress() {
        return caddress;
    }

    /**
     * @param caddress
     */
    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    /**
     * @return send_time
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return create_person
     */
    public String getCreatePerson() {
        return createPerson;
    }

    /**
     * @param createPerson
     */
    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return update_person
     */
    public String getUpdatePerson() {
        return updatePerson;
    }

    /**
     * @param updatePerson
     */
    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    /**
     * @return version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
