package wordnet.database;

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
public class WordnetDatabase {

    private static Connection connection;
    private static int idEnglishWord = 1;
    private static int idRomanianWord = 1;

    static {
        try {
            /*  String url = "jdbc:oracle:thin:@85.122.23.37:1521:xe";
            Properties properties = new Properties();
            properties.setProperty("user", "stud98");
            properties.setProperty("password", "MIHAI");
            */
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

    public static void closeDataBaseConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException errorOnClose) {
            System.out.println("Error on DataBase close!");
        }
    }

    public static void insertEnglishWords(int ID, String word) throws SQLException {
        String sql = "INSERT INTO cuvinte_engleza VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = WordnetDatabase.getConnection();
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
            conn = WordnetDatabase.getConnection();
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

public static void insertHypernyms(String cuvEng, String hiper)
            throws SQLException {
        String sql;
        Connection conn;
        PreparedStatement statement;
        ResultSet result;
        int idCuvEng = 0;
        int idHyper = 0;

        String rezultat = "@ ";
        sql = "SELECT ID_cuvant_eng\n"
                + "FROM cuvinte_engleza\n"
                + "WHERE cuvant_eng = ?";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, cuvEng);
            result = statement.executeQuery();
            //System.out.println("id cuvant: ");
            while (result.next()) {
                rezultat = rezultat + result.getString("ID_cuvant_eng");
                idCuvEng = Integer.parseInt(result.getString("ID_cuvant_eng"));
            }

            rezultat = rezultat + " --- ";

            result.close();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertHypernyms");
            eroare.printStackTrace();
            System.exit(0);
        }

        sql = "SELECT ID_cuvant_eng\n"
                + "FROM cuvinte_engleza\n"
                + "WHERE cuvant_eng = ?";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, hiper);
            result = statement.executeQuery();
            while (result.next()) {
                rezultat = rezultat + result.getString("ID_cuvant_eng");
                idHyper = Integer.parseInt(result.getString("ID_cuvant_eng"));
            }
            rezultat = rezultat + " @";
            result.close();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertHypernyms");
            eroare.printStackTrace();
            System.exit(0);
        }

        System.out.println(rezultat);
        System.out.println("-> @ " + idCuvEng + " --- " + idHyper + " @");

        if (idCuvEng != 0 && idHyper != 0) {
            sql = "INSERT INTO hiperonime VALUES (?, ?)";
            try {
                conn = WordnetDatabase.getConnection();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idCuvEng);
                statement.setInt(2, idHyper);
                statement.executeQuery();
                statement.close();
            } catch (SQLException eroare) {
                System.out.println("Error on insertHypernyms3");
                eroare.printStackTrace();
                System.exit(0);
            }

            System.out.println("-> @ OK!");
        } else {
            System.out.println("-> @ NOT OK!");
        }
    }
    
      public static void insertMeronyms(String cuvEng, String mero)
            throws SQLException {
        String sql;
        Connection conn;
        PreparedStatement statement;
        ResultSet result;
        int idCuvEng = 0;
        int idMero = 0;

        String rezultat = "@ ";
        sql = "SELECT ID_cuvant_eng\n"
                + "FROM cuvinte_engleza\n"
                + "WHERE cuvant_eng = ?";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, cuvEng);
            result = statement.executeQuery();
            //System.out.println("id cuvant: ");
            while (result.next()) {
                rezultat = rezultat + result.getString("ID_cuvant_eng");
                idCuvEng = Integer.parseInt(result.getString("ID_cuvant_eng"));
            }

            rezultat = rezultat + " --- ";

            result.close();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertMeronyms");
            eroare.printStackTrace();
            System.exit(0);
        }

        sql = "SELECT ID_cuvant_eng\n"
                + "FROM cuvinte_engleza\n"
                + "WHERE cuvant_eng = ?";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, mero);
            result = statement.executeQuery();
            while (result.next()) {
                rezultat = rezultat + result.getString("ID_cuvant_eng");
                idMero = Integer.parseInt(result.getString("ID_cuvant_eng"));
            }
            rezultat = rezultat + " @";
            result.close();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertMeronyms");
            eroare.printStackTrace();
            System.exit(0);
        }

        System.out.println(rezultat);
        System.out.println("-> @ " + idCuvEng + " --- " + idMero + " @");

        if (idCuvEng != 0 && idMero != 0) {
            sql = "INSERT INTO meronime VALUES (?, ?)";
            try {
                conn = WordnetDatabase.getConnection();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idCuvEng);
                statement.setInt(2, idMero);
                statement.executeQuery();
                statement.close();
            } catch (SQLException eroare) {
                System.out.println("Error on insertMeronyms");
                eroare.printStackTrace();
                System.exit(0);
            }

            System.out.println("-> @ OK!");
        } else {
            System.out.println("-> @ NOT OK!");
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
