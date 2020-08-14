package com.ayush.ztrainingspring.order.mycart;

import java.util.*;

import com.ayush.ztrainingspring.order.menus.Menurepo;
import com.ayush.ztrainingspring.order.menus.Menus;
import com.ayush.ztrainingspring.user_auth.User;
import com.ayush.ztrainingspring.user_auth.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins =
"*")
@RestController
public class Mycartapi {
    @Autowired
    Mycartrepo mycartrepo;
    @Autowired
    Menurepo menurepo;
    @Autowired
    UserRepository userRepository;


    @GetMapping("/api/mycart")
    public List<Mycart> dispcart(){
        return mycartrepo.findAll();
    }

    @GetMapping("/api/mycart/{userid}")
    public List<Mycart> dispallcart(@PathVariable("userid") int userid){
        return mycartrepo.findAllbyuser(userid);
    }

    @GetMapping("/api/usercart/{userid}")
    public List<Usercart> dispamycart(@PathVariable("userid") int userid){  
        List<Mycart> itids = mycartrepo.findAllbyuser(userid);
        List<Usercart> ret = new ArrayList<Usercart>();
        for(Mycart itid : itids)
        {
            List<Menus> food_det =  menurepo.findfooddet(itid.getmenus());
            ret.add(new Usercart(food_det.get(0).getitem_name(), food_det.get(0).getitem_cost(), itid.getquantity()));
        }
        return ret;
    }

    @PostMapping("/api/mycart")
    public List<Mycart> registercart(@RequestBody Map<String, String> body) {
        int iid = Integer.parseInt(body.get("itemid"));
        int uid = Integer.parseInt(body.get("userid"));
        User user = userRepository.findById(uid)
            .orElseThrow(
                    () -> new RuntimeException("unable to find restaurant with id: " + uid)
            );
        Menus menus = menurepo.findById(iid)
            .orElseThrow(
                    () -> new RuntimeException("unable to find restaurant with id: " + iid)
            );
        int qt = Integer.parseInt(body.get("quantity"));
        List<Mycart> tochangecart =  mycartrepo.findByUserAndMenus(uid, iid);
        if(tochangecart.isEmpty())   
            mycartrepo.save(new Mycart(user, menus, qt));
        else
        {
            Mycart thiscart = tochangecart.get(0);
            thiscart.setaddquantity(qt);
            mycartrepo.save(thiscart);
        }
        return mycartrepo.findAll();
    }

    @PostMapping("/api/mycart/del")
    public List<Mycart> removeFromCart(@RequestBody Map<String, String> body){
        int iid = Integer.parseInt(body.get("itemid"));
        int uid = Integer.parseInt(body.get("userid"));
        mycartrepo.deletebyiuid(uid, iid);
        return mycartrepo.findAll();
    }
}