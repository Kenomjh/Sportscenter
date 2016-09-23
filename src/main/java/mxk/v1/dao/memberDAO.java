package mxk.v1.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mxk.v1.controller.mainController;
import mxk.v1.model.kReservationModel;
import mxk.v1.model.loginViewModel;
import mxk.v1.model.memberModel;
import mxk.v1.model.pReservationModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class memberDAO {
    private static String DRV = "oracle.jdbc.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@//192.168.177.128:1521/xe";
    private static String USR = "kjh";
    private static String PWD = "123456";

    private static  String newMember  = "insert into member(mno, userid,passwd,username,bday,gender,addr,tel,email,ldate)"
                                    + "VALUES(sq_mno.nextval, ?,?,?,?,?,?,?,?,?)"; // 저장하기

    private static String zipsql = "select DISTINCT zipcode, do, gun, myeon, gil, ri from zipcode where myeon = ?";

    private static String updatesql = "update member set passwd = ?, tel = ?, bday = ?, addr = ?, email = ? where userid = ?";

    private static String listrent = " select * from PRENT join soccer using (ptime) where mno = ? " +
            " ORDER BY PRNO DESC ";

    private static String deltemem = "delete from member where userid = ?";

    private static String deltereg = "delete from register where mno = (select mno from member where userid = ?)";

    private static String deltepre = "delete from prent where mno = (select mno from member where userid = ?)";

    private static String ckrm = "select * from register join class using (lesson) where mno = ? order by rno desc";

    private static ObservableList ziplist;

    public static Connection openConn () {
        Connection conn = null;
        try {
            Class.forName(DRV);
            conn = DriverManager.getConnection(URL, USR, PWD);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return conn;
    } // openConn

    public static void closeConn(Connection c, PreparedStatement p , ResultSet r){
        if (r != null) try { r.close(); r=null; }catch (Exception e){}
        if (p != null) try { p.close(); p=null; }catch (Exception e){}
        if (c != null) try { c.close(); c=null; }catch (Exception e){}
    } // closeConn

    public static ObservableList showzipcode(String myeon) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuilder sb = null;
        ziplist = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(zipsql);
            pstmt.setString(1, myeon);
            rs = pstmt.executeQuery();

            ziplist = FXCollections.observableArrayList();

            while (rs.next()) {
                sb = new StringBuilder();
                sb.append(rs.getString(1))
                .append(rs.getString(2)).append(" ")
                .append(rs.getString(3)).append(" ")
                .append(rs.getString(4)).append(" ")
                .append(rs.getString(5)).append(" ")
                .append(rs.getString(6)).append(" ");
                ziplist.add(sb.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
           closeConn(conn, pstmt, rs);
        }
        return ziplist;
    } // showzipcode

    public static void savedb(memberModel md) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(newMember);
                pstmt.setString(1, md.getId());
                pstmt.setString(2, md.getPw());
                pstmt.setString(3, md.getName());
                pstmt.setString(4, md.getBday());
                pstmt.setString(5, md.getGender());
                pstmt.setString(6, md.getAddr1()+"-"+md.getAddr2()+" "+md.getAddr3()+" "+md.getAddr4());
                pstmt.setString(7, md.getTel());
                pstmt.setString(8, md.getEmail());
                pstmt.setString(9, md.getLdate());

            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        } // try-catch
    } // savedb

    public static void updatedb(memberModel md) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(updatesql);
            pstmt.setString(1, md.getPw());
            pstmt.setString(2, md.getTel());
            pstmt.setString(3, md.getBday());
            pstmt.setString(4, md.getAddr1()+"-"+md.getAddr2()+" "+md.getAddr3()+" "+md.getAddr4());
            pstmt.setString(5, md.getEmail());
            pstmt.setString(6, md.getId());

            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    } // updatedb

    public static void deletedb(String uid) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(deltemem);
            pstmt.setString(1, uid);
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = null;

            pstmt = conn.prepareStatement(deltereg);
            pstmt.setString(1, uid);
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = null;

            pstmt = conn.prepareStatement(deltepre);
            pstmt.setString(1, uid);
            pstmt.executeUpdate();
            pstmt.close();
            pstmt = null;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn, pstmt, null);
        }
    }

    public static List<pReservationModel> listRent(loginViewModel mlm) {
        // 데이터베이스 관련 변수
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<pReservationModel> result = new ArrayList<>();
        // 전체 글 목록을 저장하기 위해 배열로 변수 선언
        // listBoard 변수에 정의된 질의문을 실행하고
        // 그 결과를 BoardModel 객체 형태로 변환
        // 물론 모든 항목이 아닌 일부 항목을 선별
        // 일부 : 번호, 제목, 작성자, 작성일, 조회수

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(listrent);
            pstmt.setString(1, mlm.getMno());
            rs = pstmt.executeQuery();

            while (rs.next()){
                pReservationModel m = new pReservationModel(
                        rs.getString("prno"),
                        rs.getString("pdate").substring(0, 10),
                        rs.getString("ptime"),
                        rs.getString("ppay"),
                        rs.getString("tdate").substring(0, 10),
                        rs.getString("ring")
                );
                // 조회한 결과들을 BoardModel 객체로 생성
                result.add(m);
                // BoardModel 객체를 ArrayList에 저장
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConn(conn,pstmt,rs);
        }

        return result;
    }

    //회원강습정보 생성
    public static List<kReservationModel> createkrm() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<kReservationModel> result = new ArrayList<>();
        String a = mainController.mlm.getMno();

        try {
            conn = openConn();
            pstmt = conn.prepareStatement(ckrm);
            pstmt.setString(1,a);

            rs = pstmt.executeQuery();

            while (rs.next()){
                kReservationModel lm = new kReservationModel(
                        rs.getString("rno"),
                        rs.getString("lesson"),
                        rs.getString("rldate"),
                        rs.getString("lpay"),
                        rs.getString("rtdate").substring(0,10),
                        rs.getString("rting")
                );

                result.add(lm);
            }


        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeConn(conn, pstmt,rs);
        }

        return result;
    }

} // class