package logica;

import conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author juanp
*/

public class Cls_Cursos {
    private final String SQL_INSERT = "INSERT INTO CURSOS (CODIGO_CURSO, NOMBRE_CURSO, ID_PRE_REQUSITO) VALUES (?,?,?)";
    private PreparedStatement PS;            
    private final Cls_Conexion CN;
    
    public Cls_Cursos(){
        PS = null;
        CN = new Cls_Conexion();
    }
       
    public int insertDatos( String CODIGO_CURSO, String NOMBRE_CURSO, String ID_PRE_REQUSITO){
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setString(1, CODIGO_CURSO);
            PS.setString(2, NOMBRE_CURSO);
            PS.setString(3, ID_PRE_REQUSITO);
            int res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,"Operación Exitosa...");
            }
        }catch(SQLException e){
            System.err.println("ERROR AL GUARDAR EN BD..." + e.getMessage());
        } finally {
            PS = null;
            CN.close();
        }       
        return 0;
    } 
}
