package ma.emsi.gabbas.tp3gabbasyahya.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

import dev.langchain4j.service.AiServices;

public class LlmClientForGuideTouristique {

    private GuideTouristique guideTouristique;

    // Constructeur - remplace @PostConstruct
    public LlmClientForGuideTouristique() {
        String apiKey = System.getenv("GEMINI_KEY");

        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalStateException("La variable d'environnement GEMINI_KEY n'est pas définie");
        }

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3)
                .build();

        guideTouristique = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
    }

    public String obtenirInfosTouristiques(String lieu, int nombreEndroits) {
        return guideTouristique.obtenirInfosTouristiques(lieu, nombreEndroits);
    }
}
