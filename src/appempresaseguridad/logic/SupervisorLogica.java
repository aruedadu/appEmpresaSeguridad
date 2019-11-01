/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.logic;

import appempresaseguridad.data.controller.EmpresaJpaController;
import appempresaseguridad.data.controller.UsuarioJpaController;
import appempresaseguridad.data.entity.Empresa;
import appempresaseguridad.data.entity.Usuario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alejrudu
 */
public class SupervisorLogica {
    
    private final EmpresaJpaController empController;
    private final UsuarioJpaController usuController;
    
    public SupervisorLogica(){
        this.empController = new EmpresaJpaController();
        this.usuController = new UsuarioJpaController();
    }
    
    public List<Empresa> getEmpresas(){
        return empController.findEmpresaEntities();
    }
    
    public List<Usuario> getUsuariosEmpresaTurno(int idEmpresa, Date fecha){
        return usuController.findUsuariosEmpresaEntities(idEmpresa, fecha);
    }
    
}
