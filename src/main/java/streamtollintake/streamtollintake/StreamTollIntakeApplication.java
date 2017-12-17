package streamtollintake.streamtollintake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
@SpringBootApplication
public class StreamTollIntakeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamTollIntakeApplication.class, args);
	}

	@StreamListener(target = Sink.INPUT, condition = "headers['speed'] > 40")
    //@ServiceActivator(inputChannel = Sink.INPUT)
	public void logFast(String msg) {
        System.out.println("fast - " + msg);
    }

    @StreamListener(target = Sink.INPUT, condition = "headers['speed'] <= 40")
    public void logSlow(String msg) {
        System.out.println("slow - " + msg);
    }
}
