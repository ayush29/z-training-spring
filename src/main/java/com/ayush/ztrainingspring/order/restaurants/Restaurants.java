package com.ayush.ztrainingspring.order.restaurants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String rname;
    private float rrate;
    private String rdesc;
    
    Restaurants(){
    }

    Restaurants(String rname, float rrate,String rdesc){
        this.rname = rname;
        this.rrate = rrate;
        this.rdesc = rdesc;
    }

    Restaurants(Map<String, String> body) {
        this.rname = body.get("rname");
        this.rrate = Float.parseFloat(body.get("rrate"));
        this.rdesc = body.get("rdesc");

	}

    public int getid(){
        return this.id;
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