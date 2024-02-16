package br.alura.desafio.fipe.fipeTable.service;

import java.util.List;

public interface IDataConverter {
    <T> T getData(String json, Class<T> className);
    <T> List<T> getListData(String json, Class<T> className);
}
