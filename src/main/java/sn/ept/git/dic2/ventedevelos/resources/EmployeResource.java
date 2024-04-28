package sn.ept.git.dic2.ventedevelos.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sn.ept.git.dic2.ventedevelos.entities.Employe;
import sn.ept.git.dic2.ventedevelos.facades.EmployeFacade;
import sn.ept.git.dic2.ventedevelos.facades.PersonneFacade;
import sn.ept.git.dic2.ventedevelos.utils.CustomResponse;

import java.util.List;

@Path("/employe")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class EmployeResource {

    @EJB
    private EmployeFacade employeFacade;

    @EJB
    private PersonneFacade personneFacade;

    @GET
    @Operation(
            summary = "Liste des employés",
            description = "Affiche la liste de tous les employés"
    )
    public List<Employe> getEmployes(){
        return employeFacade.findAll();
    }


    @PUT
    @Operation(
            summary = "Ajouter ou Modifier un employé",
            description = "Ajoute l'employé s'il n'existe pas et le modifie s'il existe"
    )
    public Employe addEmploye(Employe employe){
        if(employe.getId() == null){
            employe.setDtype("Employe");
            employeFacade.create(employe);
        }
        else {
            Employe p = employeFacade.find(employe.getId());
            employe.setDtype("Employe");
            if(p != null)
                employeFacade.edit(employe);
            else
                employeFacade.create(employe);
        }
        return employe;
    }

    @GET
    @Operation(
            summary = "Trouver un Employé",
            description = "Affiche l'employé recherché"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Employé introuvable"),
            @ApiResponse(responseCode = "200", description = "Employé trouvé")
    })
    @Path("{number}")
    public Response find(@PathParam("number") Integer number){
        Employe employe = employeFacade.find(number);
        if(employe == null) {
            CustomResponse response = new CustomResponse("L'employé n'a pas été trouvé");
            return Response.status(Response.Status.NOT_FOUND).entity(response).build();
        }
        return Response.status(Response.Status.OK).entity(employe).build();
    }

    @DELETE
    @Operation(
            summary = "Supprimer un Employé",
            description = "Supprime le employé sélectionné"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Employé introuvable"),
            @ApiResponse(responseCode = "200", description = "Employé supprimé")
    })
    @Path("{number}")
    public Response delete(@PathParam("number") Integer number) {
        Employe employe = employeFacade.find(number);
        if(employe == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        personneFacade.remove(employe);
        CustomResponse customResponse = new CustomResponse("L'employé " + employe.getId() + " a été supprimé");
        return Response.status(Response.Status.OK).entity(customResponse).build();
    }

}
