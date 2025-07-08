// src/dao/WeighingSessionDAO.java
package dao;

import java.sql.*;
import java.util.*;
import model.*;
import utils.DBContext;

public class WeighingSessionDAO extends DBContext {

    public int insertSession(WeighingSession session) throws Exception {
        Connection conn = getConnection();
        conn.setAutoCommit(false);

        String insertSessionSQL = "INSERT INTO WeighingSession (customerID, weighingDate, pricePerKg, note) VALUES (?, GETDATE(), ?, ?)";
        PreparedStatement psSession = conn.prepareStatement(insertSessionSQL, Statement.RETURN_GENERATED_KEYS);
        psSession.setInt(1, session.getCustomerID());
        psSession.setDouble(2, session.getPricePerKg());
        psSession.setString(3, session.getNote());
        psSession.executeUpdate();

        ResultSet rs = psSession.getGeneratedKeys();
        int sessionID = 0;
        if (rs.next()) {
            sessionID = rs.getInt(1);
        }

        String insertDetailSQL = "INSERT INTO WeighingDetail (sessionID, bagIndex, weightKg) VALUES (?, ?, ?)";
        PreparedStatement psDetail = conn.prepareStatement(insertDetailSQL);

        int index = 1;
        for (WeighingDetail detail : session.getDetails()) {
            psDetail.setInt(1, sessionID);
            psDetail.setInt(2, index++);
            psDetail.setDouble(3, detail.getWeightKg());
            psDetail.addBatch();
        }

        psDetail.executeBatch();
        conn.commit();
        conn.close();
        return 0;
    }

    public List<WeighingSession> getAllSessions() throws Exception {
        List<WeighingSession> list = new ArrayList<>();
        String sql = "SELECT * FROM WeighingSession";
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            WeighingSession s = new WeighingSession();
            s.setSessionID(rs.getInt("sessionID"));
            s.setCustomerID(rs.getInt("customerID"));
            s.setWeighingDate(rs.getString("weighingDate"));
            s.setPricePerKg(rs.getDouble("pricePerKg"));
            s.setNote(rs.getString("note"));
            list.add(s);
        }
        conn.close();
        return list;
    }

    public void deleteSession(int sessionID) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps1 = conn.prepareStatement("DELETE FROM WeighingDetail WHERE sessionID = ?");
        ps1.setInt(1, sessionID);
        ps1.executeUpdate();

        PreparedStatement ps2 = conn.prepareStatement("DELETE FROM WeighingSession WHERE sessionID = ?");
        ps2.setInt(1, sessionID);
        ps2.executeUpdate();

        conn.close();
    }

    public void updateSessionNote(int sessionID, String note) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE WeighingSession SET note = ? WHERE sessionID = ?");
        ps.setString(1, note);
        ps.setInt(2, sessionID);
        ps.executeUpdate();
        conn.close();
    }

    public WeighingSession getSessionById(int sessionID) throws Exception {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM WeighingSession WHERE sessionID = ?");
        ps.setInt(1, sessionID);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            WeighingSession s = new WeighingSession();
            s.setSessionID(rs.getInt("sessionID"));
            s.setCustomerID(rs.getInt("customerID"));
            s.setWeighingDate(rs.getString("weighingDate"));
            s.setPricePerKg(rs.getDouble("pricePerKg"));
            s.setNote(rs.getString("note"));
            return s;
        }
        conn.close();
        return null;
    }
}

// phần còn lại giữ nguyên
