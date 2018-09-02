/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.Beans;
import com.database.jdbc.util.DBHandler;
import java.util.*;

/**
 *
 * @author Nilesh Singh
 */
public class EmpInfoBean implements java.io.Serializable {
   DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
    String empName;
    String empId;
    String empDept;
    String empType;
    String empAddress;
    String empGender;
    String empMerital;
    String empMobile;
    String empDOB;
    String empDOJ;
    String empHighQualification;
    String empNationality;
    String empEmail;
    String empLocation;
    String empDeg;
    Map<String, String> emptypeMap;
    

    
    
    
     public void genStory_map()
    {
        emptypeMap = new HashMap<>();
        String sql1 = "SELECT id,emp_type FROM `tb_emp_type`;";
        System.out.println("Query for selecting Employee type"+sql1);
        ArrayList<String[]> queryData = dbHandler.getDataQuery(sql1, 100);
        for (String[] data : queryData) {
            emptypeMap.put(data[1], data[0]);
        }
       
        queryData = null;
    }
    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpGender() {
        return empGender;
    }

    public void setEmpGender(String empGender) {
        this.empGender = empGender;
    }

    public String getEmpMerital() {
        return empMerital;
    }

    public void setEmpMerital(String empMerital) {
        this.empMerital = empMerital;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpDOB() {
        return empDOB;
    }

    public void setEmpDOB(String empDOB) {
        this.empDOB = empDOB;
    }

    public String getEmpDOJ() {
        return empDOJ;
    }

    public void setEmpDOJ(String empDOJ) {
        this.empDOJ = empDOJ;
    }

    public String getEmpHighQualification() {
        return empHighQualification;
    }

    public void setEmpHighQualification(String empHighQualification) {
        this.empHighQualification = empHighQualification;
    }

    public String getEmpNationality() {
        return empNationality;
    }

    public void setEmpNationality(String empNationality) {
        this.empNationality = empNationality;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpLocation() {
        return empLocation;
    }

    public void setEmpLocation(String empLocation) {
        this.empLocation = empLocation;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmpDeg() {
        return empDeg;
    }

    public void setEmpDeg(String empDeg) {
        this.empDeg = empDeg;
    }
    
}
