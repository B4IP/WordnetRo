package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 *
 * @author Mihai Cimpan
 */
public class WordnetDataBase {

    private static Connection connection;
    private static int idEnglishWord = 1;
    private static int idRomanianWord = 1;

    static {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Properties props = new Properties();
            props.setProperty("user", "mihai");
            props.setProperty("password", "mihai");
            connection = DriverManager.getConnection(url, props);
            System.out.println("DataBase has started!");
        } catch (SQLException error) {
            System.out.println("Error on DataBase open!");
            System.exit(0);
        }
    }

    public static void startDataBaseConnection() throws SQLException {
        try {
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Properties props = new Properties();
            props.setProperty("user", "mihai");
            props.setProperty("password", "mihai");
            connection = DriverManager.getConnection(url, props);
            System.out.println("Am pornit BD!");
        } catch (SQLException error) {
            System.out.println("Eroare la deschiderea bazei de date!");
            System.exit(0);
        }
    }

    public static void closeDataBaseConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException errorOnClose) {
            System.out.println("Eroare la inchiderea bazei de date!!!");
        }
    }

    public static void insertEnglishWords(int ID, String word) throws SQLException {
        String sql = "INSERT INTO cuvinte_engleza VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = WordnetDataBase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, ID);
            statement.setString(2, word);
            statement.executeQuery();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertEnglishWords");
            eroare.printStackTrace();
            System.exit(0);
        }
    }

    public static void insertRomanianWords(int idCuvantRomana, String wordRom, String wordEng, String glossa, int idCuvantEngleza)
            throws SQLException {
        String sql = "INSERT INTO cuvinte_romana VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = WordnetDataBase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idCuvantRomana);
            statement.setString(2, wordRom);
            statement.setString(3, wordEng);
            statement.setString(4, glossa);
            statement.setInt(5, idCuvantEngleza);
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertRomanianWords");
            eroare.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static int getIdCuvantEngleza() {
        return idEnglishWord;
    }

    public static void setIdCuvantEngleza(int aIdCuvant) {
        idEnglishWord = aIdCuvant;
    }

    public static int getIdCuvantRomana() {
        return idRomanianWord;
    }

    public static void setIdCuvantRomana(int aIdCuvantRomana) {
        idRomanianWord = aIdCuvantRomana;
    }
}
