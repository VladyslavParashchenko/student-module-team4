package sumdu;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class for working with Database
 */
public class DatabaseWorker {
    private final Connection connection;

    /**
     * Method ust for test run
     * @param args
     */
    public static void main(String[] args){
        try {
            DatabaseWorker worker = new DatabaseWorker("database_conf");
        }
        catch (ClassNotFoundException e){
            System.out.println("driver problems");
            e.printStackTrace();
        }
        catch (SQLException e){
            System.out.println("database connection error");
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            System.out.println("configure script not found");
            e.printStackTrace();
        }
    }

    /**
     * Start point of work with database. Set up and configure database
     * @param configPath - path of properties for database
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws FileNotFoundException
     */
    public DatabaseWorker(String configPath) throws ClassNotFoundException, SQLException, FileNotFoundException{
        ResourceBundle source = getResources(configPath);
        connection = buildConnection(source);
        configureDatabase(source);
    }

    /**
     * Find the resource bundle
     * @param configPath
     * @return
     */
    private ResourceBundle getResources(String configPath){
        return ResourceBundle.getBundle(configPath);
    }

    /**
     * Make connection with database
     * @param source bundle with database properties
     * @return database connection
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Connection buildConnection(ResourceBundle source) throws ClassNotFoundException, SQLException {
        String url = buildDatabasePath(source);
        String user = source.getString("user_name");
        String passwd = source.getString("passwd");

        String driverPath = source.getString("driver_path");
        Class.forName(driverPath);
        return DriverManager.getConnection(url, user, passwd);
    }

    /**
     * Build database path from a lot parts
     * @param source
     * @return
     */
    private String buildDatabasePath(ResourceBundle source){
        String url_schema = source.getString("url_schema");
        String driver = source.getString("driver_name");
        String port = source.getString("port");
        return String.format(url_schema, driver, port);
    }

    /**
     * Set up the database
     * @param source
     * @throws FileNotFoundException
     */
    private void configureDatabase(ResourceBundle source) throws FileNotFoundException {
        String fileName = source.getString("configure_script");
        String filePath = getClass().getClassLoader().getResource(fileName).getFile();
        Reader scriptReader = new BufferedReader(new FileReader(filePath));
        ScriptRunner runner = new ScriptRunner(connection);
        runner.runScript(scriptReader);
    }
}
