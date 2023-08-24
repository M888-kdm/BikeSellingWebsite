package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Commande;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class CommandeFacade extends AbstractFacade<Commande> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public CommandeFacade() {
        super(Commande.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
