package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class ProduitFacade extends AbstractFacade<Produit> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public ProduitFacade() {
        super(Produit.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
