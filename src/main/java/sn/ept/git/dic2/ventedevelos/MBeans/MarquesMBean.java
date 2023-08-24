package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Marque;
import sn.ept.git.dic2.ventedevelos.facades.MarqueFacade;

import java.io.Serializable;
import java.util.List;

@Named("marqueMBean")
@ViewScoped
public class MarquesMBean implements Serializable {

    @EJB
    private MarqueFacade marqueFacade;

    @PostConstruct
    public void init(){
        this.marques = marqueFacade.findAll();
    }

    private List<Marque> marques;

    private Marque marque;

    public void openNew() {
        this.marque = new Marque();
    }

    public List<Marque> getMarques() {
        if(marques == null)
            marques = marqueFacade.findAll();
        return marques;
    }

    public void save(){
        if(marque.getId() == null){
            marqueFacade.create(marque);
            marques.add(marque);
        }
        else {
            marqueFacade.edit(marque);
        }
        this.marque = new Marque();
        PrimeFaces.current().executeScript("PF('manageMarqueDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-marques");
    }

    public void deleteMarque(){
        marques.remove(marque);
        marqueFacade.remove(marque);
        this.marque = null;
        PrimeFaces.current().ajax().update("form:dt-marques");
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

}
