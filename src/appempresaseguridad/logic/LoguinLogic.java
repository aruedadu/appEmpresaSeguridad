/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.logic;

import appempresaseguridad.data.controller.UsuarioJpaController;
import appempresaseguridad.data.entity.Usuario;
import appempresaseguridad.gui.secretaria.SecretariaFrame;
import appempresaseguridad.gui.supervisor.SupervisorFrame;
import javax.persistence.NoResultException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Garcia
 */
public class LoguinLogic {

    /**
     * método encargado de hacer la orquestación necesaria para verificar que
     * los datos ingresados en pantalla correspondan a un usuario registrado
     *
     * @param user
     * @param password
     * @param loguin
     */
    public void hacerLoguin(String user, String password, JFrame loguin) {
        try {
            UsuarioJpaController userController = new UsuarioJpaController();
            Usuario userObject = userController.findUsuario(user, password);
            loguin.dispose();

            switch (userObject.getIdRolUsuario().getNombreRol()) {
                case "ADMINISTRADOR":

                    break;

                case "SECRETARIA":

                    SecretariaFrame frameSecretaria = new SecretariaFrame();
                    frameSecretaria.setLocationRelativeTo(null);
                    frameSecretaria.setVisible(true);
                    break;

                case "VIGILANTE":

                    break;

                case "SUPERVISOR":
                    
                    SupervisorFrame frameSupervisor = new SupervisorFrame();
                    frameSupervisor.setLocationRelativeTo(null);
                    frameSupervisor.setVisible(true);
                    break;

                default:
                    break;
            }

        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(null, "El nombre de usuario y la clave de acceso no corresponden");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

}
