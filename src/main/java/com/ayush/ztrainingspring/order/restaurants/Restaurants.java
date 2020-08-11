package com.ayush.ztrainingspring.order.restaurants;

import java.util.*;
import javax.persistence.*;

@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rid;
    private String rname;
    private float rrate;
    private String rdesc;
    
    Restaurants(){
    }

    Restaurants(String rname, float rrate,String rdesc){
        this.rid = UUID.randomUUID();
        this.rname = rname;
        this.rrate = rrate;
        this.rdesc = rdesc;
    }

    Restaurants(Map<String, String> body) {
        this.rid = UUID.randomUUID();
        this.rname = body.get("rname");
        this.rrate = Float.parseFloat(body.get("rrate"));
        this.rdesc = body.get("rdesc");

	}

    public UUID getrid(){
        return this.rid;
    }
    public String getrname(){
        return this.rname;
    }
    public float getrrate(){
        return this.rrate;
    }
    public String getrdesc(){
        return this.rdesc;
    }

}