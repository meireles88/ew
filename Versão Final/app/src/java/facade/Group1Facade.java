/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entityClasses.Group1;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wolvz
 */
@Stateless
public class Group1Facade extends AbstractFacade<Group1> {
    @PersistenceContext(unitName = "EWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Group1Facade() {
        super(Group1.class);
    }
    
    public Group1 findByGroupname(String groupname) {
        return (Group1)this.em.createNamedQuery("Group1.findByGroupname").setParameter("groupname",groupname).getSingleResult();
    }
    
}
