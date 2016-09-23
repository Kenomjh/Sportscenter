package mxk.v1.dao;

import javafx.collections.ObservableList;
import mxk.v1.model.loginMemberModel;
import mxk.v1.model.loginViewModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by java on 2016-09-20.
 */

public class loginDAO {

    private static String DRV = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@//192.168.177.128:1521/xe";
    private static String USR = "kjh";
    private static String PWD = "123456";


    private ObservableList<loginViewModel> loginView = null;

    private static String idTest = "select count(*) as ino from member where userid=?";
    private static String pwdTest = "select count(*) as pno from member where (select passwd from member where userid=?)=?";
    private static String cview = "select * from mem where userid=?";

    private static String cLmm = "select * from member where userid=?";

    public static Connection openConn() throws Exception{
        Class.forName(DRV);
        return DriverManager.getConnection(URL, USR, PWD);
    }

    public static void closeConn(Connection conn,PreparedStatement pstmt, ResultSet rs) {
        if(rs !=null)try{rs.close();rs=null;}catch (Exception e){}
        if(pstmt !=null)try{pstmt.close();pstmt=null;}catch (Exception e){}
        if(conn !=null)try{conn.close();conn=null;}catch (Exception e){}
    }


    //id 확인
    public static int idCheck(String userid){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int ino = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(idTest);
            pstmt.setString(1,userid);
            rs = pstmt.executeQuery();
            if(rs.next()) { ino = rs.getInt("ino");}

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn, pstmt,rs);
        }
        return ino;
    }
    //비밀번호확인
    public static int pwdCheck(String uid, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int pno = 0;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(pwdTest);
            pstmt.setString(1,uid);
            pstmt.setString(2,pwd);
            rs = pstmt.executeQuery();
            if(rs.next()) { pno = rs.getInt("pno");}

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn, pstmt,rs);
        }
        return pno;
    }
    //뷰테이블 생성
    public static loginViewModel createView(String uid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        loginViewModel result = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cview);
            pstmt.setString(1,uid);
            rs = pstmt.executeQuery();

            if(rs.next()){
                loginViewModel lm = new loginViewModel(
                        rs.getString("mno"),
                        rs.getString("userid"),
                        rs.getString("username"),
                        rs.getString("addr"),
                        rs.getString("tel"));
                result=lm;
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn, pstmt,rs);
        }

        return result;
    }

    //로그인회원정보 생성
    public static loginMemberModel createlmm(String uid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        loginMemberModel result = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cLmm);
            pstmt.setString(1,uid);
            rs = pstmt.executeQuery();

            if(rs.next()){
                loginMemberModel lm = new loginMemberModel(
                        rs.getString("mno"),
                        rs.getString("userid"),
                        rs.getString("passwd"),
                        rs.getString("username"),
                        rs.getString("bday"),
                        rs.getString("gender"),
                        rs.getString("addr"),
                        rs.getString("tel"),
                        rs.getString("email"),
                        rs.getString("ldate"));

                result=lm;
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn, pstmt,rs);
        }

        return result;
    }

}
