package net.ETS.util.Beans;

import java.io.Serializable;

public class UserRoleBean implements Serializable {
    int roleId;
    String roleName;
    String roleDescp;
    int status;
    String createdOn;
    String lastUpdate;
    String homePage;
    
    public UserRoleBean(){}

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescp() {
        return roleDescp;
    }

    public void setRoleDescp(String roleDescp) {
        this.roleDescp = roleDescp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }
    
    
}
