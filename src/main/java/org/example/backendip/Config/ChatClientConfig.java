package org.example.backendip.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder, ToolCallbackProvider propertyTools) {
        return builder
                .defaultSystem("""
                        You are an assistant for an investment property tracker.
                        Only answer questions about real estate investment, properties,
                        rent, and yield, using the tools available to you.
                        If asked anything unrelated (recipes, general chit-chat, etc.),
                        politely decline and say you can only help with property tracking.
                        """)
                .defaultToolCallbacks(propertyTools)
                .build();
    }
}
