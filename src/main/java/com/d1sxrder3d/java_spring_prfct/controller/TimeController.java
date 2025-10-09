package com.d1sxrder3d.java_spring_prfct.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
public class TimeController {

    static Map<String, String> langMap;

    {
        langMap = new HashMap<String, String>();
        langMap.put("en", "Current time: ");
        langMap.put("ru", "Текущее время: ");
    }

    private String GetCurrentTime(String lang, String format){

        LocalDateTime now = LocalDateTime.now();

        if(lang.isEmpty()){
            lang = "en";
        }

        if(!Objects.equals(format, "")){

            return langMap.get(lang) + now.format(DateTimeFormatter.ofPattern(format));
        }


        return langMap.get(lang) + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }
    @GetMapping("/getCurrentTime")
    public String getCurrentTime(String lang, String format){
        if(lang == null){
            lang = "";
        }
        if(format == null){
            format = "";
        }

        return GetCurrentTime(lang, format);
    }

}
