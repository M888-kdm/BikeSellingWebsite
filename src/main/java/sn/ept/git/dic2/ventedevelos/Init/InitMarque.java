package sn.ept.git.dic2.ventedevelos.Init;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sn.ept.git.dic2.ventedevelos.entities.Categorie;
import sn.ept.git.dic2.ventedevelos.entities.Employe;
import sn.ept.git.dic2.ventedevelos.entities.Marque;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.facades.ClientFacade;
import sn.ept.git.dic2.ventedevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventedevelos.facades.PersonneFacade;

import java.math.BigDecimal;
import java.util.List;


@Singleton
@Startup
public class InitMarque {

    @PersistenceContext(name = "projectPU")
    private EntityManager entityManager;

    @EJB
    private EmployeFacade employeFacade;

    @PostConstruct
    public void init(){
    }

}
