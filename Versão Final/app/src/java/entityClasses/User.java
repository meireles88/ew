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
import javax.persistence.Lob;
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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByOid", query = "SELECT u FROM User u WHERE u.oid = :oid"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByDatanascimento", query = "SELECT u FROM User u WHERE u.datanascimento = :datanascimento"),
    @NamedQuery(name = "User.findByMorada", query = "SELECT u FROM User u WHERE u.morada = :morada"),
    @NamedQuery(name = "User.findByFoto", query = "SELECT u FROM User u WHERE u.foto = :foto"),
    @NamedQuery(name = "User.findByIp", query = "SELECT u FROM User u WHERE u.ip = :ip"),
    @NamedQuery(name = "User.findByDatacriacao", query = "SELECT u FROM User u WHERE u.datacriacao = :datacriacao"),
    @NamedQuery(name = "User.findByDataultimoacesso", query = "SELECT u FROM User u WHERE u.dataultimoacesso = :dataultimoacesso"),
    @NamedQuery(name = "User.findByDataultimaedicao", query = "SELECT u FROM User u WHERE u.dataultimaedicao = :dataultimaedicao"),
    @NamedQuery(name = "Utilizador.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Size(max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    @Size(max = 255)
    @Column(name = "morada")
    private String morada;
    @Size(max = 255)
    @Column(name = "foto")
    private String foto;
    @Size(max = 255)
    @Column(name = "ip")
    private String ip;
    @Column(name = "datacriacao")
    @Temporal(TemporalType.DATE)
    private Date datacriacao;
    @Column(name = "dataultimoacesso")
    @Temporal(TemporalType.DATE)
    private Date dataultimoacesso;
    @Column(name = "dataultimaedicao")
    @Temporal(TemporalType.DATE)
    private Date dataultimaedicao;
    @ManyToMany(mappedBy = "userCollection")
    private Collection<Group1> group1Collection;
    @OneToMany(mappedBy = "userOid")
    private Collection<Voto> votoCollection;
    @OneToMany(mappedBy = "userOid")
    private Collection<Ideia> ideiaCollection;
    @JoinColumn(name = "group_oid", referencedColumnName = "oid")
    @ManyToOne
    private Group1 groupOid;
    @Column(name = "data_ban")
    @Temporal(TemporalType.DATE)
    private Date databan;

    public User() {
    }

    public User(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public Date getDataultimoacesso() {
        return dataultimoacesso;
    }

    public void setDataultimoacesso(Date dataultimoacesso) {
        this.dataultimoacesso = dataultimoacesso;
    }

    public Date getDataultimaedicao() {
        return dataultimaedicao;
    }

    public void setDataultimaedicao(Date dataultimaedicao) {
        this.dataultimaedicao = dataultimaedicao;
    }

    public Date getDataban() {
        return databan;
    }

    public void setDataban(Date databan) {
        this.databan = databan;
    }
    
    

    @XmlTransient
    public Collection<Group1> getGroup1Collection() {
        return group1Collection;
    }

    public void setGroup1Collection(Collection<Group1> group1Collection) {
        this.group1Collection = group1Collection;
    }

    @XmlTransient
    public Collection<Voto> getVotoCollection() {
        return votoCollection;
    }

    public void setVotoCollection(Collection<Voto> votoCollection) {
        this.votoCollection = votoCollection;
    }

    @XmlTransient
    public Collection<Ideia> getIdeiaCollection() {
        return ideiaCollection;
    }

    public void setIdeiaCollection(Collection<Ideia> ideiaCollection) {
        this.ideiaCollection = ideiaCollection;
    }

    public Group1 getGroupOid() {
        return groupOid;
    }

    public void setGroupOid(Group1 groupOid) {
        this.groupOid = groupOid;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClasses.User[ oid=" + oid + " ]";
    }
    
}
