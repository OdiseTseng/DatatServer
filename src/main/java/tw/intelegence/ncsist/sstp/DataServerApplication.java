package tw.intelegence.ncsist.sstp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tw.intelegence.ncsist.sstp.netty.Server;

@SpringBootApplication
public class DataServerApplication {

	public static void main(String[] args)  {

		new Thread(Server::startServer).start();

		SpringApplication.run(DataServerApplication.class, args);

	}

}
