package org.example.backendip;

import org.example.backendip.Controllers.PropertyController;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BackendIpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendIpApplication.class, args);
    }

    @Bean
    public List<ToolCallback> tools(PropertyController propertyTools) {
        return List.of(ToolCallbacks.from(propertyTools));
    }
}
