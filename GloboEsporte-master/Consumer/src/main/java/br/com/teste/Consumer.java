package br.com.teste;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.com.teste")
public class Consumer {
    public static void main(String[] args) {
        SpringApplication.run(Consumer.class, args);
    }

}

