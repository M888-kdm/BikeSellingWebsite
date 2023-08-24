package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Categorie;
import sn.ept.git.dic2.ventedevelos.entities.Client;
import sn.ept.git.dic2.ventedevelos.facades.CategorieFacade;

import java.io.Serializable;
import java.util.List;

@Named("categorieMBean")
@ViewScoped
public class CategoriesMBean implements Serializable {

    @EJB
    private CategorieFacade categorieFacade;

    private List<Categorie> categories;

    private Categorie categorie;

    public void openNew() {
        this.categorie = new Categorie();
    }

    public List<Categorie> getCategories() {
        if(categories == null)
            categories = categorieFacade.findAll();
        return categories;
    }

    public void save(){
        if(categorie.getId() == null){
            categorieFacade.create(categorie);
            categories.add(categorie);
        }
        else {
            categorieFacade.edit(categorie);
        }
        this.categorie = new Categorie();
        PrimeFaces.current().executeScript("PF('manageCategorieDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-categories");
    }

    public void deleteCategorie(){
        categories.remove(categorie);
        categorieFacade.remove(categorie);
        this.categorie = null;
        PrimeFaces.current().ajax().update("form:dt-categories");
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
