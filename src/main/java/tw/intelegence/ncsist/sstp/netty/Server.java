package tw.intelegence.ncsist.sstp.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import tw.intelegence.ncsist.sstp.netty.handler.ServerHandler;

import java.util.concurrent.TimeUnit;

public class Server {

    public static void startServer(){
        //Create two thread group boosGroup、workerGroup
        //負責連接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //負責讀寫或操作
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //Create Server boot target, and setting parameters
            ServerBootstrap bootstrap = new ServerBootstrap();

            //Setting two thread group boosGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)
                    //Setting server channel type
                    .channel(NioServerSocketChannel.class)
                    //Setting thread pool connect numbers
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // Setting active alive connect state
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //Use annotation type to initialize channel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //Give pipline setting processor.
                            socketChannel.pipeline().addLast(new ServerHandler());
                            socketChannel.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
                        }
                    });//给workerGroup的EventLoop對應的channel 設定 processor
            System.out.println("server already accept connect...");
            //bind port and start server
            ChannelFuture channelFuture = bootstrap.bind(6666).sync();
            //ChannelFuture channelFuture = bootstrap.bind(6666);

            //channelFuture.addListener( (ChannelFutureListener) future -> {
            //	System.out.println("future start");
            //	//判断是否操作成功
            //	if (future.isSuccess()) {
            //		System.out.println("connect success");
            //	} else {
            //		System.out.println("connect failed");
            //	}
            //} );

            // listening channel when closing.
            channelFuture.channel().closeFuture().sync();
            //channelFuture.channel().closeFuture();
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
