package com.pwldata.commons;

import com.pwl.rocket_sim.api.v1.model.RocketType;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonParser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationContext;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonDeserializer;


import java.io.IOException;

public class RocketTypeDeserializer extends JsonDeserializer<RocketType> {


    @Override
    public RocketType deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String type = node.get("rocketType").asText();
        return RocketType.fromValue(type);
    }


}