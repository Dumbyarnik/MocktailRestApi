package de.hsos.swa.mocktail.suchen.boundary.rest;

import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import de.hsos.swa.mocktail.suchen.entity.Mocktail;
import de.hsos.swa.mocktail.suchen.gateway.MocktailKatalog;

@Path("/mocktails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KatalogResource {
    // Inject - for injecting a singleton object (so there will be only one)
    @Inject
    MocktailKatalog mocktailRepository;
    
    //https://www.bookstack.cn/read/quarkus-v1.0-en/e90b3bef83a63831.md

    // PostConstruct - is used on a method that needs to be executed 
    // after dependency injection is done to perform any initialization
    @PostConstruct
    public void init(){
        this.mocktailRepository.add("Tequila Sunrise");
        this.mocktailRepository.addZutat("1", "Wasser");
        this.mocktailRepository.add("Mojito");
    }

    /////////////////////////// Get Methods ///////////////////////////////////////

    @GET
    @Operation(summary = "Zeigt alle Mocktails")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))))
     public Collection<Mocktail> getMocktails() {  
        return this.mocktailRepository.getAll();
    }

    @GET @Path("{id}")
    @Operation(summary = "Zeigt einen Mocktail")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))),
        }
    )
    public Response getMocktailId(@PathParam("id")String id){
        Optional<Mocktail> op = this.mocktailRepository.findById(id);
        if(op != null){
            return Response.ok(op.get()).build();
        } else {
            return Response.status(404).entity("Mocktail not found").type("text/plain").build();
        }
    }


    /////////////////////////// Post Methods ///////////////////////////////////////

    @POST 
    @Operation(summary = "Leider nicht verfügbar")
    public Response createMocktail() {
        return Response.status(Status.NOT_IMPLEMENTED)
            .entity("Ein Name des Mocktails wird gebraucht")
            .type("text/plain").build();                            
    }

    @POST @Path("{mocktail}")
    @Operation(summary = "Erstellt einen neuen Mocktail")
    public Response createMocktailId(@PathParam("mocktail") String mocktail) {
        Mocktail wtemp = this.mocktailRepository.add(mocktail);
        System.out.println(wtemp.toString());
        return Response.status(Status.CREATED).entity(wtemp).build();                            
    }

    /////////////////////////// Update Methods ///////////////////////////////////////

    @PUT
    @Operation(summary = "Leider nicht verfügbar")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))),
        }
    )
    public Response updateMocktail() {
        return Response.status(Status.NOT_IMPLEMENTED)
            .entity("Mocktail Id wird gebraucht")
            .type("text/plain").build();                                  
    }

    @PUT @Path("{id}")
    @Operation(summary = "Fügt eine Zutat zu dem Mocktail hinzu")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class))),
        }
    )
    public Response updateMocktailId(@PathParam("id")String id, @QueryParam("Zutat") String text) {
        Optional<Mocktail> op = this.mocktailRepository.addZutat(id, text);

        if(op != null){
            return Response.ok(op.get()).build();
        } else {
            return Response.status(404).entity("Mocktail not found").type("text/plain").build();
        }                                      
    }


    /////////////////////////// Delete Methods ///////////////////////////////////////

    @DELETE
    @Operation(summary = "Leider nicht verfügbar")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class)))
    })
    public Response deleteMocktail(){
        return Response.status(Status.NOT_IMPLEMENTED)
            .entity("Mocktail Id wird gebraucht")
            .type("text/plain").build();
    }

    @DELETE @Path("{id}")
    @Operation(summary = "Löscht einen Mocktail")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = Mocktail.class)))
    })
    public Response deleteMocktailId(@PathParam("id")String id){
        boolean tmp = this.mocktailRepository.delete(id);

        if (tmp) {
            return Response.ok().build();
        } else {
            return Response.status(404).entity("Mocktail not found").type("text/plain").build();
        }
    }

}
