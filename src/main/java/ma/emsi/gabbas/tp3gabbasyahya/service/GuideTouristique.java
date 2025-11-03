package ma.emsi.gabbas.tp3gabbasyahya.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

    @UserMessage("Tu es un assistant qui fournit des informations touristiques.\n" +
            "\n" +
            "Fournis exactement {{nombre}} endroits à visiter pour le lieu : {{lieu}}.\n" +
            "Donne aussi le prix moyen d'un repas dans la devise locale.\n" +
            "\n" +
            "Réponds TOUJOURS et UNIQUEMENT avec un objet JSON.\n" +
            "N'utilise jamais de Markdown ou de formatage de texte.\n" +
            "\n" +
            "Le format JSON doit être EXACTEMENT le suivant :\n" +
            "{\n" +
            "  \"ville_ou_pays\": \"nom du lieu\",\n" +
            "  \"endroits_a_visiter\": [\"endroit 1\", \"endroit 2\", ...],\n" +
            "  \"prix_moyen_repas\": \"<prix moyen> <devise>\"\n" +
            "}\n")
    String obtenirInfosTouristiques(@V("lieu") String lieu, @V("nombre") int nombreEndroits);
}
