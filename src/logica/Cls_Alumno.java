package logica;

// PAQUETES NECESARIOS 
import conexion.Cls_Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 * @author juanp
 */

// CLASE ALUMNO, ESTABLECEMOS INSERT Y CONSULTA DE DATOS
public class Cls_Alumno {
    
    private final String SQL_INSERT = "INSERT INTO ALUMNO (NOMBRES, APELLIDOS, DPI, TELEFONO, DIRECCION, ESTADO) VALUES (?,?,?,?,?,?)";
    private final String SQL_SELECT = "SELECT * FROM ALUMNO";
    private PreparedStatement PS;
    private DefaultTableModel DT;
    private ResultSet RS;
    private final Cls_Conexion CN;
        
    public Cls_Alumno(){
        PS = null;
        CN = new Cls_Conexion();
    }
 
// GRID EL CUAL MUESTRA LOS DATOS ALMACENADOS    
    private DefaultTableModel setTitulos(){
        DT = new DefaultTableModel();
        DT.addColumn("ID_ALUMNO");
        DT.addColumn("NOMBRES");
        DT.addColumn("APELLIDOS");
        DT.addColumn("DPI");
        DT.addColumn("TELEFONO");
        DT.addColumn("DIRECCION");
        DT.addColumn("ESTADO");
        return DT;
    }
    
// METODO PARA INSERTAR DATOS   
    public int insertDatos(String NOMBRES, String APELLIDOS, String DPI, String TELEFONO, String DIRECCION, String ESTADO){
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL_INSERT);
            PS.setString(1, NOMBRES);
            PS.setString(2, APELLIDOS);
            PS.setString(3, DPI);
            PS.setString(4, TELEFONO);
            PS.setString(5, DIRECCION);
            PS.setString(6, ESTADO);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,"Operación Exitosa...");
            }
        }catch(SQLException e){
            System.err.println("ERROR AL GUARDAR EN BD..." + e.getMessage());
        } finally {
            PS = null;
            CN.close();
        }       
        return res;
    }

// METODO PARA ACTUALIZAR DATOS      
    public int updateDatos(String ID_ALUMNO, String NOMBRES, String APELLIDOS, String DPI, String TELEFONO, String DIRECCION, String ESTADO){
        String SQL = "UPDATE ALUMNO SET NOMBRES='"+NOMBRES+"', APELLIDOS='"+APELLIDOS+"', DPI='"+DPI+"', TELEFONO='"+TELEFONO+"', DIRECCION='"+DIRECCION+"', ESTADO='"+ESTADO+"' WHERE ID_ALUMNO=" + ID_ALUMNO;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,"Modificación de Registro Exitosa...");
            }
        }catch(SQLException e){
            System.err.println("ERROR AL MODIFICAR EN BD..." + e.getMessage());
        } finally {
            PS = null;
            CN.close();
        }       
        return res;
    }

// METODO PARA ELIMINAR DATOS     
    public int deleteDatos(String ID_ALUMNO){
        String SQL = "DELETE FROM ALUMNO WHERE ID_ALUMNO=" + ID_ALUMNO;
        int res = 0;
        try {
            PS = CN.getConnection().prepareStatement(SQL);
            res = PS.executeUpdate();
            if(res > 0){
                JOptionPane.showMessageDialog(null,"Eliminación de Registro Exitosa...");
            }
        }catch(SQLException e){
            System.err.println("ERROR AL ELIMINAR EN BD..." + e.getMessage());
        } finally {
            PS = null;
            CN.close();
        }
        return res;
    }
    
// METODO PARA SELECCIONAR DATOS    
    public DefaultTableModel getDatos(){
        try{
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL_SELECT);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
        }catch(SQLException e){
            System.out.println("Error al llamar los datos..." + e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DT;
    }

// METODO PARA BUSCAR DATOS DENTRO DEL GRID     
    public DefaultTableModel getDato(int crt, String prm){
        String SQL;
        if (crt == 0){
            SQL = "SELECT * FROM ALUMNO WHERE ID_ALUMNO = " + prm;
        } else {
            SQL = "SELECT * FROM ALUMNO WHERE NOMBRES LIKE '" + prm + "%'";
        }
        try{
            setTitulos();
            PS = CN.getConnection().prepareStatement(SQL);
            RS = PS.executeQuery();
            Object[] fila = new Object[7];
            while (RS.next()) {
                fila[0] = RS.getInt(1);
                fila[1] = RS.getString(2);
                fila[2] = RS.getString(3);
                fila[3] = RS.getString(4);
                fila[4] = RS.getString(5);
                fila[5] = RS.getString(6);
                fila[6] = RS.getString(7);
                DT.addRow(fila);
            }
        }catch(SQLException e){
            System.out.println("Error al llamar los datos..." + e.getMessage());
        } finally{
            PS = null;
            RS = null;
            CN.close();
        }
        return DT;
    }
}

