package sn.ept.git.dic2.ventedevelos.MBeans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.ventedevelos.entities.Client;
import sn.ept.git.dic2.ventedevelos.facades.ClientFacade;

import java.io.Serializable;
import java.util.List;

@Named("clientMBean")
@ViewScoped
public class ClientsMBean implements Serializable {

    @EJB
    private ClientFacade clientFacade;

    @PostConstruct
    public void init(){
        this.client = new Client();
    }
    private List<Client> clients;

    private Client client;

    public void openNew() {
        this.client = new Client();
    }

    public List<Client> getClients() {
        if(clients == null)
            clients = clientFacade.findAll();
        return clients;
    }

    public void save(){
        client.setDtype("Client");
        if(client.getId() == null){
            clientFacade.create(client);
            clients.add(client);
        }
        else {
            clientFacade.edit(client);
        }
        this.client = new Client();
        PrimeFaces.current().executeScript("PF('manageClientDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-clients");
    }

    public void deleteClient(){
        clients.remove(client);
        clientFacade.remove(client);
        this.client = null;
        PrimeFaces.current().ajax().update("form:dt-clients");
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
