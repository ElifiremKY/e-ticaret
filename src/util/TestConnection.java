package util;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = ConnectionHelper.getConnection();
            System.out.println("✅ Veritabanına başarıyla bağlandı!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Bağlantı hatası: " + e.getMessage());
        }
    }
}
