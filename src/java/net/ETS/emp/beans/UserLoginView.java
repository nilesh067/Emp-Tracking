/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.emp.beans;

import com.database.jdbc.util.DBHandler;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Rishi Tyagi
 */
@ManagedBean
@SessionScoped
public class UserLoginView  implements Serializable{
 DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
  
    String username,empDept,employee_Type,empDEG,empName;
    
    private String password;


    

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }

    public String getEmployee_Type() {
        return employee_Type;
    }

    public void setEmployee_Type(String employee_Type) {
        this.employee_Type = employee_Type;
    }

    public String getEmpDEG() {
        return empDEG;
    }

    public void setEmpDEG(String empDEG) {
        this.empDEG = empDEG;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void login(ActionEvent event) throws IOException {
        RequestContext context = RequestContext.getCurrentInstance();
    }

    public void LOGINMETHOD() throws IOException {
        System.out.println(" Login Submit method  invoke:::");
        String queryLogin="SELECT user_name,emp_dept,emp_type,emp_deg FROM `tb_emp_login_details` WHERE user_name='"+username+"' AND PASSWORD='"+password+"' AND STATUS=1;";
        ArrayList<String[]>logindetail=dbHandler.getDataQuery(queryLogin, 1);
        System.out.println(">>>> Submit method query "+queryLogin);
 if (!logindetail.isEmpty()) {
     
            String[] loginObj=logindetail.get(0);
            System.out.println(">>>> Found User "+loginObj[0]);
            
            this.setUsername(loginObj[0]);
            this.setEmpName(loginObj[0]);
           this.setEmpDept(Integer.parseInt(loginObj[1])==1?"Engineeging":Integer.parseInt(loginObj[2])==2?"Operation L1":Integer.parseInt(loginObj[2])==3?"Operation L2":Integer.parseInt(loginObj[2])==4?"Product And Service Delivery":Integer.parseInt(loginObj[2])==5?"Human Resource":"Others");
           this.setEmployee_Type(loginObj[2]);
           this.setEmpDEG(loginObj[3]);
//           this.setPortalId(loginObj[3]);
//            this.setPortalName(loginObj[4]);
 //           this.setServiceId(loginObj[5]);
//            this.setServiceName(loginObj[6]);
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Emp-Tracking/ETS/ui/Employee/menupage.xhtml");
        
        }
 else{
 
 String Statusmsg="Invalid User Name or password";
         FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
 
 }
    }
    public void facesMsg()
    {   String Statusmsg="Welcome to Employee Pannel";
         FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public UserLoginView() {
    }

}