package com.steen.app.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SteenController {

    @GetMapping(value = "/healthcheck")
    public Mono<?> healthcheck(@RequestParam("format") String format) throws InterruptedException {
        HashMap<String, String> responseBody = new HashMap<>();
        responseBody.put("status", "OK");
        if(format.equals("short")){
            return Mono.just(responseBody);
        }
        else if(format.equals("full")){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            String currentTime = df.format(new Date());
            responseBody.put("currentTime", currentTime);
            return Mono.just(responseBody);
        }
        else {
            return Mono.just(ResponseEntity.badRequest());
        }
    }
//
//    @PutMapping(value = "/healthcheck")
//    public ResponseEntity<Void> healthcheckPut() {
//        return;
//    }
//
    @PostMapping(value = "/healthcheck")
    public ResponseEntity<String> healthcheckPost() {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }
//
//    @DeleteMapping(value = "/healthcheck")
//    public void healthcheckDelete() {
//        return;
//    }

}
