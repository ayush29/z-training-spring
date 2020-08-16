package com.ayush.ztrainingspring.order.mycart;

public class Usercart {
    private int item_id;
    private int quant;
    private String item_name;
    private int rate;
    private int tcost;

    Usercart(){
    }

    Usercart(String item_name, int rate, int quant, int item_id){
        this.item_id = item_id;
        this.item_name = item_name;
        this.rate = rate;
        this.quant = quant;
        this.tcost = (this.rate)*(this.quant);
    }

    public int getquant(){
        return this.quant;
    }
    public int getitem_id(){
        return this.item_id;
    }
    public String getitem_name(){
        return this.item_name;
    }
    public int getrate(){
        return this.rate;
    }
    public int gettcost(){
        return this.tcost;
    }  
}