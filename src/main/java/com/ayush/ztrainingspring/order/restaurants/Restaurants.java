package com.ayush.ztrainingspring.order.restaurants;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ayush.ztrainingspring.order.menus.Menus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Restaurants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rname;
    private float rrate;
    private String rdesc;

    @OneToMany(mappedBy = "restaurants", cascade = CascadeType.ALL)
    private List<Menus> menu = new ArrayList<>();
    
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