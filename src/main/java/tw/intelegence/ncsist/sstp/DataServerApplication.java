package tw.intelegence.ncsist.sstp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class DataServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataServerApplication.class, args);

	}

}
