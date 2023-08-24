package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Categorie;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public CategorieFacade() {
        super(Categorie.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
