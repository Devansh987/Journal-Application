package com.Devansh.Journal.appcache;

import com.Devansh.Journal.Entity.config_App;
import com.Devansh.Journal.Repo.ConfigureJournalApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Component
public class appCache {

   public  HashMap<String ,String> App_Cache;

    @Autowired
    private ConfigureJournalApp configureJournalApp;

    @PostConstruct
    public void init(){
        App_Cache = new HashMap<>();
        List<config_App> all = configureJournalApp.findAll();
        for(config_App config_app : all){
            App_Cache.put(config_app.getKey(),config_app.getValue());
        }
    }
}
