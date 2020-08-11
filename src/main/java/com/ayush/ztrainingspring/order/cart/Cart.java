package com.ayush.ztrainingspring.order.cart;

import java.util.Map;
import java.util.UUID;
import javax.persistence.*;


@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String item;
    private int rate;
    private int quantity;
    private int total;

    Cart() {
    }

    Cart(String item, int rate, int quantity) {
        this.id = UUID.randomUUID();
        this.item = item;
        this.rate = rate;
        this.quantity = quantity;
        this.total = quantity*rate;
    }

    Cart(Map<String, String> body) {
        this.id = UUID.randomUUID();
        this.item = body.get("item");
        this.rate = Integer.parseInt(body.get("rate"));
        this.quantity = Integer.parseInt(body.get("quantity"));
        this.total = (this.quantity)*(this.rate);

	}

	public UUID getid(){
        return this.id;
    }
    public String getitem(){
        return this.item;
    }
    public int getrate(){
        return this.rate;
    }
    public int getquantity(){
        return this.quantity;
    }
    public int gettotal(){
        return this.total;
    }

}       

