package sn.ept.git.dic2.ventedevelos.facades;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventedevelos.utils.AbstractFacade;
@Stateless
public class ArticleCommandeFacade extends AbstractFacade<ArticleCommande> {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    public ArticleCommandeFacade() {
        super(ArticleCommande.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
