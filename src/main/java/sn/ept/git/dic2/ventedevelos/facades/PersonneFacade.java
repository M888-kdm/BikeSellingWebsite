package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Personne;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class PersonneFacade extends AbstractFacade<Personne> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public PersonneFacade() {
        super(Personne.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
