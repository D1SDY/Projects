package cz.xentalib.service;

        import org.springframework.kafka.annotation.KafkaListener;
        import org.springframework.stereotype.Service;

        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.IOException;

@Service
public class KafkaService {
    @KafkaListener(topics = "AuctionReport", groupId = "group_id")
    public void consume(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/files/report.txt", true));
        writer.write(message);
        writer.close();
    }
}
