/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.util.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Nilesh Singh
 */
@ManagedBean
@RequestScoped
public class EmpTrackerBean implements java.io.Serializable{

    /**
     * Creates a new instance of EmpTrackerBean
     */
    String emp_id;
    String emp_name;
    String emp_dept;
    String emp_mobile;
    String emp_location;
    public EmpTrackerBean() {
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_dept() {
        return emp_dept;
    }

    public void setEmp_dept(String emp_dept) {
        this.emp_dept = emp_dept;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_location() {
        return emp_location;
    }

    public void setEmp_location(String emp_location) {
        this.emp_location = emp_location;
    }
    
}
