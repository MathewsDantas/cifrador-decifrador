package com.seguranca.cifradordecifrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class CifradorDecifradorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CifradorDecifradorApplication.class, args);
    }

}

@RestController
@RequestMapping(path = "/")
class DemoController {

    @GetMapping(path = "/print/{msg}")
    public String print(@PathVariable() String msg){
        return "Hello" + msg;
    }
}