package org.example.backendip.DTOs;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ChatRequestDTO {
    @NotBlank(message = "Message is required")
    private String message;
    private List<ChatMessageDTO> history;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatMessageDTO> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessageDTO> history) {
        this.history = history;
    }
}
