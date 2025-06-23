package nl.cbos.monitoringbuddy;

import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.WebSocket;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@WebSocket(path = "/chatbot")
public class ChatBotWebSocket {

    private final ChatBotService chatBotService;

    private final Logger log;

    public ChatBotWebSocket(ChatBotService chatBotService, Logger log) {
        this.chatBotService = chatBotService;
        this.log = log;
    }

    @OnOpen
    public String onOpen() {
        return "How can I help you today?";
    }

    @OnTextMessage
    public Multi<String> onTextMessage(String message) {
        log.infof("Chat message from user: %s", message);
        return chatBotService.chat(message);
    }
}
