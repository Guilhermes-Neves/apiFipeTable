package br.alura.desafio.fipe.fipeTable.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConverter implements IDataConverter{
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> className) {
        try {
            return mapper.readValue(json, className);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> getListData(String json, Class<T> className) {
        CollectionType collectionList = mapper.getTypeFactory()
                .constructCollectionType(List.class, className);

        try {
            return mapper.readValue(json, collectionList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
