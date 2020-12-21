import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	public static ArrayList<PrintWriter> main_OutputList;
	// 서버로 접속하는 클라이언트의 output을 모아두는 배열
	
	public static void main(String[] args) {
		
		main_OutputList = new ArrayList<PrintWriter>();
		
		try {
			ServerSocket server_socket = new ServerSocket(8888);
			// 포트번호 8888 서버소켓
			
			while(true) {
				Socket client_socket = server_socket.accept();
				
				ClientManagerThread client_thread = new ClientManagerThread();
				client_thread.setSocket(client_socket);
				
				main_OutputList.add(new PrintWriter(client_socket.getOutputStream()));
				// client_socket.getOutputStream()를 사용하기 좋게 PrintWriter로 변환후 저장
				client_thread.start();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
