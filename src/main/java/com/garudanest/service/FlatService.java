package com.garudanest.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garudanest.model.Flat;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FlatService {

    private List<Flat> flats;

    @PostConstruct
    public void init() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Flat>> typeReference = new TypeReference<List<Flat>>() {};
        InputStream inputStream = getClass().getResourceAsStream("/static/defaultFlats.json");
        flats = mapper.readValue(inputStream, typeReference);
    }

    public List<Flat> getFlats() {
        return flats;
    }
}
