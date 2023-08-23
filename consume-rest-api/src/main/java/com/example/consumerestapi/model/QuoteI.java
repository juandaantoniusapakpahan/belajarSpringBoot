package com.example.consumerestapi.model;

public class QuoteI {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ValueI getValue() {
        return value;
    }

    public void setValue(ValueI value) {
        this.value = value;
    }

    private ValueI value;

    @Override
    public String toString() {
        return "QuoteI{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
