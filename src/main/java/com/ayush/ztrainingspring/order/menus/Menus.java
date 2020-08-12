package com.ayush.ztrainingspring.order.menus;

import java.util.*;
import javax.persistence.*;

@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID item_id;
    private UUID rid;
    private String item_name;
    private int item_cost;
    private String item_img;
    private String item_category;

    Menus(){
    }

    Menus(Map<String, String> body) {
        this.item_id = UUID.randomUUID();
        this.item_name = body.get("item_name");
        this.item_img = body.get("item_img");
        this.item_cost = Integer.parseInt(body.get("item_cost"));
        this.rid = UUID.fromString(body.get("rid"));
        this.item_category = body.get("item_category");
	}

    public UUID getitem_id(){
        return this.item_id;
    }
    public String getitem_name(){
        return this.item_name;
    }
    public int getitem_cost(){
        return this.item_cost;
    }
    public UUID getrid(){
        return this.rid;
    }
    public String getitem_img(){
        return this.item_img;
    }
    public String getitem_category(){
        return this.item_category;
    }
}