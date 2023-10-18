package com.spring.next.activemq.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private String name;
    private Double price;
    private int quantity;

    public Ticket(){}

    public Ticket(String name, Double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return String.format("Compra de ingresso -> " +
                        "Name=%s, Price=%s, Quantity=%s}",
                getName(), getPrice(), getQuantity());
    }
}
