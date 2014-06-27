/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entityClasses.Tag;
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
public class TagFacade extends AbstractFacade<Tag> {
    @PersistenceContext(unitName = "EWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagFacade() {
        super(Tag.class);
    }
    
        public Tag findByNome(String uname) {
            Tag t = null;
            try{
                t = (Tag)em.createNamedQuery("Tag.findByNome").setParameter("nome", uname).getSingleResult();
            }
            catch(Exception e){}
        return t; 
    }
}
