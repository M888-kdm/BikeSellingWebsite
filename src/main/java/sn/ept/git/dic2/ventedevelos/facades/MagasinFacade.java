package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class MagasinFacade extends AbstractFacade<Magasin> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public MagasinFacade() {
        super(Magasin.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
