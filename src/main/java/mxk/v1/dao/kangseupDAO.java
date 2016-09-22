package mxk.v1.dao;

import mxk.v1.model.kangseupModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by java on 2016-09-20.
 */


public class kangseupDAO {

    private static String DRV = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@//192.168.177.128:1521/xe";
    private static String USR = "kjh";
    private static String PWD = "123456";

    // 오라클 데이터베이스 접속용 메서드
    public static Connection openConn() throws Exception{
        Class.forName(DRV);
        return DriverManager.getConnection(URL, USR, PWD);
    }
    // 오라클 데이터베이스 접속 해제용 메서드
    public static void closeConn(Connection conn,PreparedStatement pstmt, ResultSet rs) {
        if(rs !=null)try{rs.close();rs=null;}catch (Exception e){}
        if(pstmt !=null)try{pstmt.close();pstmt=null;}catch (Exception e){}
        if(conn !=null)try{conn.close();conn=null;}catch (Exception e){}
    }

    public static void newApplication(kangseupModel km){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = openConn();
            /*pstmt = conn.prepareStatement();*/

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }

    }

}
