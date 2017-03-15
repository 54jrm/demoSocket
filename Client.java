package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) {

		try {
			// 1创建客户端Socket 制定服务器端的地址和端口
			Socket socket = new Socket("localhost", 8888);
			// 2 获取输出流，向服务器发送
			OutputStream os = socket.getOutputStream();
			PrintWriter pr = new PrintWriter(os);
			pr.write("用户黑工;密码123");
			pr.flush();
			socket.shutdownOutput();
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while ((info = br.readLine()) != null) {
				System.out.println("服务端返回" +" "+ info);
			}
			br.close();
			is.close();
			pr.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
