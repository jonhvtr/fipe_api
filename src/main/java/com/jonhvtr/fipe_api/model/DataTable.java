package com.jonhvtr.fipe_api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataTable(@JsonAlias("code") String code,
                        @JsonAlias("name") String name) {
}
