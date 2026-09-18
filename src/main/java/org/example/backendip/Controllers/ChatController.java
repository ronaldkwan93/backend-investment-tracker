package org.example.backendip.Controllers;

import jakarta.validation.Valid;
import org.example.backendip.DTOs.ChatMessageDTO;
import org.example.backendip.DTOs.ChatRequestDTO;
import org.example.backendip.DTOs.ChatResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping
    public ResponseEntity<ChatResponseDTO> chat(@RequestBody @Valid ChatRequestDTO request) {
        List<Message> messages = new ArrayList<>();
        if (request.getHistory() != null) {
            for (ChatMessageDTO turn : request.getHistory()) {
                if ("assistant".equalsIgnoreCase(turn.getRole())) {
                    messages.add(new AssistantMessage(turn.getContent()));
                } else {
                    messages.add(new UserMessage(turn.getContent()));
                }
            }
        }
        messages.add(new UserMessage(request.getMessage()));

        String reply = chatClient.prompt(new Prompt(messages))
                .call()
                .content();

        return ResponseEntity.ok(new ChatResponseDTO(reply));
    }
}
