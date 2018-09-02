package net.ETS.Beans;

import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import net.ETS.util.DBconnection;
//import net.rocg.util.RLogger;
//import net.rocg.util.SystemConfig;

/**
 *
 * @author Nilesh Singh
 */
@ManagedBean
@SessionScoped
public class LoginBean  implements java.io.Serializable{
    DBconnection dbConn;
    String userName;
    String password;
    boolean loginStatus;
    int userId;
    int roleId;
    String roleName;
    String homepage;
    String email;
    String mobile;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        dbConn=new DBconnection();
        loginStatus=false;
    }
    
    public String checkLogin(){
        loginStatus=false;
        java.sql.Connection conn=dbConn.getConnection();
        if(conn!=null){
            try{
                String sql1="SELECT a.user_id,a.email,a.mobile,b.role_id,b.role_name,b.homepage FROM tb_users a, tb_user_roles b WHERE a.role_id=b.role_id AND a.status>0 AND b.status>0 AND a.user_name=? AND a.password=?;";
                System.out.println("LoginBean.class :: checkLogin() :: sql1 : "+sql1);                
                PreparedStatement st=conn.prepareStatement(sql1);
                st.setString(1, this.getUserName());
                st.setString(2, this.getPassword());
                java.sql.ResultSet rs=st.executeQuery();
                if(rs.next()){
                    this.setUserId(rs.getInt("user_id"));
                    this.setEmail(rs.getString("email"));
                    this.setMobile(rs.getString("mobile"));
                    this.setRoleId(rs.getInt("role_id"));
                    this.setRoleName(rs.getString("role_name"));
                    this.setHomepage(rs.getString("homepage"));
                    this.setLoginStatus(true);
                }
                if(rs!=null) rs.close();
                rs=null;
               
            }catch(Exception e){
                System.out.println("LoginBean.class :: checkLogin() :: Exception "+e);
            }finally{
                try{if(conn!=null) conn.close();}catch(Exception ee){}
                conn=null;
            }
        }else{
            System.out.println("LoginBean.class :: checkLogin() :: Failed to connect Database.");
           
        }
        if(this.isLoginStatus()){
            System.out.println("LoginBean.class :: checkLogin() :: Login Successful.");
            setSessionExpiry(5);
            return "loginsuccess";
        }else{
            System.out.println("LoginBean.class :: checkLogin() :: Login Failed."); 
           
            return "BLANK";
        }
    }

    public void setSessionExpiry(int min){
         FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext extCtx = ctx.getExternalContext();
        extCtx.getSessionMap().put("loginBean", this);
        extCtx.setSessionMaxInactiveInterval(min*60);
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
}
