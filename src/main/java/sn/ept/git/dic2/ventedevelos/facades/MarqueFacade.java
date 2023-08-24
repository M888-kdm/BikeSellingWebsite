package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Marque;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class MarqueFacade extends AbstractFacade<Marque> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public MarqueFacade() {
        super(Marque.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
