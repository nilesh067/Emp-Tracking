/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.Beans;

import com.database.jdbc.util.DBHandler;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import net.ETS.util.Beans.EmpTrackerBean;

/**
 *
 * @author Nilesh Singh
 */
@ManagedBean
@SessionScoped
public class EmpTrack implements java.io.Serializable{
    DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
String selectedName,selectedid;
String empName;
    String empId;
    String empDept;
    String empMobile;
    String empLocation;
    
    /**
     * Creates a new instance of EmpTrack
     */
    List<EmpTrackerBean> records;
    public EmpTrack() {
       
        
        
    }

    public void submit(String trackMode){
         records = new ArrayList<EmpTrackerBean>();
        records.clear();
        String Statusmsg=null;
    EmpTrackerBean newRecord=null;
    if (trackMode.equalsIgnoreCase("name")) {       
        String Sql="SELECT id,empName,empDept,empMobile,`empDeptLoc` FROM `tb_ets_emp_reg` WHERE `empName` like '"+this.getSelectedName()+"';";
       String Sql2="SELECT id,empName,empDept,empMobile,`empDeptLoc` FROM tb_ets_emp_reg WHERE `empName` LIKE \"%"+this.selectedName+"%\";";
        System.out.println("Employee tracking Submit method call::: Track employee by name query "+Sql2+" selected emp Nmae"+this.getSelectedName());
        ArrayList<String[]> queryData = dbHandler.getDataQuery(Sql2, 100);
        for (String[] data : queryData) {
             newRecord = new EmpTrackerBean(); 
           newRecord.setEmp_id(data[0]);
            newRecord.setEmp_name(data[1]);
            newRecord.setEmp_dept(Integer.parseInt(data[2])==1?"Engineeging":Integer.parseInt(data[2])==2?"Operation L1":Integer.parseInt(data[2])==3?"Operation L2":Integer.parseInt(data[2])==4?"Product And Service Delivery":Integer.parseInt(data[2])==5?"Human Resource":"Others");
            newRecord.setEmp_mobile(data[3]);
            newRecord.setEmp_location("Ground floor");
            System.out.println("data"+data[0]+" "+data[1]);
            records.add(newRecord);
       newRecord=null;
        }
        if(!queryData.isEmpty()){
        Statusmsg="Employee Successfully Track  \n found result "+queryData.size()+"";
        }
        else{
        Statusmsg="Something missing";
        }
        FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    else if(trackMode.equalsIgnoreCase("id")){
        String Sql="SELECT id,empName,empDept,empMobile,`empDeptLoc` FROM `tb_ets_emp_reg` WHERE `id`="+this.getSelectedid()+";";
    System.out.println("Employee tracking Submit method call ::: track employee by id query "+Sql+" selected emp ID "+this.getSelectedid());
        ArrayList<String[]> queryData = dbHandler.getDataQuery(Sql, 100);
        records.clear();
        for (String[] data : queryData) {
             newRecord = new EmpTrackerBean(); 
           newRecord.setEmp_id(data[0]);
            newRecord.setEmp_name(data[1]);
            newRecord.setEmp_dept(Integer.parseInt(data[2])==1?"Engineeging":Integer.parseInt(data[2])==2?"Operation L1":Integer.parseInt(data[2])==3?"Operation L2":Integer.parseInt(data[2])==4?"Product And Service Delivery":Integer.parseInt(data[2])==5?"Human Resource":"Others");
            newRecord.setEmp_mobile(data[3]);
            newRecord.setEmp_location("Ground floor");
            System.out.println("data"+data[0]+" "+data[1]);
            records.add(newRecord);
       newRecord=null;
        }
         if(!queryData.isEmpty()){
        Statusmsg="Employee Successfully Track  \n found result "+queryData.size()+"";
        }
        else{
        Statusmsg="Something missing";
        }
        FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    else{}
    }
public void submit2(){
//         records = new ArrayList<EmpTrackerBean>();
//        records.clear();
//    EmpTrackerBean newRecord=null;
//        String Sql="SELECT id,empName,empDept,empMobile,`empDeptLoc` FROM `tb_ets_emp_reg` WHERE `empName`='"+this.getSelectedName()+"';";
//       System.out.println("Employee tracking Submit method call::: query"+Sql+" selected emp Nmae"+this.getSelectedName());
//        ArrayList<String[]> queryData = dbHandler.getDataQuery(Sql, 100);
//        for (String[] data : queryData) {
//             newRecord = new EmpTrackerBean(); 
//           newRecord.setEmp_id(data[0]);
//            newRecord.setEmp_name(data[1]);
//            newRecord.setEmp_dept(Integer.parseInt(data[2])==1?"Engineeging":Integer.parseInt(data[2])==2?"Operation L1":Integer.parseInt(data[2])==3?"Operation L2":Integer.parseInt(data[2])==4?"Product And Service Delivery":Integer.parseInt(data[2])==5?"Human Resource":"Others");
//            newRecord.setEmp_mobile(data[3]);
//            newRecord.setEmp_location("Ground floor");
//            records.add(newRecord);
//       newRecord=null;
//        }
       
    }
    public List<EmpTrackerBean> getRecords() {
        return records;
    }

    public void setRecords(List<EmpTrackerBean> records) {
        this.records = records;
    }

    public String getSelectedName() {
        return selectedName;
    }

    public void setSelectedName(String selectedName) {
        this.selectedName = selectedName;
    }

    public String getSelectedid() {
        return selectedid;
    }

    public void setSelectedid(String selectedid) {
        this.selectedid = selectedid;
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

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpLocation() {
        return empLocation;
    }

    public void setEmpLocation(String empLocation) {
        this.empLocation = empLocation;
    }



}
