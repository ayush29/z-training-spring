package com.ayush.ztrainingspring.order.menus;

import javax.persistence.*;

import com.ayush.ztrainingspring.order.mycart.Mycart;
import com.ayush.ztrainingspring.order.restaurants.Restaurants;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;
    private String item_name;
    private int item_cost;
    private String item_img;
    private String item_category;
    private String descp;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "rid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurants restaurants;

    @OneToMany(mappedBy = "menus", cascade = CascadeType.ALL)
    private List<Mycart> mycart = new ArrayList<>();

    Menus(){
    }

    // Menus(Map<String, String> body) {
    //     Restaurantrepo rrepo;
    //     this.item_name = body.get("item_name");
    //     this.item_img = body.get("item_img");
    //     this.item_cost = Integer.parseInt(body.get("item_cost"));
    //     this.restaurants = rrepo.findById(Integer.parseInt(body.get("rid")));
    //     this.item_category = body.get("item_category");
    //     this.descp = body.get("descp");
    // }
    Menus(String item_name, String item_img, int item_cost, String item_category, String descp, Restaurants rid) {
        this.item_name = item_name;
        this.item_img = item_img;
        this.item_cost = item_cost;
        this.restaurants = rid;
        this.item_category = item_category;
        this.descp = descp;
	}

    public int getitem_id(){
        return this.item_id;
    }
    public String getitem_name(){
        return this.item_name;
    }
    public int getitem_cost(){
        return this.item_cost;
    }
    public Restaurants getrid(){
        return this.restaurants;
    }
    public String getitem_img(){
        return this.item_img;
    }
    public String getitem_category(){
        return this.item_category;
    }
    public String getdescp(){
        return this.descp;
    }
}