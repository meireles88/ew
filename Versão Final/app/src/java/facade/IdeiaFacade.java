/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entityClasses.Ideia;
import entityClasses.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wolvz
 */
@Stateless
public class IdeiaFacade extends AbstractFacade<Ideia> {
    @PersistenceContext(unitName = "EWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdeiaFacade() {
        super(Ideia.class);
    }
    
    public List<Ideia> findApproved() {
        return (List<Ideia>)em.createNamedQuery("Ideia.findByAprovada").setParameter("aprovada", true).getResultList();
    }
    
    public List<Ideia> findUserIdeias(User u) {
        return (List<Ideia>)em.createNamedQuery("Ideia.findUserIdeias").setParameter("userOid", u).getResultList();
    }

    public List findNotApproved() {
    return (List<Ideia>)em.createNamedQuery("Ideia.findByAprovada").setParameter("aprovada", false).getResultList();    
    }
}
