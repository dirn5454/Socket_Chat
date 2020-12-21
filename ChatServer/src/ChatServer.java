import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

	public static ArrayList<PrintWriter> main_OutputList;
	// ������ �����ϴ� Ŭ���̾�Ʈ�� output�� ��Ƶδ� �迭
	
	public static void main(String[] args) {
		
		main_OutputList = new ArrayList<PrintWriter>();
		
		try {
			ServerSocket server_socket = new ServerSocket(8888);
			// ��Ʈ��ȣ 8888 ��������
			
			while(true) {
				Socket client_socket = server_socket.accept();
				
				ClientManagerThread client_thread = new ClientManagerThread();
				client_thread.setSocket(client_socket);
				
				main_OutputList.add(new PrintWriter(client_socket.getOutputStream()));
				// client_socket.getOutputStream()�� ����ϱ� ���� PrintWriter�� ��ȯ�� ����
				client_thread.start();
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
