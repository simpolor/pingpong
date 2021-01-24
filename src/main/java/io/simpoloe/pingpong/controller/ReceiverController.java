package io.simpoloe.pingpong.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class ReceiverController {

    @GetMapping("/callback")
    public ResponseEntity<String> callback(HttpServletRequest request){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your request ip is ");

        String ip = request.getHeader("X-Forwarded-For");
        log.info("Header X-FORWARDED-FOR : {}", ip);

        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
            log.info("Header Proxy-Client-IP : {}", ip);
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
            log.info("Header WL-Proxy-Client-IP : {}", ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
            log.info("Header HTTP_CLIENT_IP : {}", ip);
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            log.info("Header HTTP_X_FORWARDED_FOR : {}", ip);
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
        stringBuilder.append(ip);

        return new ResponseEntity(stringBuilder.toString(), HttpStatus.OK);
    }
}
