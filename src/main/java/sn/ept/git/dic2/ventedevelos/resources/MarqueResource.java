package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Marque;
import sn.ept.git.dic2.ventedevelos.facades.MarqueFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/marque")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MarqueResource {

    @EJB
    private MarqueFacade marqueFacade;

    @GET
    @Operation(
            summary = "Liste des Marques",
            description = "Affiche la liste de toutes les marques"
    )
    public List<Marque> getMarques(){
        return marqueFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un marque",
            description = "Ajoute la marque si elle n'existe pas et la modifie si elle existe"
    )
    public Marque addMarque(Marque marque){
        if(marque.getId() == null){
            marqueFacade.create(marque);
        }
        else {
            Marque p = marqueFacade.find(marque.getId()); 
            if(p != null)
                marqueFacade.edit(marque);
            else
                marqueFacade.create(marque);
        }
        return marque;
    }

    @GET
    @Operation(
            summary = "Trouver une Marque",
            description = "Affiche la marque recherchée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Marque introuvable"),
            @ApiResponse(responseCode = "200", description = "Marque trouvé")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Marque marque = marqueFacade.find(number);
        if(marque == null) {
            CustomResponse response = new CustomResponse("La marque n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(marque).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer une Marque",
            description = "Supprime la marque sélectionnée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Marque introuvable"),
            @ApiResponse(responseCode = "200", description = "Marque supprimée")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Marque marque = marqueFacade.find(number);
        if(marque == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        marqueFacade.remove(marque);
        CustomResponse customResponse = new CustomResponse("La marque " + marque.getNom() + " a été supprimée");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
