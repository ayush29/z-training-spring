package com.ayush.ztrainingspring.order.users;

import java.util.*;
import javax.persistence.*;

@Entity
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userid;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String img;
    
    Users(){
    }


    Users(Map<String, String> body) {
        this.userid = UUID.randomUUID();
        this.name = body.get("name");
        this.email = body.get("email");
        this.password = body.get("password");
        this.phone = body.get("phone");
        this.img = body.get("img");
	}

    public UUID getuserid(){
        return this.userid;
    }
    public String getname(){
        return this.name;
    }
    public String getemail(){
        return this.email;
    }
    public String getphone(){
        return this.phone;
    }
    public String getimg(){
        return this.img;
    }
    public String getpassword(){
        return this.password;
    }
}