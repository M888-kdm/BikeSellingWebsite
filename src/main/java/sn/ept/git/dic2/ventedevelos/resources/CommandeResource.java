package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Commande;
import sn.ept.git.dic2.ventedevelos.facades.CommandeFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/commande")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CommandeResource {

    @EJB
    private CommandeFacade commandeFacade;

    @GET
    @Operation(
            summary = "Liste des Commandes",
            description = "Affiche la liste de tous les commandes"
    )
    public List<Commande> getCommandes(){
        return commandeFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier une commande",
            description = "Ajoute la commande si elle n'existe pas et la modifie si elle existe"
    )
    public Commande addCommande(Commande commande){
        if(commande.getNumero() == null){
            commandeFacade.create(commande);
        }
        else {
            Commande p = commandeFacade.find(commande.getNumero());
            if(p != null)
                commandeFacade.edit(commande);
            else
                commandeFacade.create(commande);
        }
        return commande;
    }

    @GET
    @Operation(
            summary = "Trouver une Commande",
            description = "Affiche la commande recherchée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Commande introuvable"),
            @ApiResponse(responseCode = "200", description = "Commande trouvée")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Commande commande = commandeFacade.find(number);
        if(commande == null) {
            CustomResponse response = new CustomResponse("La commande n'a pas été trouvée");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(commande).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer une commande",
            description = "Supprime la commande sélectionnée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Commande introuvable"),
            @ApiResponse(responseCode = "200", description = "Commande supprimée")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Commande commande = commandeFacade.find(number);
        if(commande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commandeFacade.remove(commande);
        CustomResponse customResponse = new CustomResponse("La commande " + commande.getNumero() + " a été supprimée");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
