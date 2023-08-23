package conexion;

// PAQUETES NECESARIOS PARA LA CONEXION
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



// CONFIGURACIÓN PARA LA CONEXCION
public class Cls_Conexion {

    private static final String DRIVER   ="com.mysql.cj.jdbc.Driver";
    private static final String USER     ="root";
    private static final String PASSWORD ="elian030114";
    private static final String URL      ="jdbc:mysql://localhost:3306/controlumg";
    private Connection CN;
    
    public Cls_Conexion(){
        CN = null;
    }

// ESTABLECEMOS CONEXION CON LA BASE DE DATOS    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            CN = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERRO AL CONECTAR A LA BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return CN;
    }

// CERRAMOS LA CONEXION
    public void close(){
        try{
            CN.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR AL CERRAR", JOptionPane.ERROR_MESSAGE);
        }
    }
}