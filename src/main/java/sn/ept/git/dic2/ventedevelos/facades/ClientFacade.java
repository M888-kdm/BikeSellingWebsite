package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Client;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
