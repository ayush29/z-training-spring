package com.ayush.ztrainingspring.order.mycart;

public class Usercart {
    private int quant;
    private String item_name;
    private int rate;
    private int tcost;

    Usercart(){
    }

    Usercart(String item_name, int rate, int quant){
        this.item_name = item_name;
        this.rate = rate;
        this.quant = quant;
        this.tcost = (this.rate)*(this.quant);
    }

    public int getquant(){
        return this.quant;
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