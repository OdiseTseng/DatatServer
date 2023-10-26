package tw.intelegence.ncsist.sstp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import tw.intelegence.ncsist.sstp.netty.NettyServer;

@SpringBootApplication
@ServletComponentScan
public class DataServerApplication {

	public static void main(String[] args)  {

		new Thread(NettyServer::startServer).start();

		SpringApplication.run(DataServerApplication.class, args);

	}

}
