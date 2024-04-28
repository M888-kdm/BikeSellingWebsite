package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Client;
import sn.ept.git.dic2.ventedevelos.facades.ClientFacade;
import sn.ept.git.dic2.ventedevelos.facades.PersonneFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/client")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ClientResource {

    @EJB
    private ClientFacade clientFacade;

    @EJB
    private PersonneFacade personneFacade;

    @GET
    @Operation(
            summary = "Liste des Clients",
            description = "Affiche la liste de tous les clients"
    )
    public List<Client> getClients(){
        return clientFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un client",
            description = "Ajoute le client s'il n'existe pas et le modifie s'il existe"
    )
    public Client addClient(Client client){
        if(client.getId() == null){
            client.setDtype("Client");
            clientFacade.create(client);
        }
        else {
            Client p = clientFacade.find(client.getId());
            client.setDtype("Client");
            if(p != null)
                clientFacade.edit(client);
            else
                clientFacade.create(client);
        }
        return client;
    }

    @GET
    @Operation(
            summary = "Trouver un Client",
            description = "Affiche le client recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "200", description = "Client trouvé")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Client client = clientFacade.find(number);
        if(client == null) {
            CustomResponse response = new CustomResponse("Le client n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(client).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un Client",
            description = "Supprime le client sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Client introuvable"),
            @ApiResponse(responseCode = "200", description = "Client supprimé")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Client client = clientFacade.find(number);
        if(client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        personneFacade.remove(client);
        CustomResponse customResponse = new CustomResponse("Le client " + client.getId() + " a été supprimé");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
