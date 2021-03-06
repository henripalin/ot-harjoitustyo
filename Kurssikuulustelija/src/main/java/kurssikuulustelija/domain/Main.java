package kurssikuulustelija.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import kurssikuulustelija.ui.GUI;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author henripal
 */
@SpringBootApplication
@ComponentScan({"kurssikuulustelija.ui", "kurssikuulustelija.dao"})
public class Main {

    static ConfigurableApplicationContext applicationContext;
    static String jdbcString = "jdbc:h2:./kurssikuulustelija";

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Main.class, args);
        //FIRST LAUNCH SETUP
        try (Connection conn = DriverManager.getConnection(jdbcString, "sa", "")) {
            conn.prepareStatement("SELECT 1 FROM User LIMIT 1").execute();
            conn.close();
        } catch (SQLException ex) {
            formatDatabase();
        }
        GUI.launchApp(GUI.class, args, applicationContext);
    }

    //DELETES AND RE-CREATES ALL SQL TABLES
    public static void formatDatabase() {
        try (Connection conn = DriverManager.getConnection(jdbcString, "sa", "")) {
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Exercise IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Point IF EXISTS;").executeUpdate();

            conn.prepareStatement("CREATE TABLE User(id INTEGER AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Exercise(id INTEGER AUTO_INCREMENT, course VARCHAR(100), question VARCHAR(100), answer VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Point(id INTEGER AUTO_INCREMENT, course VARCHAR(100), userId INTEGER, exerciseId INTEGER, PRIMARY KEY (id));").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
