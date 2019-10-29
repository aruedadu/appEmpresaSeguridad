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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author alejrudu
 */
@Embeddable
public class TurnoUsuarioEmpresaPK implements Serializable {
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

    public TurnoUsuarioEmpresaPK() {
    }

    public TurnoUsuarioEmpresaPK(int idUsuario, int idEmpresa, int idTurno, Date fehcaInicioTurno, Date fechaFinTurno) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idUsuario;
        hash += (int) idEmpresa;
        hash += (int) idTurno;
        hash += (fehcaInicioTurno != null ? fehcaInicioTurno.hashCode() : 0);
        hash += (fechaFinTurno != null ? fechaFinTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoUsuarioEmpresaPK)) {
            return false;
        }
        TurnoUsuarioEmpresaPK other = (TurnoUsuarioEmpresaPK) object;
        if (this.idUsuario != other.idUsuario) {
            return false;
        }
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        if (this.idTurno != other.idTurno) {
            return false;
        }
        if ((this.fehcaInicioTurno == null && other.fehcaInicioTurno != null) || (this.fehcaInicioTurno != null && !this.fehcaInicioTurno.equals(other.fehcaInicioTurno))) {
            return false;
        }
        if ((this.fechaFinTurno == null && other.fechaFinTurno != null) || (this.fechaFinTurno != null && !this.fechaFinTurno.equals(other.fechaFinTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.TurnoUsuarioEmpresaPK[ idUsuario=" + idUsuario + ", idEmpresa=" + idEmpresa + ", idTurno=" + idTurno + ", fehcaInicioTurno=" + fehcaInicioTurno + ", fechaFinTurno=" + fechaFinTurno + " ]";
    }
    
}
