
package final_gestion_tienda;

import java.sql.SQLException;
import vista.gestion_cliente;

/**
 *
 * @author maxi
 */
public class Final_Gestion_Tienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        gestion_cliente vista = new gestion_cliente();
        vista.setVisible(true);
    }
    
}
