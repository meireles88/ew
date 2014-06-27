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
@Table(name = "categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByOid", query = "SELECT c FROM Categoria c WHERE c.oid = :oid"),
    @NamedQuery(name = "Categoria.findByTitulo", query = "SELECT c FROM Categoria c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "Categoria.findByDescricao", query = "SELECT c FROM Categoria c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Categoria.findByDatainsercao", query = "SELECT c FROM Categoria c WHERE c.datainsercao = :datainsercao"),
    @NamedQuery(name = "Categoria.findByDataedicao", query = "SELECT c FROM Categoria c WHERE c.dataedicao = :dataedicao")})
public class Categoria implements Serializable {
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
    @OneToMany(mappedBy = "categoriaOid")
    private Collection<Ideia> ideiaCollection;

    public Categoria() {
    }

    public Categoria(Integer oid) {
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClasses.Categoria[ oid=" + oid + " ]";
    }
    
}
