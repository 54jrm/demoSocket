package demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			// 1.创建服务器端socket serverSocket
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("server start///....服务器开启在8888端口上");
			int count  =  0;
			// 2.捕获客户端返回的socket
			Socket socket = null;
			while(true){
				socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
				count++;
				System.out.println("客户端数量"+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端ip"+" "+address.getHostAddress());
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
