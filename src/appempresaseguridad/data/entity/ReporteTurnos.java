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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alejrudu
 */
@Entity
@Table(name = "reporte_turnos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteTurnos.findAll", query = "SELECT r FROM ReporteTurnos r"),
    @NamedQuery(name = "ReporteTurnos.findByIdRegistro", query = "SELECT r FROM ReporteTurnos r WHERE r.idRegistro = :idRegistro"),
    @NamedQuery(name = "ReporteTurnos.findByComentarioReporte", query = "SELECT r FROM ReporteTurnos r WHERE r.comentarioReporte = :comentarioReporte")})
public class ReporteTurnos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_registro")
    private Integer idRegistro;
    @Basic(optional = false)
    @Column(name = "comentario_reporte")
    private String comentarioReporte;
    @JoinColumn(name = "id_usuario_registra", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioRegistra;
    @JoinColumn(name = "id_usuario_reportado", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuarioReportado;

    public ReporteTurnos() {
    }

    public ReporteTurnos(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public ReporteTurnos(Integer idRegistro, String comentarioReporte) {
        this.idRegistro = idRegistro;
        this.comentarioReporte = comentarioReporte;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getComentarioReporte() {
        return comentarioReporte;
    }

    public void setComentarioReporte(String comentarioReporte) {
        this.comentarioReporte = comentarioReporte;
    }

    public Usuario getIdUsuarioRegistra() {
        return idUsuarioRegistra;
    }

    public void setIdUsuarioRegistra(Usuario idUsuarioRegistra) {
        this.idUsuarioRegistra = idUsuarioRegistra;
    }

    public Usuario getIdUsuarioReportado() {
        return idUsuarioReportado;
    }

    public void setIdUsuarioReportado(Usuario idUsuarioReportado) {
        this.idUsuarioReportado = idUsuarioReportado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReporteTurnos)) {
            return false;
        }
        ReporteTurnos other = (ReporteTurnos) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.ReporteTurnos[ idRegistro=" + idRegistro + " ]";
    }
    
}
