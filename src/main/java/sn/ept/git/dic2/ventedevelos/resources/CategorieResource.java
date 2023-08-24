package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Categorie;
import sn.ept.git.dic2.ventedevelos.facades.CategorieFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/categorie")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CategorieResource {

    @EJB
    private CategorieFacade categorieFacade;

    @GET
    @Operation(
            summary = "Liste des catégories",
            description = "Affiche la liste de toutes les categories"
    )
    public List<Categorie> getCategories(){
        return categorieFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier une catégorie",
            description = "Ajoute la catégorie si elle n'existe pas et la modifie si elle existe"
    )
    public Categorie addCategorie(Categorie categorie){
        if(categorie.getId() == null){
            categorieFacade.create(categorie);
        }
        else {
            Categorie p = categorieFacade.find(categorie.getId());
            if(p != null)
                categorieFacade.edit(categorie);
            else
                categorieFacade.create(categorie);
        }
        return categorie;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(
            summary = "Trouver une catégorie",
            description = "Affiche la catégorie recherchée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Catégorie introuvable"),
            @ApiResponse(responseCode = "200", description = "Catégorie trouvée")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Categorie categorie = categorieFacade.find(number);
        if(categorie == null) {
            CustomResponse response = new CustomResponse("La catégorie n'a pas été trouvée");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(categorie).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer une catégorie",
            description = "Supprime la catégorie sélectionnée"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Catégorie introuvable"),
            @ApiResponse(responseCode = "200", description = "Catégorie supprimée")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Categorie categorie = categorieFacade.find(number);
        if(categorie == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categorieFacade.remove(categorie);
        CustomResponse customResponse = new CustomResponse("La catégorie " + categorie.getNom() + " a été supprimée");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
