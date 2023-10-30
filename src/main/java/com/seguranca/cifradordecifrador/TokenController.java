package com.seguranca.cifradordecifrador;

import com.seguranca.cifradordecifrador.exceptions.EmptyKeyException;
import com.seguranca.cifradordecifrador.exceptions.InvalidTextException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @PostMapping(path = "/cifrador")
    @Operation(summary = "cifrar", description = "Cifragem da senha")
    public String encrypt(@RequestBody TokenModel request) {
        String text = request.getText();
        String[] key = request.getKey();

        if (text == null || text.isEmpty()) {
            throw new InvalidTextException("Texto inválido, não pode está vazio.");
        }

        if (key == null || key.length == 0 || Arrays.stream(key).anyMatch(k -> k.isEmpty()) || Arrays.stream(key).anyMatch(k-> !k.matches("\\d+"))) {
            throw new EmptyKeyException("Chaves vazias não são permitidas.");
        }

        String result = tokenService.encrypt(text, key);

        if (result == null) {
            throw new InvalidTextException("Cifragem falhou, verifique seu texto, ele pode não está adequado as regras.");
        }

        return result;
    }

    @PostMapping(path = "/decifrador")
    @Operation(summary = "decifrar", description = "Decifragem da senha")
    public String decrypt(@RequestBody TokenModel request) {
        String text = request.getText();
        String[] key = request.getKey();

        if (text == null || text.isEmpty()) {
            throw new InvalidTextException("Texto inválido, não pode está vazio.");
        }

        if (key == null || key.length == 0 || Arrays.stream(key).anyMatch(k -> k.isEmpty()) || Arrays.stream(key).anyMatch(k-> !k.matches("\\d+"))) {
            throw new EmptyKeyException("Chaves vazias não são permitidas.");
        }

        String result = tokenService.decrypt(text, key);

        if (result == null) {
            throw new InvalidTextException("Cifragem falhou, verifique seu texto, ele pode não está adequado as regras.");
        }

        return result;
    }
}
