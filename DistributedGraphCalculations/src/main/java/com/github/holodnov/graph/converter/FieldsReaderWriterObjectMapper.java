package com.github.holodnov.graph.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;

/**
 * @author Kyrylo Holodnov
 */
public class FieldsReaderWriterObjectMapper extends ObjectMapper {

    public FieldsReaderWriterObjectMapper() {
        super();
        setVisibilityChecker(getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(ANY)
                .withGetterVisibility(NONE)
                .withSetterVisibility(NONE)
                .withCreatorVisibility(NONE));
        setVisibilityChecker(getDeserializationConfig().getDefaultVisibilityChecker().
                withFieldVisibility(ANY)
                .withGetterVisibility(NONE)
                .withSetterVisibility(NONE)
                .withCreatorVisibility(NONE));
        enable(SerializationFeature.INDENT_OUTPUT);
        configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        setPropertyNamingStrategy(CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        setSerializationInclusion(NON_NULL);
    }
}
