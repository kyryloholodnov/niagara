package com.github.holodnov.graph;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.holodnov.graph.generator.UINT64Generator;
import com.github.holodnov.graph.service.GraphService;
import com.github.holodnov.graph.zoo.ZooClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class ApplicationConfig {

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

    @Bean
    public UINT64Generator uint64Generator() {
        return new UINT64Generator();
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }

    @Bean(destroyMethod = "close")
    public ZooClient zooClient() throws Exception {
        return new ZooClient();
    }

    @Bean(destroyMethod = "close")
    public GraphService graphService(ZooClient zooClient, UINT64Generator uint64Generator) throws Exception {
        GraphService graphService = new GraphService(zooClient, uint64Generator);
        graphService.start();
        return graphService;
    }
}
