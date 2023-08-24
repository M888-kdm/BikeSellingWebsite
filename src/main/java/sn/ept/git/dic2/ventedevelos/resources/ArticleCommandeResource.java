package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.ArticleCommande;
import sn.ept.git.dic2.ventedevelos.entities.ArticleCommandePK;
import sn.ept.git.dic2.ventedevelos.facades.ArticleCommandeFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/articleCommande")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ArticleCommandeResource {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    @GET
    @Operation(
            summary = "Liste des articles de commandes",
            description = "Affiche la liste de tous les articles d'une commande"
    )
    public List<ArticleCommande> getArticleCommandes(){
        return articleCommandeFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un article",
            description = "Ajoute l'article s'il n'existe pas et le modifie s'il existe"
    )
    public ArticleCommande addArticleCommande(ArticleCommande articleCommande){
        if(articleCommande.getArticleCommandePK() == null) {
            articleCommandeFacade.create(articleCommande);
        }
        else {
            ArticleCommande p = articleCommandeFacade.find(articleCommande.getArticleCommandePK());
            if(p != null){
                articleCommandeFacade.edit(articleCommande);
            }
            else
                articleCommandeFacade.create(articleCommande);
        }
        return articleCommande;
    }

    @GET
    @Operation(
            summary = "Trouver un article",
            description = "Affiche l'article recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Article introuvable"),
            @ApiResponse(responseCode = "200", description = "Article trouvé")
    })
    @Path("{commande}/{ligne}")
    public Response find(@PathParam("commande") Integer commande, @PathParam("ligne") Integer ligne){
        ArticleCommandePK articleCommandePK = new ArticleCommandePK();

        articleCommandePK.setNumeroCommande(commande);
        articleCommandePK.setLigne(ligne);

        ArticleCommande articleCommande = articleCommandeFacade.find(articleCommandePK);

        if(articleCommande == null) {
            CustomResponse response = new CustomResponse("L'article n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(articleCommande).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un article",
            description = "Supprime l'article sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Article introuvable"),
            @ApiResponse(responseCode = "200", description = "Article supprimé")
    })
    @Path("{commande}/{ligne}")
    public Response delete(@PathParam("commande") Integer commande, @PathParam("ligne") Integer ligne) {
        ArticleCommandePK articleCommandePK = new ArticleCommandePK();

        articleCommandePK.setNumeroCommande(commande);
        articleCommandePK.setLigne(ligne);

        ArticleCommande articleCommande = articleCommandeFacade.find(articleCommandePK);

        if(articleCommande == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        articleCommandeFacade.remove(articleCommande);
        CustomResponse customResponse = new CustomResponse("L'article " + articleCommande.getArticleCommandePK().toString() + " a été supprimé");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
