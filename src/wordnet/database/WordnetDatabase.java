package wordnet.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import net.sf.extjwnl.data.POS;

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

            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            Properties properties = new Properties();
            properties.setProperty("user", "mihai");
            properties.setProperty("password", "mihai");

            /*
             String url = "jdbc:oracle:thin:@85.122.23.37:1521:xe";
             Properties properties = new Properties();
             properties.setProperty("user", "stud98");
             properties.setProperty("password", "MIHAI");  
             */
            connection = DriverManager.getConnection(url, properties);
            System.out.println("Database has started!");
        } catch (SQLException error) {
            System.out.println("Error on DataBase open!");
            System.exit(0);
        }
    }

    public static void closeDataBaseConnection() {
        try {
            connection.close();
            System.out.println("Database has closed!");
        } catch (SQLException errorOnClose) {
            System.out.println("Error on DataBase close!");
        }
    }

    public static void insertEnglishWords(int ID, String word) {
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

    public static void insertRomanianWords(int idCuvantRomana, String wordRom, String wordEng, String glossa,
            int idCuvantEngleza, int modificatDe, String glossa_tradusa, String pos) {
        String sql = "INSERT INTO cuvinte_romana VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
            statement.setInt(6, modificatDe);
            statement.setString(7, glossa_tradusa);
            statement.setString(8, pos);
            statement.executeQuery();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on insertRomanianWords");
            eroare.printStackTrace();
            System.exit(0);
        }
    }

    public static void insertHypernyms(int idCuvantEng, String hiper, String glossa) {

        String sql;
        Connection conn;
        PreparedStatement statement;
        ResultSet result;
        int idHyper = 0;
        //int idGlossHyper = 0;

        String rezultat = " ";
        sql = "SELECT ID_cuvant_eng_FK \n"
                + "FROM cuvinte_romana \n"
                + "WHERE lower(cuvant_eng) = lower(?) AND lower(glossa) = lower(?)";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, hiper);
            statement.setString(2, glossa);
            result = statement.executeQuery();
            while (result.next()) {
                idHyper = Integer.parseInt(result.getString("ID_cuvant_eng_FK"));
               //idGlossHyper =  Integer.parseInt(result.getString("ID_cuvant_eng_FK"));
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
        System.out.println("-> @ " + idCuvantEng + " --- " + idHyper + " @");

        if (idCuvantEng != 0 && idHyper != 0) {
            sql = "INSERT INTO hiperonime VALUES (?, ?)";
            try {
                conn = WordnetDatabase.getConnection();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idCuvantEng);
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

    public static void insertMeronyms(int idCuvantEng, String mero, String glossa) {
        String sql;
        Connection conn;
        PreparedStatement statement;
        ResultSet result;
        int idMero = 0;
        String rezultat = "";
        sql = "SELECT ID_cuvant_eng_FK \n"
                + "FROM cuvinte_romana \n"
                + "WHERE lower(cuvant_eng) = lower(?) AND lower(glossa) = lower(?)";
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, mero);
            statement.setString(2, glossa);
            result = statement.executeQuery();
            while (result.next()) {
                rezultat = rezultat + result.getString("ID_cuvant_eng_FK");
                idMero = Integer.parseInt(result.getString("ID_cuvant_eng_FK"));
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
        System.out.println("-> @ " + idCuvantEng + " --- " + idMero + " @");

        if (idCuvantEng != 0 && idMero != 0) {
            sql = "INSERT INTO meronime VALUES (?, ?)";
            try {
                conn = WordnetDatabase.getConnection();
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idCuvantEng);
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

    
    public static int getContiunePosition() {

        Connection conn;
        CallableStatement getContinueTranslatePosition;
        String function;
        function = "{? = call getContinueTranslatePosition}";
        int position = 1;
        try {
            conn = WordnetDatabase.getConnection();
            getContinueTranslatePosition = conn.prepareCall(function);
            getContinueTranslatePosition.registerOutParameter(1, Types.NUMERIC);
            getContinueTranslatePosition.executeUpdate();
            position = getContinueTranslatePosition.getInt(1);
            getContinueTranslatePosition.close();
        } catch (SQLException error) {
            System.out.println("Error on getContiunePosition");
            error.printStackTrace();
        }
        return position;
    }

    public static int getStopPosition() {

        Connection conn;
        CallableStatement getStopPosition;
        String function;
        function = "{? = call getStopPosition}";
        int position = 1;
        try {
            conn = WordnetDatabase.getConnection();
            getStopPosition = conn.prepareCall(function);
            getStopPosition.registerOutParameter(1, Types.NUMERIC);
            getStopPosition.executeUpdate();
            position = getStopPosition.getInt(1);
            getStopPosition.close();
        } catch (SQLException error) {
            System.out.println("Error on getStopPosition");
            error.printStackTrace();
        }
        return position;
    }

    public static String getEnglishWord(int idCuvantRomana) {

        Connection conn;
        CallableStatement getEnglishWord;
        String function;
        function = "{? = call getEnglishWord(?)}";
        String word = "";
        try {
            conn = WordnetDatabase.getConnection();
            getEnglishWord = conn.prepareCall(function);
            getEnglishWord.registerOutParameter(1, Types.VARCHAR);
            getEnglishWord.setInt(2, idCuvantRomana);
            getEnglishWord.executeUpdate();
            word = getEnglishWord.getString(1);
            getEnglishWord.close();
        } catch (SQLException error) {
            System.out.println("Error on getEnglishWord");
            WordnetDatabase.closeDataBaseConnection();
            error.printStackTrace();
            System.exit(0);
        }
        return word;
    }

    public static String getEnglishGloss(int idCuvantRomana) {

        Connection conn;
        CallableStatement getEnglishGloss;
        String function;
        function = "{? = call getEnglishGloss(?)}";
        String gloss = "";
        try {
            conn = WordnetDatabase.getConnection();
            getEnglishGloss = conn.prepareCall(function);
            getEnglishGloss.registerOutParameter(1, Types.VARCHAR);
            getEnglishGloss.setInt(2, idCuvantRomana);
            getEnglishGloss.executeUpdate();
            gloss = getEnglishGloss.getString(1);
            getEnglishGloss.close();
        } catch (SQLException error) {
            System.out.println("Error on getEnglishGloss");
            WordnetDatabase.closeDataBaseConnection();
            error.printStackTrace();
            System.exit(0);
        }
        return gloss;
    }

    public static POS getEnglishPOS(int idCuvantRomana) {

        Connection conn;
        CallableStatement getEnglishPOS;
        String function;
        function = "{? = call getEnglishPOS(?)}";
        String posString = "";
        try {
            conn = WordnetDatabase.getConnection();
            getEnglishPOS = conn.prepareCall(function);
            getEnglishPOS.registerOutParameter(1, Types.VARCHAR);
            getEnglishPOS.setInt(2, idCuvantRomana);
            getEnglishPOS.executeUpdate();
            posString = getEnglishPOS.getString(1);
            getEnglishPOS.close();
        } catch (SQLException error) {
            System.out.println("Error on getEnglishPOS");
            WordnetDatabase.closeDataBaseConnection();
            error.printStackTrace();
            System.exit(0);
        }
        return POS.valueOf(posString.toUpperCase());
    }

    public static void updateRomaninWord(int idCuvantRomana, String traducere, String glossa, int modificat) {
        String sql = "UPDATE cuvinte_romana \n"
                + "SET cuvant_rom = ?,\n"
                + "glossa_tradusa = ?,\n"
                + "modificat = ? \n"
                + "WHERE ID_cuvant_rom = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, traducere);
            statement.setString(2, glossa);
            statement.setInt(3, modificat);
            statement.setInt(4, idCuvantRomana);
            statement.executeQuery();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on updateRomaninWord");
            eroare.printStackTrace();
            System.exit(0);
        }

    }

    public static void updateOnTraslationFail(int idCuvantRomana) {
        String sql = "UPDATE cuvinte_romana \n"
                + "SET modificat = ?,\n"
                + "WHERE ID_cuvant_rom = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        int modificat = -1;
        try {
            conn = WordnetDatabase.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, modificat);
            statement.setInt(2, idCuvantRomana);
            statement.executeQuery();
            statement.close();
        } catch (SQLException eroare) {
            System.out.println("Error on updateRomaninWord");
            eroare.printStackTrace();
            System.exit(0);
        }

    }

    public static int[] getFailedToTranslateIDs() {
        Connection conn;
        CallableStatement getNumberOfFails;
        String function;
        function = "{? = call getNumberOfFails}";
        int numberOfFails = -1;
        try {
            conn = WordnetDatabase.getConnection();
            getNumberOfFails = conn.prepareCall(function);
            getNumberOfFails.registerOutParameter(1, Types.NUMERIC);
            getNumberOfFails.executeUpdate();
            numberOfFails = getNumberOfFails.getInt(1);
            getNumberOfFails.close();
        } catch (SQLException error) {
            System.out.println("Error on getEnglishPOS");
            WordnetDatabase.closeDataBaseConnection();
            error.printStackTrace();
            System.exit(0);
        }

        if (numberOfFails != -1) {
            int listOfFailedTraslations[] = new int[numberOfFails];
            String sql;
            PreparedStatement statement;
            ResultSet result;
            int idCuvnatRomana = 0;
            int iterator = 0;
            sql = "SELECT ID_cuvant_rom \n"
                    + "FROM cuvinte_romana \n"
                    + "WHERE modificat = -1";
            try {
                conn = WordnetDatabase.getConnection();
                statement = conn.prepareStatement(sql);
                result = statement.executeQuery();
                while (result.next()) {
                    idCuvnatRomana = Integer.parseInt(result.getString("ID_cuvant_rom"));
                    listOfFailedTraslations[iterator++] = idCuvnatRomana;
                }
                result.close();
                statement.close();
            } catch (SQLException eroare) {
                System.out.println("Error on insertMeronyms");
                eroare.printStackTrace();
                System.exit(0);
            }
            return listOfFailedTraslations;
        }

        return null;
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
