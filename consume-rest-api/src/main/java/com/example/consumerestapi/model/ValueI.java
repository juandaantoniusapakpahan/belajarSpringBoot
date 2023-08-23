package com.example.consumerestapi.model;

public class ValueI {
    @Override
    public String toString() {
        return "ValueI{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }

    private Integer id;
    private String quote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }


}
