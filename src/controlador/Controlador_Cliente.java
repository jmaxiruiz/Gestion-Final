
package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Cliente;
import vista.gestion_cliente;

/**
 *
 * @author maxi
 */
public class Controlador_Cliente {
    
    private static Cliente cliente;
    
    public static void ActualizarCliente(gestion_cliente vista) throws SQLException {
        cliente = new Cliente();
        vista.getModelo().setColumnCount(0);
        vista.getModelo().setNumRows(0);
        vista.getModelo().addColumn("Id");
        vista.getModelo().addColumn("Nombre");
        vista.getModelo().addColumn("Telefono");
        vista.getModelo().addColumn("Direccion");
        ResultSet r = cliente.listarCliente();
        while (r.next()) {
            Object[] fila = new Object[5];
            fila[0] = r.getString("idCliente");
            fila[1] = r.getString("nombre");
            fila[2] = r.getString("telefono");
            fila[3] = r.getString("direccion");
            vista.getModelo().addRow(fila);
        }
        vista.getTabla_cliente().setModel(vista.getModelo());
    }

    public static void Mostrar(gestion_cliente vista) {
        int fila = vista.getTabla_cliente().getSelectedRow();
        if(fila > -1){
            vista.getTxt_id().setEnabled(false);
            vista.getTxt_id().setText(vista.getTabla_cliente().getModel().getValueAt(fila, 0).toString());
            vista.getTxt_nombre().setText(vista.getTabla_cliente().getModel().getValueAt(fila, 1).toString());
            vista.getTxt_telefono().setText(vista.getTabla_cliente().getModel().getValueAt(fila, 2).toString());
            vista.getTxt_direccion().setText(vista.getTabla_cliente().getModel().getValueAt(fila, 3).toString());
        }
    }

    public static void AltasCliente(gestion_cliente vista) {
        cliente = new Cliente();
        try {
            cliente.setNombre(vista.getTxt_nombre().getText());
            cliente.setTelefono(Integer.valueOf(vista.getTxt_telefono().getText()));
            cliente.setDireccion(vista.getTxt_direccion().getText());
            System.out.println("AGREGANDO CLIENTE");
            cliente.grabarCliente();
            ActualizarCliente(vista);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(vista, "Datos mal ingresados", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
    }

    public static void EditarCliente(gestion_cliente vista) throws SQLException {
        int fila = vista.getTabla_cliente().getSelectedRow();
        if (fila >-1) {
            int opt = JOptionPane.showConfirmDialog(vista, "¿Esta seguro de modificarlo?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cliente = new Cliente();
                cliente.setIdcliente(Integer.valueOf(vista.getTxt_id().getText()));
                cliente.setNombre(vista.getTxt_nombre().getText());
                cliente.setTelefono(Integer.valueOf(vista.getTxt_telefono().getText()));
                cliente.setDireccion(vista.getTxt_direccion().getText());
                
                System.out.println("MODIFICANDO CLIENTE");
                cliente.editarCliente();
                ActualizarCliente(vista);
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cliente", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void EliminarCliente(gestion_cliente vista) throws SQLException {
        int fila = vista.getTabla_cliente().getSelectedRow();
        if (fila > -1) {
            int opt = JOptionPane.showConfirmDialog(vista, "Esta seguro de Borrar ?", "Mensaje de Confirmación", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                cliente = new Cliente();
                cliente.setIdcliente(Integer.parseInt(vista.getTabla_cliente().getModel().getValueAt(fila, 0).toString()));
                cliente.eliminarCliente();
 
            }
        } else {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un Cliente", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
