package com.thentrees.ddd.controller.http;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HiController {

    private final RestTemplate restTemplate;

        @GetMapping("/hi")
        @RateLimiter(name = "backendA", fallbackMethod = "helloFallback")
        public String hello() {
            return "Hello, World - 1!";
        }

        public String helloFallback(Throwable throwable) {
            return "Too many req!!!";
        }

        @GetMapping("/hi2")
        @RateLimiter(name = "backendB", fallbackMethod = "helloFallback")
        public String hello2() {
            return "Hello, World! - 2";
        }

        private static final SecureRandom random = new SecureRandom();
        @GetMapping("/circuit/breaker")
        @CircuitBreaker(name = "checkRandom", fallbackMethod = "circuitBreakerFallback")
        public String circuitBreaker() {

            int productId = random.nextInt(20) + 1; // 1-20 random

            String strURL = "https://fakestoreapi.com/products/"+productId;
            return restTemplate.getForObject(strURL, String.class);
        }

        public String circuitBreakerFallback(Throwable throwable) {
            return throwable.getMessage();
        }
}
