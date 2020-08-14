package com.ayush.ztrainingspring.order.mycart;

import java.io.Serializable;

import com.ayush.ztrainingspring.order.menus.Menus;
import com.ayush.ztrainingspring.user_auth.User;

public class compclass implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2447155950214841993L;
    private Menus menus;
    private User user;

    public User getuser(){
        return this.user;
    }
    public Menus getmenus(){
        return this.menus;
    }
}