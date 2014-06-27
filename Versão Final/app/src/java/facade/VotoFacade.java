/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entityClasses.Ideia;
import entityClasses.User;
import entityClasses.Voto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wolvz
 */
@Stateless
public class VotoFacade extends AbstractFacade<Voto> {
    @PersistenceContext(unitName = "EWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VotoFacade() {
        super(Voto.class);
    }
    
    public Voto findVoto(Voto v) { 
        Voto vo = null;
        try {
        vo = (Voto)em.createNamedQuery("Voto.findByUserAndIdeia").setParameter("userOid",v.getUserOid()).setParameter("ideiaOid", v.getIdeiaOid()).getSingleResult(); 
        }
        catch(Exception e){}
        return vo;
    }
    
    public List<Ideia> findVotosUser(User u) {
        List<Ideia> list = new ArrayList<>();
        try{
            list = (List<Ideia>)em.createNamedQuery("Voto.findVotosUser").setParameter("userOid", u).getResultList();
        }
        catch(Exception e){}

        return list;
    }
}
