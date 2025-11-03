package ma.emsi.gabbas.tp3gabbasyahya.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ma.emsi.gabbas.tp3gabbasyahya.service.LlmClientForGuideTouristique;

@Path("/guide")
@Produces(MediaType.APPLICATION_JSON)
public class GuideTouristiquResource {

    private final LlmClientForGuideTouristique llmClient;

    // Constructeur
    public GuideTouristiquResource() {
        this.llmClient = new LlmClientForGuideTouristique();
    }

    @GET
    @Path("/lieu/{lieu}")
    public Response obtenirInfos(
            @PathParam("lieu") String lieu,
            @QueryParam("nombre") @DefaultValue("2") int nombre) {
        try {
            // Validation du nombre de lieux
            if (nombre <= 0 || nombre > 20) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erreur\": \"Le paramètre 'nombre' doit être entre 1 et 20.\"}")
                        .build();
            }

            // Appel du LLM pour obtenir les informations
            String infosJson = llmClient.obtenirInfosTouristiques(lieu, nombre);

            // Retourner la réponse JSON directement
            return Response.ok(infosJson)
                    .header("Access-Control-Allow-Origin", "*") // Permet les appels cross-origin
                    .build();

        } catch (Exception e) {
            e.printStackTrace(); // Log l'erreur côté serveur
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erreur\": \"Une erreur interne est survenue.\"}")
                    .build();
        }
    }
}