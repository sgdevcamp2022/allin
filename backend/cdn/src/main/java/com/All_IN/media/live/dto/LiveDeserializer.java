package com.All_IN.media.live.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class LiveDeserializer implements Deserializer<LiveDTO> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public LiveDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null){
                return null;
            }
            return objectMapper.readValue(new String(data, "UTF-8"), LiveDTO.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Live DTO");
        }
    }

    @Override
    public void close() {
    }
}
