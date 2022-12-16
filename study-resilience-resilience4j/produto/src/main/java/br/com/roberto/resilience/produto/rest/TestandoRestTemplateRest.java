package br.com.roberto.resilience.produto.rest;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/teste")
public class TestandoRestTemplateRest {

    private final Logger logger = LoggerFactory.getLogger(TestandoRestTemplateRest.class);

    @GetMapping()
    @Retry(name = "default")
    public String foobar(){
        logger.info("Request to FooBar is Received");
        var response =  new RestTemplate().getForEntity("http://localhost:8080/foo-bar",String.class);
        return response.getBody();
    }

}
