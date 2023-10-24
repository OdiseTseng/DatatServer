package tw.intelegence.ncsist.sstp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tw.intelegence.ncsist.sstp.netty.NettyServer;

@SpringBootApplication
public class DataServerApplication {

	public static void main(String[] args)  {

		new Thread(NettyServer::startServer).start();

		SpringApplication.run(DataServerApplication.class, args);

	}

}
