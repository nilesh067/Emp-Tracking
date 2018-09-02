/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.Beans;

import com.database.jdbc.util.DBHandler;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;
import net.ETS.Beans.*;
import net.ETS.util.Mail;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Nilesh Singh
 */
@ManagedBean
@SessionScoped
public class EmpRegBean {
 DBHandler dbHandler = new DBHandler("java:comp/env/ETSusrdb", true);
    String empName;
    String empFName;
    String empLName;
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
    Map<String,String> empdeptMap;
    Map<String,String> ALLDept;
    String selectedDEPTid;
    List<EmpInfoBean> empList;
    /**
     * Creates a new instance of EmpRegBean
     */
    public EmpRegBean() {
        empList=new ArrayList<EmpInfoBean>();
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
       
        queryData = null;
    }
    public void genEmpdept(){
        System.out.println("gemEMpDept call");
        empdeptMap=new HashMap<>();
        empdeptMap.clear();
        String Sql="SELECT id,`dept_name` FROM `tb_ets_dept` WHERE `emp_type`="+this.getEmpType()+";";
         System.out.println("Query for selecting Employee Dept"+Sql);
         ArrayList<String []> querydata=dbHandler.getDataQuery(Sql, 100);
         for(String [] data:querydata){
             empdeptMap.put(data[1],data[0]);
         }
    }
    public void submit() throws ParseException, IOException
    {
        String DOJ;
            String DOB;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
            Date date1 = null;
            Date date2 = null;

            if (getEmpDOB() == null && getEmpDOJ() == null) {
                System.out.println("Dates are NULL");
            } else {
                DOB = getEmpDOB().toString();
                DOJ = getEmpDOJ().toString();
                date1 = (Date) formatter.parse(DOJ);
                date2 = (Date) formatter.parse(DOB);
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String LastDate = dateFormat.format(date);
            String dateStr = null;
            String EMPDOJ=null,EMPDOB=null;
            if (getEmpDOB()== null && getEmpDOJ()== null) {
               // dateStr = "a.download_date like '" + LastDate + "%'";
            } else {
            EMPDOB=simpleDateFormat.format(date2);
            EMPDOJ=simpleDateFormat.format(date1);
            // dateStr = "a.download_date BETWEEN '" + simpleDateFormat.format(date1) + "' AND '" + simpleDateFormat.format(date2) + "'";
            }

        System.out.println("Submit method called");
        String Statusmsg;
        String Sql="INSERT INTO `tb_ets_emp_reg` (`empName`,`empDept`,`empGender`,`empSsn`,`empDeptLoc`,`empDOB`,`empDOJ`,`empDesignation`,`emptype`,`empMobile`,`empEmail`,`empMeritorial`) VALUES('"+this.getEmpFName()+" "+this.getEmpLName()+"','"+this.getEmpDept()+"','"+this.getEmpGender()+"','0000','gf','"+EMPDOB+"','"+EMPDOJ+"','"+this.getEmpDeg()+"',"+this.getEmpType()+",'"+this.getEmpMobile()+"','"+this.getEmpEmail()+"','"+this.getEmpMerital()+"');";
        System.out.println("Query for register new Employee "+Sql);
        int result=dbHandler.getUpdateQuery(Sql);
        System.out.println("Query Result :: "+result);
       if(result>=1){ 
          String sb="INSERT INTO `tb_emp_login_details` (`user_name`,`password`,`emp_id`,`emp_dept`,`emp_type`,`emp_deg`,`emp_email`) SELECT `empName`,`empMobile`,`id`,`empDept`,`emptype`,`empDesignation`,`empEmail` FROM `tb_ets_emp_reg` WHERE `empEmail`='"+this.getEmpEmail()+"';";
          int result1=dbHandler.getUpdateQuery(sb);
          if(result>0){
             String Subject="Employee Tracking System";
             String to=this.getEmpEmail();
             String text="Hi \n "+this.getEmpFName()+" you are registered with us\n your login  details for ETS employee panel  are as follows \n User name="+this.getEmpFName()+" "+this.getEmpLName()+"\n Password="+this.getEmpMobile()+" \n ETS employee panel link is http://localhost:8081/Emp-Tracking/ETS/ui/Employee/loginpage.xhtml \n Please go and check your information";
             Mail.mailSender(to, Subject, text);
          }
          Statusmsg="Employee Record Successfully  Inserted and login details send to email";}
       else{
           Statusmsg="there Is Something Missing";
       }
       
//     empFName=null;
//     empLName=null;
//    empId=null;
//     empDept=null;
//     empType=null;
//     empAddress=null;
//     empGender=null;
//     empMerital=null;
//     empMobile=null;
//     empDOB=null;
//     empDOJ=null;
//    empHighQualification=null;
//     empNationality=null;
//     empEmail=null;
//     empLocation=null;
//     empDeg=null;
//        }
        //return "/ETS/ui/Manage/empManager.xhtml?faces-redirect=true";
//         
        FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", Statusmsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        //FacesContext.getCurrentInstance().getExternalContext().redirect("empManager.xhtml");
    }
    public void onTabChange() {
        //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
       // if(event.getTab().getTitle().contains("View")){} 
        DeptUpdator();
          }
    public void DeptUpdator(){
    String Sql="SELECT id,dept_name FROM `tb_ets_dept`;";
    String msg1=null;
    ALLDept =new HashMap<>();
    ALLDept.clear();
      ArrayList<String []> querydata=dbHandler.getDataQuery(Sql, 100);
         for(String [] data:querydata){
             ALLDept.put(data[1],data[0]);
         }
    if(!querydata.isEmpty()){msg1="Department list updated";}
    FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", msg1);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public void empChange(){
        empList.clear();
        String Sql="SELECT id,`empName`,`empDept`,`empDesignation`,`empMobile` FROM `tb_ets_emp_reg` WHERE `emptype`="+this.getSelectedDEPTid()+";";
        System.out.println("Emp change/Edit method call Query="+Sql);
        ArrayList<String []> queryData=dbHandler.getDataQuery(Sql, 100);
            EmpInfoBean newObj=null;
               for(String record[] :queryData){
       newObj = new EmpInfoBean();       
       newObj.setEmpId(record[0]);
       newObj.setEmpName(record[1]);
       newObj.setEmpDept(record[2]);
       newObj.setEmpDeg(record[3]);
       newObj.setEmpMobile(record[4]);
       System.out.println(record[0] +""+record[1]);
       empList.add(newObj);
       newObj=null;
       }
            
            
            
        
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

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
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

    public Map<String, String> getEmptypeMap() {
        return emptypeMap;
    }

    public void setEmptypeMap(Map<String, String> emptypeMap) {
        this.emptypeMap = emptypeMap;
    }

    public String getEmpFName() {
        return empFName;
    }

    public void setEmpFName(String empFName) {
        this.empFName = empFName;
    }

    public String getEmpLName() {
        return empLName;
    }

    public void setEmpLName(String empLName) {
        this.empLName = empLName;
    }

    public Map<String, String> getEmpdeptMap() {
        return empdeptMap;
    }

    public void setEmpdeptMap(Map<String, String> empdeptMap) {
        this.empdeptMap = empdeptMap;
    }

    public String getEmpDeg() {
        return empDeg;
    }

    public void setEmpDeg(String empDeg) {
        this.empDeg = empDeg;
    }

    public Map<String, String> getALLDept() {
        return ALLDept;
    }

    public void setALLDept(Map<String, String> ALLDept) {
        this.ALLDept = ALLDept;
    }

    public String getSelectedDEPTid() {
        return selectedDEPTid;
    }

    public void setSelectedDEPTid(String selectedDEPTid) {
        this.selectedDEPTid = selectedDEPTid;
    }

    public List<EmpInfoBean> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EmpInfoBean> empList) {
        this.empList = empList;
    }
    
}

