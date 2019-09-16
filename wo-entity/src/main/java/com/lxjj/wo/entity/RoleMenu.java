package com.lxjj.wo.entity;

import java.util.Date;

public class RoleMenu {
    private String roleId;

    private String menuId;

    private String status;

    private Date createTime;

    private String createPerson;

    private Date updateTime;

    private String updatePerson;

    private String version;

    private String remarks;

    public RoleMenu(String roleId, String menuId, String status, Date createTime, String createPerson, Date updateTime, String updatePerson, String version, String remarks) {
        this.roleId = roleId;
        this.menuId = menuId;
        this.status = status;
        this.createTime = createTime;
        this.createPerson = createPerson;
        this.updateTime = updateTime;
        this.updatePerson = updatePerson;
        this.version = version;
        this.remarks = remarks;
    }

    public RoleMenu() {
        super();
    }

    /**
     * @return role_id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return menu_id
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * @param menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
