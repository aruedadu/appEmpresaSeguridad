/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appempresaseguridad.data.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alejrudu
 */
@Entity
@Table(name = "tipo_documento", catalog = "empresa_seguridad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByNombreTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.nombreTipoDocumento = :nombreTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByAbreviaturaTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.abreviaturaTipoDocumento = :abreviaturaTipoDocumento")})
public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento")
    private Integer idTipoDocumento;
    @Basic(optional = false)
    @Column(name = "nombre_tipo_documento")
    private String nombreTipoDocumento;
    @Basic(optional = false)
    @Column(name = "abreviatura_tipo_documento")
    private String abreviaturaTipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumentoPersona")
    private List<Persona> personaList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumento(Integer idTipoDocumento, String nombreTipoDocumento, String abreviaturaTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreTipoDocumento = nombreTipoDocumento;
        this.abreviaturaTipoDocumento = abreviaturaTipoDocumento;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }

    public String getAbreviaturaTipoDocumento() {
        return abreviaturaTipoDocumento;
    }

    public void setAbreviaturaTipoDocumento(String abreviaturaTipoDocumento) {
        this.abreviaturaTipoDocumento = abreviaturaTipoDocumento;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.TipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
