package com.jonhvtr.fipe_api.service;

import java.util.List;

public interface IConvertingData {
    <T> T getData(String json, Class<T> tClass);

    <T> List<T> getDataList(String json, Class<T> tClass);
}
