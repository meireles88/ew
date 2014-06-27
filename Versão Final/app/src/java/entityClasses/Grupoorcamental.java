/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityClasses;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author wolvz
 */
@Entity
@Table(name = "grupoorcamental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoorcamental.findAll", query = "SELECT g FROM Grupoorcamental g"),
    @NamedQuery(name = "Grupoorcamental.findByOid", query = "SELECT g FROM Grupoorcamental g WHERE g.oid = :oid"),
    @NamedQuery(name = "Grupoorcamental.findByTitulo", query = "SELECT g FROM Grupoorcamental g WHERE g.titulo = :titulo"),
    @NamedQuery(name = "Grupoorcamental.findByDescricao", query = "SELECT g FROM Grupoorcamental g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "Grupoorcamental.findByDatainsercao", query = "SELECT g FROM Grupoorcamental g WHERE g.datainsercao = :datainsercao"),
    @NamedQuery(name = "Grupoorcamental.findByDataedicao", query = "SELECT g FROM Grupoorcamental g WHERE g.dataedicao = :dataedicao")})
public class Grupoorcamental implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "datainsercao")
    @Temporal(TemporalType.DATE)
    private Date datainsercao;
    @Column(name = "dataedicao")
    @Temporal(TemporalType.DATE)
    private Date dataedicao;
    @OneToMany(mappedBy = "grupoorcamentalOid")
    private Collection<Ideia> ideiaCollection;

    public Grupoorcamental() {
    }

    public Grupoorcamental(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDatainsercao() {
        return datainsercao;
    }

    public void setDatainsercao(Date datainsercao) {
        this.datainsercao = datainsercao;
    }

    public Date getDataedicao() {
        return dataedicao;
    }

    public void setDataedicao(Date dataedicao) {
        this.dataedicao = dataedicao;
    }

    @XmlTransient
    public Collection<Ideia> getIdeiaCollection() {
        return ideiaCollection;
    }

    public void setIdeiaCollection(Collection<Ideia> ideiaCollection) {
        this.ideiaCollection = ideiaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoorcamental)) {
            return false;
        }
        Grupoorcamental other = (Grupoorcamental) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClasses.Grupoorcamental[ oid=" + oid + " ]";
    }
    
}
