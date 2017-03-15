package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket socket = null;
	
	public ServerThread (Socket socket){
		this.socket=socket;
	}
	
	public void run(){
		InputStream is =null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		// 4.服务器输出回复给客户端 欢迎
		OutputStream os=null;
		PrintWriter pw=null;
		try {
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = "";
			String username = "";
			while ((info = br.readLine()) != null) {
				System.out.println("我是服务器客户端说" + info);
				if(info != null){
					username = info.substring(2, 4);
				}
			}
			// 3.关闭输入流 
			socket.shutdownInput();
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("welcome to the server"+ " "+username);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pw!=null)
				pw.close();
				if(os!=null)
				os.close();
				if(br!=null)
				br.close();
				if(isr!=null)
				isr.close();
				if(is!=null)
				is.close();
				if(socket!=null)
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
}
