package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("magasinMBean")
@ViewScoped
public class MagasinMBean implements Serializable {

    @EJB
    private MagasinFacade magasinFacade;

    @PostConstruct
    public void init(){
        this.magasin = new Magasin();
    }
    private List<Magasin> magasins;

    private Magasin magasin;

    public void openNew() {
        this.magasin = new Magasin();
    }

    public List<Magasin> getMagasins() {
        if(magasins == null)
            magasins = magasinFacade.findAll();
        return magasins;
    }

    public void save(){
        if(magasin.getId() == null){
            magasinFacade.create(magasin);
        }
        else {
            magasinFacade.edit(magasin);
        }
        this.magasin = new Magasin();
        PrimeFaces.current().executeScript("PF('manageMagasinDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-magasins");
    }

    public void deleteMagasin(){
        magasins.remove(magasin);
        magasinFacade.remove(magasin);
        this.magasin = null;
        PrimeFaces.current().ajax().update("form:dt-magasins");
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

}
