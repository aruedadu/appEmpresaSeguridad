/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.logic;

import appempresaseguridad.data.controller.TipoDocumentoJpaController;
import appempresaseguridad.data.entity.TipoDocumento;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Garcia
 */
public class LoguinLogic {

    /**
     * método encargado de hacer la orquestación necesaria para verificar que
     * los datos ingresados en pantalla correspondan a un usuario registrado
     */
    public void hacerLoguin() {
        try {
            TipoDocumentoJpaController controller = new TipoDocumentoJpaController();
            Object o[] = null;
            List<TipoDocumento> listaDocumento = controller.findTipoDocumentoEntities();
            
            for(TipoDocumento documento : listaDocumento){
                System.err.println("test "+documento.getNombreTipoDocumento());
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
