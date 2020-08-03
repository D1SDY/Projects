package cz.xentalib;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class app {
    public static void main(String[] args) {
        SpringApplication.run(app.class, args);
    }
    @Bean
    public NewTopic adviceTopic() {
        return new NewTopic("AuctionReport", 3, (short) 1);
    }
}
