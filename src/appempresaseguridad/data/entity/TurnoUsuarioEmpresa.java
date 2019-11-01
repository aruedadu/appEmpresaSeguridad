/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejrudu
 */
@Entity
@Table(name = "turno_usuario_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoUsuarioEmpresa.findAll", query = "SELECT t FROM TurnoUsuarioEmpresa t"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdUsuario", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.idUsuario = :idUsuario"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdEmpresa", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.idTurno = :idTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByFehcaInicioTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.fehcaInicioTurno = :fehcaInicioTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByFechaFinTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.fechaFinTurno = :fechaFinTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByFechaInicioFechaFinTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.fehcaInicioTurno BETWEEN :fechaInicio and :fechaFin OR t.fechaFinTurno BETWEEN :fechaInicio and :fechaFin OR :fechaInicio BETWEEN t.fehcaInicioTurno and t.fechaFinTurno OR :fechaFin BETWEEN t.fehcaInicioTurno and t.fechaFinTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdRegistroTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.idRegistroTurno = :idRegistroTurno")})
public class TurnoUsuarioEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private int idUsuario;
    @Basic(optional = false)
    @Column(name = "id_empresa")
    private int idEmpresa;
    @Basic(optional = false)
    @Column(name = "id_turno")
    private int idTurno;
    @Basic(optional = false)
    @Column(name = "fehca_inicio_turno")
    @Temporal(TemporalType.DATE)
    private Date fehcaInicioTurno;
    @Basic(optional = false)
    @Column(name = "fecha_fin_turno")
    @Temporal(TemporalType.DATE)
    private Date fechaFinTurno;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro_turno")
    private Integer idRegistroTurno;

    public TurnoUsuarioEmpresa() {
    }

    public TurnoUsuarioEmpresa(Integer idRegistroTurno) {
        this.idRegistroTurno = idRegistroTurno;
    }

    public TurnoUsuarioEmpresa(Integer idRegistroTurno, int idUsuario, int idEmpresa, int idTurno, Date fehcaInicioTurno, Date fechaFinTurno) {
        this.idRegistroTurno = idRegistroTurno;
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.idTurno = idTurno;
        this.fehcaInicioTurno = fehcaInicioTurno;
        this.fechaFinTurno = fechaFinTurno;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public Date getFehcaInicioTurno() {
        return fehcaInicioTurno;
    }

    public void setFehcaInicioTurno(Date fehcaInicioTurno) {
        this.fehcaInicioTurno = fehcaInicioTurno;
    }

    public Date getFechaFinTurno() {
        return fechaFinTurno;
    }

    public void setFechaFinTurno(Date fechaFinTurno) {
        this.fechaFinTurno = fechaFinTurno;
    }

    public Integer getIdRegistroTurno() {
        return idRegistroTurno;
    }

    public void setIdRegistroTurno(Integer idRegistroTurno) {
        this.idRegistroTurno = idRegistroTurno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistroTurno != null ? idRegistroTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoUsuarioEmpresa)) {
            return false;
        }
        TurnoUsuarioEmpresa other = (TurnoUsuarioEmpresa) object;
        if ((this.idRegistroTurno == null && other.idRegistroTurno != null) || (this.idRegistroTurno != null && !this.idRegistroTurno.equals(other.idRegistroTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.TurnoUsuarioEmpresa[ idRegistroTurno=" + idRegistroTurno + " ]";
    }

}
