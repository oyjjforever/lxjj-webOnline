package com.lxjj.wo.entity;

import java.util.Date;

public class Goods {
    private String fid;

    private String name;

    private String type;

    private String color;

    private String supplier;

    private String format;

    private Double price;

    private String status;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    private String version;

    private String remarks;

    public Goods(String fid, String name, String type, String color, String supplier, String format, Double price, String status, Date createTime, String createPerson, Date updateTime, String updatePerson, String version, String remarks) {
        this.fid = fid;
        this.name = name;
        this.type = type;
        this.color = color;
        this.supplier = supplier;
        this.format = format;
        this.price = price;
        this.status = status;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
        this.version = version;
        this.remarks = remarks;
    }

    public Goods() {
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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
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
