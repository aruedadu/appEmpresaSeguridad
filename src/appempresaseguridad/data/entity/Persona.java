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
 * @author alejrudu
 */
@Entity
@Table(name = "persona", catalog = "empresa_seguridad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByNumeroDocumentoPersona", query = "SELECT p FROM Persona p WHERE p.numeroDocumentoPersona = :numeroDocumentoPersona"),
    @NamedQuery(name = "Persona.findByNombresPersona", query = "SELECT p FROM Persona p WHERE p.nombresPersona = :nombresPersona"),
    @NamedQuery(name = "Persona.findByPrimerApellidoPersona", query = "SELECT p FROM Persona p WHERE p.primerApellidoPersona = :primerApellidoPersona"),
    @NamedQuery(name = "Persona.findBySegundoApellidoPersona", query = "SELECT p FROM Persona p WHERE p.segundoApellidoPersona = :segundoApellidoPersona")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Basic(optional = false)
    @Column(name = "numero_documento_persona")
    private String numeroDocumentoPersona;
    @Basic(optional = false)
    @Column(name = "nombres_persona")
    private String nombresPersona;
    @Basic(optional = false)
    @Column(name = "primer_apellido_persona")
    private String primerApellidoPersona;
    @Column(name = "segundo_apellido_persona")
    private String segundoApellidoPersona;
    @JoinColumn(name = "id_tipo_documento_persona", referencedColumnName = "id_tipo_documento")
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDocumentoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersonaUsuario")
    private List<Usuario> usuarioList;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer idPersona, String numeroDocumentoPersona, String nombresPersona, String primerApellidoPersona) {
        this.idPersona = idPersona;
        this.numeroDocumentoPersona = numeroDocumentoPersona;
        this.nombresPersona = nombresPersona;
        this.primerApellidoPersona = primerApellidoPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNumeroDocumentoPersona() {
        return numeroDocumentoPersona;
    }

    public void setNumeroDocumentoPersona(String numeroDocumentoPersona) {
        this.numeroDocumentoPersona = numeroDocumentoPersona;
    }

    public String getNombresPersona() {
        return nombresPersona;
    }

    public void setNombresPersona(String nombresPersona) {
        this.nombresPersona = nombresPersona;
    }

    public String getPrimerApellidoPersona() {
        return primerApellidoPersona;
    }

    public void setPrimerApellidoPersona(String primerApellidoPersona) {
        this.primerApellidoPersona = primerApellidoPersona;
    }

    public String getSegundoApellidoPersona() {
        return segundoApellidoPersona;
    }

    public void setSegundoApellidoPersona(String segundoApellidoPersona) {
        this.segundoApellidoPersona = segundoApellidoPersona;
    }

    public TipoDocumento getIdTipoDocumentoPersona() {
        return idTipoDocumentoPersona;
    }

    public void setIdTipoDocumentoPersona(TipoDocumento idTipoDocumentoPersona) {
        this.idTipoDocumentoPersona = idTipoDocumentoPersona;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "appempresaseguridad.data.entity.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
