package mxk.v1.dao;

import mxk.v1.controller.mainController;
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

    private static String payTest = "select lpay from class where lesson=?";
    private static String kLoad = "insert into REGISTER(rno,mno,lesson,rldate) values (sq_rno.nextval,?,?,?)";
    private static String updatekang = "update register set lesson=?, rldate=? where rno=?";
    private static String deletekang = "update register set rting='예약취소' where rno=?";
    private static String cntT1 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '테니스%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='테니스' and a='어린이반'";
    private static String cntT2 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '테니스%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='테니스' and a='성인반'";
    private static String cntT3 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '테니스%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='테니스' and a='직장인반'";
    private static String cntS1 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '수영%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='수영' and a='어린이반'";
    private static String cntS2 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '수영%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='수영' and a='성인반'";
    private static String cntS3 = "select count(rno) as cnt from REGISTER join(\n" +
            " SELECT DISTINCT rno, TRIM(REGEXP_SUBSTR(lesson, '[^-]+', 1, LEVEL)) as a\n" +
            " FROM register where lesson like '수영%' and rting !='예약취소'\n" +
            "        CONNECT BY INSTR(lesson, '-', 1, LEVEL - 1) > 0 ) using(rno) where a !='수영' and a='직장인반'";

    //예상비용불러오기
    public  int payCk(String s1){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int lpayA = 0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(payTest);
            pstmt.setString(1,s1);
            rs = pstmt.executeQuery();
            if(rs.next()){
                lpayA = rs.getInt("lpay");
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }

        return  lpayA;
    }
    //신청내역저장하기
    public void kangseupLoad(String rsl, String as){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(kLoad);
            pstmt.setString(1, mainController.mlm.getMno());
            pstmt.setString(2,rsl);
            pstmt.setString(3,as);
            pstmt.executeUpdate();
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }

    }

    //수정하기
    public  void updateKS(String rsl, String as,String rno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(updatekang);
            pstmt.setString(1,rsl );
            pstmt.setString(2,as);
            pstmt.setString(3,rno);
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }

    }

    //삭제하기
    public  void deleteKS(String rno){
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(deletekang);
            pstmt.setString(1,rno);
            pstmt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }

    }

    //인원체크
    public int kangseupS1Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntS1);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }
    public int kangseupS2Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntS2);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }
    public int kangseupS3Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntS3);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }
    public int kangseupT1Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntT1);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }
    public int kangseupT2Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntT2);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }
    public int kangseupT3Cnt(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int result=0;
        try {
            conn = openConn();
            pstmt = conn.prepareStatement(cntT3);
            rs = pstmt.executeQuery();
            if(rs.next()){
                result = rs.getInt("cnt");
            }
        }catch (Exception e){e.printStackTrace();
        }finally {
            closeConn(conn,pstmt,null);
        }
        return result;
    }





}