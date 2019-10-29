/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejrudu
 */
@Entity
@Table(name = "turno", catalog = "empresa_seguridad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findByIdTurno", query = "SELECT t FROM Turno t WHERE t.idTurno = :idTurno"),
    @NamedQuery(name = "Turno.findByNombreTurno", query = "SELECT t FROM Turno t WHERE t.nombreTurno = :nombreTurno"),
    @NamedQuery(name = "Turno.findByHoraInicioTurno", query = "SELECT t FROM Turno t WHERE t.horaInicioTurno = :horaInicioTurno"),
    @NamedQuery(name = "Turno.findByHoraFinTurno", query = "SELECT t FROM Turno t WHERE t.horaFinTurno = :horaFinTurno")})
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_turno")
    private Integer idTurno;
    @Basic(optional = false)
    @Column(name = "nombre_turno")
    private String nombreTurno;
    @Basic(optional = false)
    @Column(name = "hora_inicio_turno")
    private int horaInicioTurno;
    @Basic(optional = false)
    @Column(name = "hora_fin_turno")
    private int horaFinTurno;

    public Turno() {
    }

    public Turno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public Turno(Integer idTurno, String nombreTurno, int horaInicioTurno, int horaFinTurno) {
        this.idTurno = idTurno;
        this.nombreTurno = nombreTurno;
        this.horaInicioTurno = horaInicioTurno;
        this.horaFinTurno = horaFinTurno;
    }

    public Integer getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Integer idTurno) {
        this.idTurno = idTurno;
    }

    public String getNombreTurno() {
        return nombreTurno;
    }

    public void setNombreTurno(String nombreTurno) {
        this.nombreTurno = nombreTurno;
    }

    public int getHoraInicioTurno() {
        return horaInicioTurno;
    }

    public void setHoraInicioTurno(int horaInicioTurno) {
        this.horaInicioTurno = horaInicioTurno;
    }

    public int getHoraFinTurno() {
        return horaFinTurno;
    }

    public void setHoraFinTurno(int horaFinTurno) {
        this.horaFinTurno = horaFinTurno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurno != null ? idTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.idTurno == null && other.idTurno != null) || (this.idTurno != null && !this.idTurno.equals(other.idTurno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.Turno[ idTurno=" + idTurno + " ]";
    }
    
}
