package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Magasin;
import sn.ept.git.dic2.ventedevelos.facades.MagasinFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/magasin")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MagasinResource {

    @EJB
    private MagasinFacade magasinFacade;

    @GET
    @Operation(
            summary = "Liste des Magasins",
            description = "Affiche la liste de tous les magasins"
    )
    public List<Magasin> getMagasins(){
        return magasinFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un magasin",
            description = "Ajoute le magasin s'il n'existe pas et le modifie s'il existe"
    )
    public Magasin addMagasin(Magasin magasin){
        if(magasin.getId() == null){
            magasinFacade.create(magasin);
        }
        else {
            Magasin p = magasinFacade.find(magasin.getId());
            if(p != null)
                magasinFacade.edit(magasin);
            else
                magasinFacade.create(magasin);
        }
        return magasin;
    }

    @GET
    @Operation(
            summary = "Trouver un Magasin",
            description = "Affiche le magasin recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Magasin introuvable"),
            @ApiResponse(responseCode = "200", description = "Magasin trouvé")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Magasin magasin = magasinFacade.find(number);
        if(magasin == null) {
            CustomResponse response = new CustomResponse("Le magasin n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(magasin).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un Magasin",
            description = "Supprime le magasin sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Magasin introuvable"),
            @ApiResponse(responseCode = "200", description = "Magasin supprimé")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Magasin magasin = magasinFacade.find(number);
        if(magasin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        magasinFacade.remove(magasin);
        CustomResponse customResponse = new CustomResponse("Le magasin " + magasin.getId() + " a été supprimé");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
