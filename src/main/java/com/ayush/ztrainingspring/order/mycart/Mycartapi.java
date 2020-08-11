package com.ayush.ztrainingspring.order.mycart;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =
"*")
@RestController
public class Mycartapi {
    @Autowired
    Mycartrepo mycartrepo;

    @GetMapping("/api/mycart")
    public List<Mycart> dispMenu(){
        return mycartrepo.findAll();
    }

    @PostMapping(value="api/mycart")
    public Mycart registerMenu(@RequestBody Map<String, String> body) {
        UUID iid = UUID.fromString(body.get("itemid"));
        UUID uid = UUID.fromString(body.get("userid"));
        int qt = Integer.parseInt(body.get("quantity"));
        List<Mycart> tochangecart =  mycartrepo.findByUseridAndItemid(uid, iid);
        if(tochangecart.isEmpty())   
        {
            System.out.println("\n \n \n ----------------first --------------- success ------------- \n \n \n");
            return mycartrepo.save(new Mycart(body));
        }
        else
        {
            Mycart thiscart = tochangecart.get(0);
            thiscart.setaddquantity(qt);
            return mycartrepo.save(thiscart);
        }
    }
}