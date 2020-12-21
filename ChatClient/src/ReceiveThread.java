import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReceiveThread extends Thread {

	private Socket main_socket;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			
			BufferedReader tempbf = new BufferedReader(new InputStreamReader(main_socket.getInputStream()));
			String receiveString;
			String[] split;
			
			
			while(true) {
				
				receiveString = tempbf.readLine();
				split = receiveString.split(">");
				// 해당 메시지의 ID가 자신이라면 출력을 막는다
				if(split.length >= 2 && split.equals(ChatClient.UserID)){
					continue;
				}
				
			}
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	public void setSocket(Socket socket) {
		main_socket = socket;
	}
	
	

}
