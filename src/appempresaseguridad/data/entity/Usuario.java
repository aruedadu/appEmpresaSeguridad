/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Felipe Garcia
 */
@Entity
@Table(name = "usuario", catalog = "empresa_seguridad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByIdPersona", query = "SELECT u FROM Usuario u WHERE u.idPersonaUsuario.idPersona = :idPersona"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByPassUsuario", query = "SELECT u FROM Usuario u WHERE u.passUsuario = :passUsuario"),
    @NamedQuery(name = "Usuario.findByIdEmpresa", query = "SELECT u FROM Usuario u WHERE u.idUsuario IN (SELECT e.idUsuario FROM TurnoUsuarioEmpresa e WHERE e.idEmpresa = :idEmpresa AND :fecha BETWEEN e.fehcaInicioTurno AND e.fechaFinTurno)"),
    @NamedQuery(name = "Usuario.findByPassUsuarioNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.passUsuario = :passUsuario AND u.nombreUsuario = :nombreUsuario")})
public class Usuario implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioRegistra")
    private Collection<ReporteTurnos> reporteTurnosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioReportado")
    private Collection<ReporteTurnos> reporteTurnosCollection1;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "pass_usuario")
    private String passUsuario;
    @JoinColumn(name = "id_persona_usuario", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona idPersonaUsuario;
    @JoinColumn(name = "id_rol_usuario", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Rol idRolUsuario;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String passUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.passUsuario = passUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassUsuario() {
        return passUsuario;
    }

    public void setPassUsuario(String passUsuario) {
        this.passUsuario = passUsuario;
    }

    public Persona getIdPersonaUsuario() {
        return idPersonaUsuario;
    }

    public void setIdPersonaUsuario(Persona idPersonaUsuario) {
        this.idPersonaUsuario = idPersonaUsuario;
    }

    public Rol getIdRolUsuario() {
        return idRolUsuario;
    }

    public void setIdRolUsuario(Rol idRolUsuario) {
        this.idRolUsuario = idRolUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombreUsuario();
    }

    @XmlTransient
    public Collection<ReporteTurnos> getReporteTurnosCollection() {
        return reporteTurnosCollection;
    }

    public void setReporteTurnosCollection(Collection<ReporteTurnos> reporteTurnosCollection) {
        this.reporteTurnosCollection = reporteTurnosCollection;
    }

    @XmlTransient
    public Collection<ReporteTurnos> getReporteTurnosCollection1() {
        return reporteTurnosCollection1;
    }

    public void setReporteTurnosCollection1(Collection<ReporteTurnos> reporteTurnosCollection1) {
        this.reporteTurnosCollection1 = reporteTurnosCollection1;
    }
    
}
