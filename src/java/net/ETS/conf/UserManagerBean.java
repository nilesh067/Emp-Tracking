package net.ETS.conf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import net.ETS.util.DBconnection;



import net.ETS.util.Beans.UserBean;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TabChangeEvent;


@ManagedBean
@ViewScoped
public class UserManagerBean {
     List<UserBean> userList;
    UserBean selectedUser,newUser;
    Map<String,String> roleMap;
    DBconnection dbConn;
    String classLogHeader;

    public UserManagerBean() {
        dbConn=new DBconnection();
        selectedUser=new UserBean();
        newUser=new UserBean();
        userList=new ArrayList<UserBean>();
        roleMap=new HashMap<String,String>();
        classLogHeader="UserManagerBean.class :: "; 
        runQueries(false,true);
    }
    public void runQueries(boolean reloadUserList,boolean reloadRolesList){
    String loggerHead=classLogHeader+"reloadPage() :: ";
       java.sql.Connection conn=dbConn.getConnection();
        if(conn==null) conn=dbConn.getConnection();
        if(conn!=null){
            try{
                java.sql.Statement st=conn.createStatement();
                if(reloadUserList) reloadUsers(st);
                if(reloadRolesList) reloadRolesList(st);
            }catch(Exception e){
                System.out.println("RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead Exception "+e);
//dbConn.logUIMsg(RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead+" Exception "+e);
                FacesMessage msg = new FacesMessage("Story Box Error", "Exception: " + e);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }finally{
                try{if(conn!=null) conn.close();}catch(Exception e1){}
                conn=null;
            }
        }
     }
    public void reloadRolesList(java.sql.Statement st){
        String loggerHead=classLogHeader+"reloadRoles() :: ";
        try{
            
            String sql1="select role_id,role_name from tb_user_roles where status>0 order by role_name;";
            java.sql.ResultSet rs=st.executeQuery(sql1);
            roleMap.clear();
            
            while(rs.next()){
                roleMap.put(rs.getString("role_name"), rs.getString("role_id"));
                
            }
            if(rs!=null) rs.close();
            rs=null;
            //FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", "Story chapter List Reloaded!");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
           System.out.println("RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead Exception "+e);
// dbConn.logUIMsg(RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead+" Exception "+e);
            FacesMessage msg = new FacesMessage("Story Box Error", "Exception: " + e);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    public void reloadUsers(java.sql.Statement st){
        String loggerHead=classLogHeader+"reloadUsers() :: ";
        try{
            
            String sql1="select a.user_id,b.role_id,b.role_name,a.user_name,a.password,a.email,a.mobile,a.status,a.created_on,a.last_update from tb_users a,tb_user_roles b where a.role_id=b.role_id order by a.user_name,b.role_name;";
            java.sql.ResultSet rs=st.executeQuery(sql1);
            userList.clear();
            UserBean newObj=null;
            while(rs.next()){
                newObj=new UserBean();
                newObj.setUserId(rs.getInt("user_id"));
                newObj.setRoleId(rs.getInt("role_id"));
                newObj.setUserName(rs.getString("user_name"));
                newObj.setPassword(rs.getString("password"));
                newObj.setEmail(rs.getString("email"));
                newObj.setMobile(rs.getString("mobile"));
                newObj.setStatus(rs.getInt("status"));
                newObj.setCreatedOn(rs.getString("created_on"));
                newObj.setLastUpdate(rs.getString("last_update"));
               
               userList.add(newObj);
                newObj=null;
            }
            if(rs!=null) rs.close();
            rs=null;
            FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", "User List Reloaded!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch(Exception e){
           System.out.println("RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead Exception "+e);
            //dbConn.logUIMsg(RLogger.MSG_TYPE_ERROR, RLogger.LOGGING_LEVEL_ERROR, loggerHead+" Exception "+e);
            FacesMessage msg = new FacesMessage("Story Box Error", "Exception: " + e);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

          public void onTabChange(TabChangeEvent event) {
        //FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        if(event.getTab().getTitle().contains("View")) runQueries(true,false);
          }

           public void registerNewUser() throws SQLException {  
         String newUserName=newUser.getUserName();
        newUserName=(newUserName==null)?"":newUserName.trim();
        //int circleId=RUtil.strToInt(newUser.getCircleId(),0);
        newUser.setUserName("");
        String statusMsg="";
        if(newUserName.length()>0){
            String sql1="insert into tb_users(role_id,user_name,password,email,mobile,status,created_on,last_update) values ('"+newUser.getRoleIdStr()+"','"+newUserName+"','"+newUser.getPassword()+"','"+newUser.getEmail()+"'"+newUser.getMobile()+"',1,now(),now());";
            java.sql.Connection conn=dbConn.getConnection();
            java.sql.Statement st=conn.createStatement();
            int rep=st.executeUpdate(sql1);
            statusMsg=(rep>0)?"Database Updated Successfully":"Failed to register new User! Pl try again.";
            if(rep>0) runQueries(true,false);
        }else{
            statusMsg="Invalid User! User can not be blank!";
        }
        FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", statusMsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
          }
        
        public void onEdit(RowEditEvent event) throws SQLException {  
        //add code for updating new data into database
        String statusMsg="";
        UserBean actionObj=(UserBean)event.getObject();
        if(actionObj!=null && actionObj.getUserId()>0){
            String sql1="update tb_users set Role_id='"+actionObj.getRoleId()+"',user_name='"+actionObj.getUserName()+"',password='"+actionObj.getPassword()+"',email='"+actionObj.getEmail()+"',mobile='"+actionObj.getMobile()+"',status='"+actionObj.getStatus()+"',last_update='"+actionObj.getLastUpdate()+"',company_id='"+actionObj.getCompanyId()+"' where user_id='"+actionObj.getUserId()+"';";
            java.sql.Connection conn=dbConn.getConnection();
            int rep=conn.createStatement().executeUpdate(sql1);
            //int rep=dbConn.executeUpdate(sql1);
            statusMsg=(rep>0)?"Database Updated Successfully":"Failed to update Database! Pl try again.";
            if(rep>0) runQueries(true,false);
        }else{
            statusMsg="Invalid Object! Pl select an object to update!";
        }
         FacesMessage msg = new FacesMessage("Employee Tracking Syatem Alert", statusMsg);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    public List<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
    }

    public UserBean getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(UserBean selectedUser) {
        this.selectedUser = selectedUser;
    }

    public UserBean getNewUser() {
        return newUser;
    }

    public void setNewUser(UserBean newUser) {
        this.newUser = newUser;
    }

    public Map<String, String> getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map<String, String> roleMap) {
        this.roleMap = roleMap;
    }

    
}
