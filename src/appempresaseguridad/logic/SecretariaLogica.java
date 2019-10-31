/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.logic;

import appempresaseguridad.data.controller.PersonaJpaController;
import appempresaseguridad.data.controller.RolJpaController;
import appempresaseguridad.data.controller.TipoDocumentoJpaController;
import appempresaseguridad.data.controller.UsuarioJpaController;
import appempresaseguridad.data.entity.Persona;
import appempresaseguridad.data.entity.Rol;
import appempresaseguridad.data.entity.TipoDocumento;
import appempresaseguridad.data.entity.Usuario;
import java.util.List;

/**
 *
 * @author Felipe Garcia
 */
public class SecretariaLogica {

    private List<TipoDocumento> listaTiposDocumento = null;
    private List<Rol> listaRoles = null;
    private PersonaJpaController persController;
    private UsuarioJpaController usuController;

    public SecretariaLogica() {
        if (null == this.listaTiposDocumento) {
            TipoDocumentoJpaController controllerTipoDocumento = new TipoDocumentoJpaController();
            this.listaTiposDocumento = controllerTipoDocumento.findTipoDocumentoEntities();
        }
        if (null == listaRoles) {
            RolJpaController rolController = new RolJpaController();
            this.listaRoles = rolController.findRolEntities();
        }
        this.persController = new PersonaJpaController();
        this.usuController = new UsuarioJpaController();
    }

    /**
     * @return the listaTiposDocumento
     */
    public List<TipoDocumento> getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    /**
     * @return the listaRoles
     */
    public List<Rol> getListaRoles() {
        return listaRoles;
    }
    
    public void crearUsuario(Persona persona, Usuario usuario){
        persController.create(persona);
        usuario.setIdPersonaUsuario(persona);
        usuController.create(usuario);
    }

}
