package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Employe;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class EmployeFacade extends AbstractFacade<Employe> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public EmployeFacade() {
        super(Employe.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
