package io.simpoloe.pingpong.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SenderController {

    private final RestTemplate restTemplate;

    @Value("${callback.url}")
    private String callbackUrl;

    @Value("${proxy.url}")
    private String proxyUrl;

    @GetMapping("/call")
    public ResponseEntity<String> call(){

        StringBuilder stringBuilder = new StringBuilder();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        String message = restTemplate.exchange(callbackUrl, HttpMethod.GET, httpEntity, String.class).getBody();
        log.info("> Request url : {}, Response message : {}", callbackUrl, message);

        stringBuilder.append("The response to your request is ");
        stringBuilder.append(message);


        return new ResponseEntity(stringBuilder.toString(), HttpStatus.OK);
    }

    @GetMapping("/call/proxy")
    public ResponseEntity<String> callProxy(){

        StringBuilder stringBuilder = new StringBuilder();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        String message = restTemplate.exchange(proxyUrl, HttpMethod.GET, httpEntity, String.class).getBody();
        log.info("> Proxy url : {}, Response message : {}", callbackUrl, message);

        stringBuilder.append("The response to your request is ");
        stringBuilder.append(message);

        return new ResponseEntity(stringBuilder.toString(), HttpStatus.OK);
    }
}
