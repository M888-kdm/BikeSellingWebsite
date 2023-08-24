package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Produit;
import sn.ept.git.dic2.ventedevelos.facades.ProduitFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/produit")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProduitResource {

    @EJB
    private ProduitFacade produitFacade;

    @GET
    @Operation(
            summary = "Liste des Produits",
            description = "Affiche la liste de tous les produits"
    )
    public List<Produit> getProduits(){
        System.out.println("Moussa");
        return produitFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un produit",
            description = "Ajoute le produit s'il n'existe pas et le modifie s'il existe"
    )
    public Produit addProduit(Produit produit){
        if(produit.getId() == null){
            produitFacade.create(produit);
        }
        else {
            Produit p = produitFacade.find(produit.getId());
            if(p != null)
                produitFacade.edit(produit);
            else
                produitFacade.create(produit);
        }
        return produit;
    }

    @GET
    @Operation(
            summary = "Trouver un Produit",
            description = "Affiche le produit recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Produit introuvable"),
            @ApiResponse(responseCode = "200", description = "Produit trouvé")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Produit produit = produitFacade.find(number);
        if(produit == null) {
            CustomResponse response = new CustomResponse("The product with the requested number was not found.");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(produit).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un Produit",
            description = "Supprime le produit sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Produit introuvable"),
            @ApiResponse(responseCode = "200", description = "Produit supprimé")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Produit produit = produitFacade.find(number);
        if(produit == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        produitFacade.remove(produit);
        CustomResponse customResponse = new CustomResponse("The product " + produit.getNom() + " was deleted successfully.");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
