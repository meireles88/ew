/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityClasses;

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
 * @author wolvz
 */
@Entity
@Table(name = "voto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Voto.findAll", query = "SELECT v FROM Voto v"),
    @NamedQuery(name = "Voto.findByOid", query = "SELECT v FROM Voto v WHERE v.oid = :oid"),
        @NamedQuery(name = "Voto.findByUserAndIdeia", query = "SELECT v FROM Voto v WHERE v.userOid = :userOid AND v.ideiaOid = :ideiaOid"),
        @NamedQuery(name = "Voto.findVotosUser", query = "SELECT v.ideiaOid FROM Voto v WHERE v.userOid = :userOid")
})
public class Voto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @JoinColumn(name = "user_oid", referencedColumnName = "oid")
    @ManyToOne
    private User userOid;
    @JoinColumn(name = "ideia_oid", referencedColumnName = "oid")
    @ManyToOne
    private Ideia ideiaOid;

    public Voto() {
    }

    public Voto(Integer oid) {
        this.oid = oid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public User getUserOid() {
        return userOid;
    }

    public void setUserOid(User userOid) {
        this.userOid = userOid;
    }

    public Ideia getIdeiaOid() {
        return ideiaOid;
    }

    public void setIdeiaOid(Ideia ideiaOid) {
        this.ideiaOid = ideiaOid;
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
        if (!(object instanceof Voto)) {
            return false;
        }
        Voto other = (Voto) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityClasses.Voto[ oid=" + oid + " ]";
    }
    
}
