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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "ideia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ideia.findAll", query = "SELECT i FROM Ideia i"),
    @NamedQuery(name = "Ideia.findByOid", query = "SELECT i FROM Ideia i WHERE i.oid = :oid"),
    @NamedQuery(name = "Ideia.findByTitulo", query = "SELECT i FROM Ideia i WHERE i.titulo = :titulo"),
    @NamedQuery(name = "Ideia.findByDescricao", query = "SELECT i FROM Ideia i WHERE i.descricao = :descricao"),
    @NamedQuery(name = "Ideia.findByDatainsercao", query = "SELECT i FROM Ideia i WHERE i.datainsercao = :datainsercao"),
    @NamedQuery(name = "Ideia.findByDataedicao", query = "SELECT i FROM Ideia i WHERE i.dataedicao = :dataedicao"),
    @NamedQuery(name = "Ideia.findByRating", query = "SELECT i FROM Ideia i WHERE i.rating = :rating"),
    @NamedQuery(name = "Ideia.findByQuantia", query = "SELECT i FROM Ideia i WHERE i.quantia = :quantia"),
    @NamedQuery(name = "Ideia.findByAprovada", query = "SELECT i FROM Ideia i WHERE i.aprovada = :aprovada"),
    @NamedQuery(name = "Ideia.findUserIdeias", query = "SELECT i FROM Ideia i WHERE i.userOid = :userOid ")})
public class Ideia implements Serializable {
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
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "quantia")
    private Integer quantia;
    @Column(name = "aprovada")
    private Boolean aprovada;
    @JoinTable(name = "tag_ideia", joinColumns = {
        @JoinColumn(name = "ideiaoid", referencedColumnName = "oid")}, inverseJoinColumns = {
        @JoinColumn(name = "tagoid", referencedColumnName = "oid")})
    @ManyToMany
    private Collection<Tag> tagCollection;
    @OneToMany(mappedBy = "ideiaOid")
    private Collection<Voto> votoCollection;
    @JoinColumn(name = "user_oid", referencedColumnName = "oid")
    @ManyToOne
    private User userOid;
    @JoinColumn(name = "grupoorcamental_oid", referencedColumnName = "oid")
    @ManyToOne
    private Grupoorcamental grupoorcamentalOid;
    @JoinColumn(name = "categoria_oid", referencedColumnName = "oid")
    @ManyToOne
    private Categoria categoriaOid;

    public Ideia() {
    }

    public Ideia(Integer oid) {
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getQuantia() {
        return quantia;
    }

    public void setQuantia(Integer quantia) {
        this.quantia = quantia;
    }

    public Boolean getAprovada() {
        return aprovada;
    }

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    @XmlTransient
    public Collection<Tag> getTagCollection() {
        return tagCollection;
    }

    public void setTagCollection(Collection<Tag> tagCollection) {
        this.tagCollection = tagCollection;
    }

    @XmlTransient
    public Collection<Voto> getVotoCollection() {
        return votoCollection;
    }

    public void setVotoCollection(Collection<Voto> votoCollection) {
        this.votoCollection = votoCollection;
    }

    public User getUserOid() {
        return userOid;
    }

    public void setUserOid(User userOid) {
        this.userOid = userOid;
    }

    public Grupoorcamental getGrupoorcamentalOid() {
        return grupoorcamentalOid;
    }

    public void setGrupoorcamentalOid(Grupoorcamental grupoorcamentalOid) {
        this.grupoorcamentalOid = grupoorcamentalOid;
    }

    public Categoria getCategoriaOid() {
        return categoriaOid;
    }

    public void setCategoriaOid(Categoria categoriaOid) {
        this.categoriaOid = categoriaOid;
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
        if (!(object instanceof Ideia)) {
            return false;
        }
        Ideia other = (Ideia) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityclasses.Ideia[ oid=" + oid + " ]";
    }
    
}
