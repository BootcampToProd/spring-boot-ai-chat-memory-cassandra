package com.bootcamptoprod.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.cassandra.CassandraChatMemory;
import org.springframework.ai.chat.memory.cassandra.CassandraChatMemoryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        return chatClientBuilder.defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory)).build();
    }

    @Bean
    public ChatMemory cassandraChatMemory(CqlSession cqlSession) {

        // Creates a CassandraChatMemory instance using a configuration builder.
        // Needs the active CqlSession to interact with the database.
        return CassandraChatMemory.create(CassandraChatMemoryConfig.builder()

                // Specifies the keyspace where the chat memory table should reside or be created.
                // By default, CassandraChatMemory uses a keyspace named "springframework"
                // If the keyspace and table doesn't exist, Spring AI will attempt to create it
                .withKeyspaceName("my_local_app_keyspace")

                // Provide the active CqlSession used for interacting with the Cassandra database
                .withCqlSession(cqlSession)

                // Optional: Specify a custom table name. Defaults to "ai_chat_memory" if not set.
                // .withTableName("custom_chat_table")

                // Optional: Set a Time-To-Live for chat entries.
                // TTL is applied per-entry; Cassandra removes the entry after the duration expires from its insertion time.
                // .withTimeToLive(Duration.ofMinutes(60)) // e.g., corresponding entry will expire  after 60 minutes
                .build());
    }
}
