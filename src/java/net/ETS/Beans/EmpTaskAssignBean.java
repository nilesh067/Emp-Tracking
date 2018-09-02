/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
import com.database.jdbc.util.DBHandler;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import net.ETS.util.Beans.empTask;

/**
 *
 * @author Nilesh Singh
 */
@ManagedBean
@SessionScoped
public class EmpTaskAssignBean {
DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
    LoginBean loginBeanObj=null;
    Map<String,String>emptypeMap;
    Map<String,String>empdeptMap;
    Map<String,String>empnameMap;
    String selectedEmpType,selectedDeptId,selectedEmpName,taskName,taskDescp;
    String employee_id,assigntaskName,taskStatus,assignTime,lastUpdate;
    String employeeType,employeeDept,employeeID;
    List<empTask> records;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public EmpTaskAssignBean() {
    }
    public void genEmptype()
    {
        emptypeMap = new HashMap<>();
        emptypeMap.clear();
        String sql1 = "SELECT id,emp_type FROM `tb_emp_type`;";
        System.out.println("Query for selecting Employee type "+sql1);
        ArrayList<String[]> queryData = dbHandler.getDataQuery(sql1, 100);
        for (String[] data : queryData) {
            emptypeMap.put(data[1], data[0]);
            System.out.println(" name "+data[1]+" value "+data[0]);
        }
       
        queryData = null;
       // FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", "Employee type selected");
       // FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      public void genEmpdept(){
        System.out.println("gemEMpDept call");
        empdeptMap=new HashMap<>();
        empdeptMap.clear();
        String Sql="SELECT id,`dept_name` FROM `tb_ets_dept` WHERE `emp_type`="+this.getSelectedEmpType()+";";
         System.out.println("Query for selecting Employee Dept"+Sql);
         ArrayList<String []> querydata=dbHandler.getDataQuery(Sql, 100);
         for(String [] data:querydata){
             empdeptMap.put(data[1],data[0]);
         }
           FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", "Employee Dept List updated");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      public void genEmpName(){
        System.out.println("gemEMpDept call");
        System.out.println("emp type"+this.getSelectedEmpType()+"  emp dept "+this.getSelectedDeptId());
        empnameMap=new HashMap<>();
        empnameMap.clear();
        String Sql="SELECT id,empName FROM `tb_ets_emp_reg` WHERE emptype="+this.getSelectedEmpType()+" AND empDept="+this.getSelectedDeptId()+";";
         System.out.println("Query for selecting Employee Dept"+Sql);
         ArrayList<String []> querydata=dbHandler.getDataQuery(Sql, 100);
         for(String [] data:querydata){
             empnameMap.put(data[1],data[0]);
         }
           FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", "Employee Name List Updated");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
      
      public void genEmpDetails(){
         this.setEmployeeType(this.getSelectedEmpType());
         this.setEmployeeDept(this.getSelectedDeptId());
         this.setEmployeeID(this.getSelectedEmpName());
          System.out.println("Emp type::: "+this.getSelectedEmpType()+" empDeptid:: "+this.getSelectedDeptId()+" empId "+this.getSelectedEmpName());
            System.out.println("Emp type::: "+this.getEmployeeType()+" empDeptid:: "+this.getEmployeeDept()+" empId "+this.getEmployeeID());
    }
      public void submit(){
          String StatusMsg=null;
          
          System.out.println("EMP TYPE "+this.getSelectedEmpType()+" EMP DEPT "+this.getSelectedDeptId()+" EMP ID "+this.getSelectedEmpName());
          String sql="INSERT INTO `tb_ets_assigned_task` (`emp_type`,`dept_id`,`emp_id`,`task_name`,`task_descp`,`assigned_by`,`status`,`assigned_time`,`last_update`,`employee_msg`) values ("+this.getEmployeeType()+","+this.getEmployeeDept()+","+this.getEmployeeID()+",'"+this.getTaskName()+"','"+this.getTaskDescp()+"','Admin',1,NOW(),NOW(),'');";
          System.out.println(" admin dashboard task assign submit() method called :: Query "+sql);
          int result=dbHandler.getUpdateQuery(sql);
          if(result>0){
              System.out.println(" Query for assign task executed successfully query result "+result); 
              StatusMsg="Task assign Successfully ";
          }
          else{
              System.out.println(" Query for assign task not executed successfully query result "+result); 
              StatusMsg="Something Missing ";
          }
          FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", StatusMsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
     empdeptMap.clear();
     empnameMap.clear();
     
      }
      public void employeeAssignTask(){
          records=new ArrayList<empTask>();
          records.clear();
          String StatusMsg=null;
          empTask newRecords=null;
        String sql="SELECT `emp_id`,`task_name`,`status`,`assigned_time`,`last_update` FROM `tb_ets_assigned_task` WHERE `emp_type`="+this.getSelectedEmpType()+" AND `dept_id`="+this.getSelectedDeptId()+" AND `emp_id`="+this.getSelectedEmpName()+";";  
        System.out.println(" admin dashboard task assign ststus method called :: Query "+sql); 
        ArrayList<String []> queryData=dbHandler.getDataQuery(sql, 30);
         for(String data[]:queryData){
             newRecords=new empTask();
             newRecords.setEmpID(data[0]);
             newRecords.setTaskName(data[1]);
             newRecords.setStatus(data[2]);
             newRecords.setAssignedTime(data[3]);
             newRecords.setLastUpdate(data[4]);
              records.add(newRecords);
                     newRecords=null;
                     }
         if(!queryData.isEmpty()){
              System.out.println(" Query for assign task ststus executed successfully"); 
              StatusMsg="Task assign status loaded Successfully ";
          }
          else{
              System.out.println(" Query for assign task  ststus not executed successfully query result "); 
              StatusMsg="Something Missing ";
          }
          FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", StatusMsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
     empdeptMap.clear();
     empnameMap.clear();
      }
    public Map<String, String> getEmptypeMap() {
        return emptypeMap;
    }

    public void setEmptypeMap(Map<String, String> emptypeMap) {
        this.emptypeMap = emptypeMap;
    }

    public Map<String, String> getEmpdeptMap() {
        return empdeptMap;
    }

    public void setEmpdeptMap(Map<String, String> empdeptMap) {
        this.empdeptMap = empdeptMap;
    }

    public String getSelectedEmpType() {
        return selectedEmpType;
    }

    public void setSelectedEmpType(String selectedEmpType) {
        this.selectedEmpType = selectedEmpType;
    }

    public String getSelectedDeptId() {
        return selectedDeptId;
    }

    public void setSelectedDeptId(String selectedDeptId) {
        this.selectedDeptId = selectedDeptId;
    }

    public String getSelectedEmpName() {
        return selectedEmpName;
    }

    public void setSelectedEmpName(String selectedEmpName) {
        this.selectedEmpName = selectedEmpName;
    }

    public Map<String, String> getEmpnameMap() {
        return empnameMap;
    }

    public void setEmpnameMap(Map<String, String> empnameMap) {
        this.empnameMap = empnameMap;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescp() {
        return taskDescp;
    }

    public void setTaskDescp(String taskDescp) {
        this.taskDescp = taskDescp;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getAssigntaskName() {
        return assigntaskName;
    }

    public void setAssigntaskName(String assigntaskName) {
        this.assigntaskName = assigntaskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<empTask> getRecords() {
        return records;
    }

    public void setRecords(List<empTask> records) {
        this.records = records;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeDept() {
        return employeeDept;
    }

    public void setEmployeeDept(String employeeDept) {
        this.employeeDept = employeeDept;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

}
