package com.ayush.ztrainingspring.order.menus;

import java.util.List;

public class Retclass {
    private String class_name;
    private int class_id;
    private List<Menus> items;

    Retclass(){
    }

    Retclass(String cname, int cid, List<Menus> it)
    {
        this.class_name = cname;
        this.class_id = cid;
        this.items = it;
    }

    public String getClass_name(){
        return this.class_name;
    }
    public int getclass_id(){
        return this.class_id;
    }
    public List<Menus> getitem(){
        return this.items;
    }
}