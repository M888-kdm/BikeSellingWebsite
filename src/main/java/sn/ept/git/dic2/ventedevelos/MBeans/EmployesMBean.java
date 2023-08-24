package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Employe;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventedevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("employeMBean")
@ViewScoped
public class EmployesMBean implements Serializable {

    @EJB
    private EmployeFacade employeFacade;

    @EJB
    private MagasinFacade magasinFacade;

    @PostConstruct
    public void init(){
        this.employe = new Employe();
    }
    private List<Employe> employes;

    private Integer magasinId;

    private Integer managerId;

    private Employe employe;

    public void openNew() {
        this.employe = new Employe();
    }

    public List<Employe> getEmployes() {
        if(employes == null)
            employes = employeFacade.findAll();
        return employes;
    }

    public void save(){
        Employe manager = employeFacade.find(managerId);
        Magasin magasin = magasinFacade.find(magasinId);

        employe.setManager(manager);
        employe.setMagasin(magasin);

        employe.setDtype("Employe");

        if(employe.getId() == null){
            employeFacade.create(employe);
            employes.add(employe);
        }
        else {
            employeFacade.edit(employe);
        }

        this.employe = new Employe();
        this.managerId = null;
        this.magasinId = null;
        PrimeFaces.current().executeScript("PF('manageEmployeDialog').hide()");
    }

    public void deleteEmploye(){
        employes.remove(employe);
        employeFacade.remove(employe);
        this.employe = null;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe Employe) {
        this.employe = Employe;
    }

    public Integer getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Integer magasinId) {
        this.magasinId = magasinId;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public void setEmployes(List<sn.ept.git.dic2.ventedevelos.entities.Employe> employes) {
        this.employes = employes;
    }
}
