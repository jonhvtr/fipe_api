package com.jonhvtr.fipe_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataFipe (@JsonAlias("brand") String brand,
                        @JsonAlias("codeFipe") String codeFipe,
                        @JsonAlias("fuel") String fuel,
                        @JsonAlias("fuelAcronym") String fuelAcronym,
                        @JsonAlias("model") String model,
                        @JsonAlias("modelYear") String modelYear,
                        @JsonAlias("price") String price,
                        @JsonAlias("referenceMonth") String referenceMonth){

    @Override
    public String toString() {
        return String.format("%s %s  ano: %s valor: %s combustível: %s",
                brand, model, modelYear, price, fuel);
    }
}
