package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Stock;
import sn.ept.git.dic2.ventedevelos.entities.StockPK;
import sn.ept.git.dic2.ventedevelos.facades.StockFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/stock")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StockResource {

    @EJB
    private StockFacade stockFacade;

    @GET
    @Operation(
            summary = "Liste des Stocks",
            description = "Affiche la liste de tous les stocks"
    )
    public List<Stock> getStocks(){
        return stockFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un stock",
            description = "Ajoute le stock s'il n'existe pas et la modifie s'il existe"
    )
    public Stock addStock(Stock stock){
        if(stock.getStockPK() == null){
            stockFacade.create(stock);
        }
        else {
            Stock p = stockFacade.find(stock.getStockPK());
            if(p != null)
                stockFacade.edit(stock);
            else
                stockFacade.create(stock);
        }
        return stock;
    }

    @GET
    @Operation(
            summary = "Trouver un Stock",
            description = "Affiche le stock recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Stock introuvable"),
            @ApiResponse(responseCode = "200", description = "Stock trouvé")
    })
    @Path("{magasin}/{product}")
    public Response find(@PathParam("magasin") Integer magasin, @PathParam("product") Integer produit){
        StockPK stockPK = new StockPK();

        stockPK.setMagasinId(magasin);
        stockPK.setProduitId(produit);

        Stock stock = stockFacade.find(stockPK);

        if(stock == null) {
            CustomResponse response = new CustomResponse("Le stock n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(stock).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un Stock",
            description = "Supprime le stock sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Stock introuvable"),
            @ApiResponse(responseCode = "200", description = "Stock supprimé")
    })
    @Path("{magasin}/{product}")
    public Response delete(@PathParam("magasin") Integer magasin, @PathParam("product") Integer produit) {
        StockPK stockPK = new StockPK();

        stockPK.setMagasinId(magasin);
        stockPK.setProduitId(produit);

        Stock stock = stockFacade.find(stockPK);
        if(stock == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        stockFacade.remove(stock);
        CustomResponse customResponse = new CustomResponse("Le stock " + stock.getStockPK().toString() + " a été supprimé");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
