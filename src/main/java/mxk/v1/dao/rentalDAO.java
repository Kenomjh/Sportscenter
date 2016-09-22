package mxk.v1.dao;

import mxk.v1.controller.mainController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by java on 2016-09-20.
 */
public class rentalDAO {

    private static String insertPrent = " insert into PRENT (PRNO, MNO, PDATE, PTIME ) "+
            " values (sq_pno.nextval, ?, ?, ?) ";
    private static String outPpay = " select ppay from soccer where ptime = ? ";
    private static String updatePrent = " update prent set pdate=?, ptime=? where PRNO=? ";
    private static String deletePrent = " update prent set ring='예약취소' where PRNO=? ";

    public String loadPpay(String r) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String result = null;

        try {
            conn = loginDAO.openConn();

            pstmt = conn.prepareStatement(outPpay);
            pstmt.setString(1, r);
            rs = pstmt.executeQuery();

            if (rs.next()){
                result = rs.getString("ppay");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loginDAO.closeConn(conn, pstmt,rs);
        }
        return result;
    }

    public static void newPrent(String r,String r2) {
        // 데이터베이스 관련 변수
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = loginDAO.openConn();

            pstmt = conn.prepareStatement(insertPrent);
            pstmt.setString(1, mainController.mlm.getMno());
            pstmt.setString(2, r2);
            pstmt.setString(3, r);

            pstmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            loginDAO.closeConn(conn, pstmt, null);
        }
    }//newPrent

    public void updateRS(String rs, String rs2, String prno) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = loginDAO.openConn();

            pstmt = conn.prepareStatement(updatePrent);
            pstmt.setString(1, rs2);
            pstmt.setString(2, rs);
            pstmt.setString(3, prno);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loginDAO.closeConn(conn, pstmt, null);
        }
    }

    public void deleteRS(String prno) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = loginDAO.openConn();

            pstmt = conn.prepareStatement(deletePrent);
            pstmt.setString(1, prno);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loginDAO.closeConn(conn, pstmt, null);
        }
    }
}
