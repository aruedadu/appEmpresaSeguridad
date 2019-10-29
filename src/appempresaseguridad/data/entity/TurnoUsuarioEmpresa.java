/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejrudu
 */
@Entity
@Table(name = "turno_usuario_empresa", catalog = "empresa_seguridad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TurnoUsuarioEmpresa.findAll", query = "SELECT t FROM TurnoUsuarioEmpresa t"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdUsuario", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.turnoUsuarioEmpresaPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdEmpresa", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.turnoUsuarioEmpresaPK.idEmpresa = :idEmpresa"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByIdTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.turnoUsuarioEmpresaPK.idTurno = :idTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByFehcaInicioTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.turnoUsuarioEmpresaPK.fehcaInicioTurno = :fehcaInicioTurno"),
    @NamedQuery(name = "TurnoUsuarioEmpresa.findByFechaFinTurno", query = "SELECT t FROM TurnoUsuarioEmpresa t WHERE t.turnoUsuarioEmpresaPK.fechaFinTurno = :fechaFinTurno")})
public class TurnoUsuarioEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TurnoUsuarioEmpresaPK turnoUsuarioEmpresaPK;

    public TurnoUsuarioEmpresa() {
    }

    public TurnoUsuarioEmpresa(TurnoUsuarioEmpresaPK turnoUsuarioEmpresaPK) {
        this.turnoUsuarioEmpresaPK = turnoUsuarioEmpresaPK;
    }

    public TurnoUsuarioEmpresa(int idUsuario, int idEmpresa, int idTurno, Date fehcaInicioTurno, Date fechaFinTurno) {
        this.turnoUsuarioEmpresaPK = new TurnoUsuarioEmpresaPK(idUsuario, idEmpresa, idTurno, fehcaInicioTurno, fechaFinTurno);
    }

    public TurnoUsuarioEmpresaPK getTurnoUsuarioEmpresaPK() {
        return turnoUsuarioEmpresaPK;
    }

    public void setTurnoUsuarioEmpresaPK(TurnoUsuarioEmpresaPK turnoUsuarioEmpresaPK) {
        this.turnoUsuarioEmpresaPK = turnoUsuarioEmpresaPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (turnoUsuarioEmpresaPK != null ? turnoUsuarioEmpresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TurnoUsuarioEmpresa)) {
            return false;
        }
        TurnoUsuarioEmpresa other = (TurnoUsuarioEmpresa) object;
        if ((this.turnoUsuarioEmpresaPK == null && other.turnoUsuarioEmpresaPK != null) || (this.turnoUsuarioEmpresaPK != null && !this.turnoUsuarioEmpresaPK.equals(other.turnoUsuarioEmpresaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.TurnoUsuarioEmpresa[ turnoUsuarioEmpresaPK=" + turnoUsuarioEmpresaPK + " ]";
    }
    
}
