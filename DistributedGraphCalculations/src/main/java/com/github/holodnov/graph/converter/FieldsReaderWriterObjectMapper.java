package com.github.holodnov.graph.converter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @author Kyrylo Holodnov
 */
public class FieldsReaderWriterObjectMapper extends ObjectMapper {

    public FieldsReaderWriterObjectMapper() {
        super();
        setVisibilityChecker(getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
    }
}
