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
import javax.faces.context.FacesContext;
/**
 *
 * @author Nilesh Singh
 */

@ManagedBean
@SessionScoped
public class EmpTaskAssignBean1 implements java.io.Serializable{
DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
    Map<String,String>emptypeMap;
    Map<String,String>empdeptMap;
    String selectedEmpType,selectedDeptId,selectedEmpName;
    /**
     * Creates a new instance of EmpTaskAssignBean
     */
    public EmpTaskAssignBean1() {
    }
    
      public void genEmptype()
    {
        emptypeMap = new HashMap<>();
        emptypeMap.clear();
        String sql1 = "SELECT id,emp_type FROM `tb_emp_type`;";
        System.out.println("Query for selecting Employee type"+sql1);
        ArrayList<String[]> queryData = dbHandler.getDataQuery(sql1, 100);
        for (String[] data : queryData) {
            emptypeMap.put(data[1], data[0]);
        }
       
        String Statusmsg="employee type map updated";
        FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        queryData = null;
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

}
