/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityClasses;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author wolvz
 */
@Entity
@Table(name = "`group`")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Group1.findAll", query = "SELECT g FROM Group1 g"),
    @NamedQuery(name = "Group1.findByOid", query = "SELECT g FROM Group1 g WHERE g.oid = :oid"),
    @NamedQuery(name = "Group1.findByGroupname", query = "SELECT g FROM Group1 g WHERE g.groupname = :groupname")
})

public class Group1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Size(max = 255)
    @Column(name = "groupname")
    private String groupname;
    @JoinTable(name = "user_group", joinColumns = {
        @JoinColumn(name = "group_oid", referencedColumnName = "oid")}, inverseJoinColumns = {
        @JoinColumn(name = "user_oid", referencedColumnName = "oid")})
    @ManyToMany
    private Collection<User> userCollection;
    @JoinTable(name = "group_module", joinColumns = {
        @JoinColumn(name = "group_oid", referencedColumnName = "oid")}, inverseJoinColumns = {
        @JoinColumn(name = "module_oid", referencedColumnName = "oid")})
    @ManyToMany
    private Collection<Module> moduleCollection;
    @JoinColumn(name = "module_oid", referencedColumnName = "oid")
    @ManyToOne
    private Module moduleOid;
    @OneToMany(mappedBy = "groupOid")
    private Collection<User> userCollection1;

    public Group1() {
    }

    public Group1(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Module> getModuleCollection() {
        return moduleCollection;
    }

    public void setModuleCollection(Collection<Module> moduleCollection) {
        this.moduleCollection = moduleCollection;
    }

    public Module getModuleOid() {
        return moduleOid;
    }

    public void setModuleOid(Module moduleOid) {
        this.moduleOid = moduleOid;
    }

    @XmlTransient
    public Collection<User> getUserCollection1() {
        return userCollection1;
    }

    public void setUserCollection1(Collection<User> userCollection1) {
        this.userCollection1 = userCollection1;
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
        if (!(object instanceof Group1)) {
            return false;
        }
        Group1 other = (Group1) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClasses.Group1[ oid=" + oid + " ]";
    }
    
}
