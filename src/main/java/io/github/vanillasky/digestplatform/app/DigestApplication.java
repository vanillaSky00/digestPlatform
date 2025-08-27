package io.github.vanillasky.digestplatform.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DigestApplication {

    public static void main(String[] args) {

        SpringApplication.run(DigestApplication.class, args);
        System.out.println("Heelo");
    }

}
