package za.co.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CurrencyRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyRestApplication.class, args);
    }

}
