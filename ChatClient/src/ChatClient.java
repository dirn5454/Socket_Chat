import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	
	public static String UserID;

	public static void main(String[] args) {
		
		try {
			Socket client_socket = new Socket("192.168.56.1", 8888);
			
			ReceiveThread receive_thread = new ReceiveThread();
			receive_thread.setSocket(client_socket);
			
			SendThread send_thread = new SendThread();
			send_thread.setSocket(client_socket);

			send_thread.start();
			receive_thread.start();
			
		}catch(UnknownHostException e) {
			e.printStackTrace();
			
			
		}catch(IOException e) {
			e.getStackTrace();

	    }
	}
}
