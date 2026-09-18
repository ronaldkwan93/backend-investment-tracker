package org.example.backendip.Config;

import org.example.backendip.Services.PropertyAiTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpToolConfig {

    @Bean
    public ToolCallbackProvider propertyTools(PropertyAiTools propertyAiTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(propertyAiTools)
                .build();
    }
}
