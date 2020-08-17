package com.ayush.ztrainingspring.order.mycart;

import java.util.List;

public class Retclass{
    private List<Usercart> cart;
    private int titem;
    private int tcost;

    Retclass(){
    }

    Retclass(List<Usercart> cart, int titem, int tcost ){
        this.cart = cart;
        this.titem = titem;
        this.tcost = tcost;
    }

    public List<Usercart> getcart(){
        return this.cart;
    }
    public int gettitem(){
        return titem;
    }
    public int gettcost(){
        return this.tcost;
    }

}