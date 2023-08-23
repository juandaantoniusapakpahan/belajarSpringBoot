package com.example.consumerestapi.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


// @JsonIgnoreProperties is a class-level annotation that marks a
// property or a list of properties that Jackson will ignore
@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(String type, Value value) {

}
