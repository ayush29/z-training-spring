package com.ayush.ztrainingspring.order.menus;

import java.util.*;

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
public class Menuapi {
    @Autowired
    Menurepo menurepo;
    @Autowired
    private Classgenerator bodyconv;

    @GetMapping("/api/menus")
    public List<Menus> dispMenu(){
        return menurepo.findAll();
    }

    @PostMapping(value="api/menus")
    public Menus registerMenu(@RequestBody Map<String, String> body) {
        return menurepo.save(bodyconv.conv(body));
    }

    @PostMapping(value="api/menus/all")
    public List<Menus> registerMenuall(@RequestBody List<Map<String, String> >body) {
        for(Map<String, String> bb : body)
            menurepo.save(bodyconv.conv(bb));
        return menurepo.findAll();
    }

    @GetMapping(value="api/menus/{rid}")
    public List<Retclass> getMenu(@PathVariable("rid") int rid) {
        List<String> cats = menurepo.getcats(rid);
        List<Retclass> ret = new ArrayList<Retclass>();
        int cid = 0;
        for(String icat : cats)
        {
            List<Menus> menuret = menurepo.getmenu(rid, icat);
            ret.add(new Retclass(icat, cid++, menuret));
        }
        return ret;
    }

}