package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Stock;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;

@Stateless
public class StockFacade extends AbstractFacade<Stock> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public StockFacade() {
        super(Stock.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
