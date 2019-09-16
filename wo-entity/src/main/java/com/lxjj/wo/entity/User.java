package com.lxjj.wo.entity;

import java.util.Date;

public class User {
    private String fid;

    private String name;

    private String account;

    private String password;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    private String version;

    private String remarks;

    public User(String fid, String name, String account, String password, Date createTime, String createPerson, Date updateTime, String updatePerson, String version, String remarks) {
        this.fid = fid;
        this.name = name;
        this.account = account;
        this.password = password;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
        this.version = version;
        this.remarks = remarks;
    }

    public User() {
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
