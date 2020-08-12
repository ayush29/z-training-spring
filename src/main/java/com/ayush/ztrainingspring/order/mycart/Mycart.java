package com.ayush.ztrainingspring.order.mycart;

import java.util.*;
import javax.persistence.*;

@Entity
@IdClass(compclass.class)
public class Mycart {
    @Id
    private UUID itemid;
    @Id
    private UUID userid;
    private int quantity;

    
    Mycart(){
    }

    Mycart(Map<String, String> body) {
        this.itemid = UUID.fromString(body.get("itemid"));
        this.quantity = Integer.parseInt(body.get("quantity"));
        this.userid = UUID.fromString(body.get("userid"));
    }
    
    Mycart(UUID userid, UUID itemid, int quantity){
        this.itemid = itemid;
        this.quantity = quantity;
        this.userid = userid;
    }

    public UUID getitemid(){
        return this.itemid;
    }
    public int getquantity(){
        return this.quantity;
    }
    public UUID getuserid(){
        return this.userid;
    }
    public void setaddquantity(int qt){
        this.quantity += qt;
    }
}