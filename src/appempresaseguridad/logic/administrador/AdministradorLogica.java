/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.logic.administrador;

import appempresaseguridad.data.controller.TurnoUsuarioEmpresaJpaController;
import appempresaseguridad.data.entity.TurnoUsuarioEmpresa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class AdministradorLogica {
    
    private final TurnoUsuarioEmpresaJpaController turnController;
    
    public AdministradorLogica(){
        this.turnController = new TurnoUsuarioEmpresaJpaController();
    }
    
    public List<TurnoUsuarioEmpresa> getReporte(Date fechaDesde, Date fechaHasta){
        return turnController.generarReporte(fechaDesde, fechaHasta);
    }
    
    
}
