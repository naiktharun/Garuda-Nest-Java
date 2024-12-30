package com.garudanest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garudanest.model.Flat;
import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FlatService {
    private static final Logger logger = LoggerFactory.getLogger(FlatService.class);

    private List<Flat> flats;

    @PostConstruct
    public void init() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Flat>> typeReference = new TypeReference<List<Flat>>() {};
        InputStream inputStream = getClass().getResourceAsStream("/static/defaultFlats.json");
        try {
            flats = mapper.readValue(inputStream, typeReference);
            logger.info("Flats loaded successfully from JSON file");
        } catch (IOException e) {
            logger.error("Error loading flats from JSON file", e);
        }
    }

    public List<Flat> getFlats() {
        return flats;
    }
}
