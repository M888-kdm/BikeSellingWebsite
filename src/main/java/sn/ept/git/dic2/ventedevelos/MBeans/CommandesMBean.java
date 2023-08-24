package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Client;
import sn.ept.git.dic2.ventedevelos.entities.Commande;
import sn.ept.git.dic2.ventedevelos.entities.Employe;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.facades.ClientFacade;
import sn.ept.git.dic2.ventedevelos.facades.CommandeFacade;
import sn.ept.git.dic2.ventedevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventedevelos.facades.MagasinFacade;

import java.io.Serializable;
import java.util.List;

@Named("commandeMBean")
@ViewScoped
public class CommandesMBean implements Serializable {

    @EJB
    private CommandeFacade commandeFacade;

    @EJB
    private ClientFacade clientFacade;

    @EJB
    private MagasinFacade magasinFacade;

    @EJB
    EmployeFacade employeFacade;

    @PostConstruct
    public void init(){
        this.commande = new Commande();
    }
    private List<Commande> commandes;

    private Commande commande;

    private Integer clientId;
    private Integer magasinId;
    private Integer employeId;

    public void openNew() {
        this.commande = new Commande();
    }

    public List<Commande> getCommandes() {
        if(commandes == null)
            commandes = commandeFacade.findAll();
        return commandes;
    }

    public void save(){

        Client client = clientFacade.find(clientId);
        Magasin magasin = magasinFacade.find(magasinId);
        Employe employe = employeFacade.find(employeId);

        commande.setClientId(client);
        commande.setMagasinId(magasin);
        commande.setVendeurId(employe);

        if(commande.getNumero() == null){
            commandeFacade.create(commande);
        }
        else {
            commandeFacade.edit(commande);
        }
        this.commande = new Commande();
        PrimeFaces.current().ajax().update("form:dt-commandes");
    }

    public void deleteCommande(){
        commandes.remove(commande);
        commandeFacade.remove(commande);
        this.commande = null;
        PrimeFaces.current().ajax().update("form:dt-commandes");
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(Integer magasinId) {
        this.magasinId = magasinId;
    }

    public Integer getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Integer employeId) {
        this.employeId = employeId;
    }
}
