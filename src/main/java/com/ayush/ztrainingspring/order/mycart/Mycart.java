package com.ayush.ztrainingspring.order.mycart;

import javax.persistence.*;

import com.ayush.ztrainingspring.order.menus.Menus;
import com.ayush.ztrainingspring.user_auth.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Mycart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartid;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Menus menus;

    private int quantity;

    Mycart(){
    }

    // Mycart(Map<String, String> body) {
    //     this.menus = body.get("menus");
    //     this.quantity = Integer.parseInt(body.get("quantity"));
    //     this.user = body.get("user");
    // }
    
    Mycart(User user, Menus menus, int quantity){
        this.menus = menus;
        this.quantity = quantity;
        this.user = user;
    }

    public int getcartid(){
        return this.cartid;
    }
    public Menus getmenus(){
        return this.menus;
    }
    public int getquantity(){
        return this.quantity;
    }
    public User getuser(){
        return this.user;
    }
    public void setaddquantity(int qt){
        this.quantity += qt;
    }
    public void setquantity(int qt){
        this.quantity = qt;
    }
}