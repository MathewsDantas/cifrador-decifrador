package com.seguranca.cifradordecifrador;

import com.seguranca.cifradordecifrador.exceptions.EmptyKeyException;
import com.seguranca.cifradordecifrador.exceptions.InvalidTextException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping(path = "/cifrar")
    public String cifrador(@RequestBody TokenModel request) {
        String text = request.getText();
        String[] key = request.getKey();

        if (text == null || text.isEmpty()) {
            throw new InvalidTextException("Texto inválido, não está nas regras.");
        }

        if (key == null || key.length == 0 || Arrays.stream(key).anyMatch(String::isEmpty)) {
            throw new EmptyKeyException("Chaves vazias não são permitidas.");
        }

        return tokenService.cifrar(text, key);
    }

    @PostMapping(path = "/decifrar")
    public String decifrador(@RequestBody TokenModel request) {
        String text = request.getText();
        String[] key = request.getKey();

        if (text == null || text.isEmpty()) {
            throw new InvalidTextException("Texto inválido, não está nas regras.");
        }

        if (key == null || key.length == 0 || Arrays.stream(key).anyMatch(String::isEmpty)) {
            throw new EmptyKeyException("Chaves vazias não são permitidas.");
        }

        return tokenService.decifrar(text, key);
    }
}
