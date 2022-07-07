package com.lepric.btservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    @Value("${app.name}")
    private String appName;
    @Value("${app.version}")
    private String appVersion;

    @GetMapping("version")
    public ResponseEntity<String> getAppDetails() {
        return new ResponseEntity<String>(appName+"@"+appVersion, HttpStatus.OK);
    }
    @GetMapping("version/{version}")
    public ResponseEntity<String> isCorrectVerison(@PathVariable("version") String version) {
        if(appVersion.equals(version)) {
            return new ResponseEntity<String>("true", HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("false", HttpStatus.OK);
        }
    }
}
