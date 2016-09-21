import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//创建服务器socket
			ServerSocket serverSocket = new ServerSocket(8888);
		    //
			System.out.println("服务器开始");
			Socket socket = serverSocket.accept();
			//
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			while((info = br.readLine()) != null)
				System.out.println("我是服务器，客户端说：" + info);
			socket.shutdownInput();
			
			//4
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎您！");
			pw.flush();
			//
			pw.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
