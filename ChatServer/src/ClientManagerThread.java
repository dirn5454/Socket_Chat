import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManagerThread extends Thread{
	
	private Socket main_socket;
	private String main_ID;	// 소켓의 채팅ID 변수
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader tempbf = new BufferedReader(new InputStreamReader(main_socket.getInputStream()));
			String text;
			
			while(true) {
				text = tempbf.readLine();
				// 클라이언트 소켓의 inputstream을 사용하기 위해 데이터를 받아오는 부분
				
				
				// 해당 소켓이 나갔는지 체크         
				if(text == null) {
					System.out.println(main_ID + " 이(가) 나갔습니다.");
					for(int i = 0; i< ChatServer.main_OutputList.size(); ++i) {
						
						ChatServer.main_OutputList.get(i).println(main_ID + " 이(가) 나갔습니다.");
						// 특정ID가 나간 것을 서버뿐아닌 모든 클라이언트에게 알림
						ChatServer.main_OutputList.get(i).flush();
						// 버퍼플 비워주는 함수
					}
					break;
			    }
				
				String[] split = text.split("highkrs12345");
				
				if(split.length == 2 && split[0].equals("ID")){
					main_ID = split[1];
					System.out.println(main_ID + " 이(가) 입장하셨습니다.");
					for(int i =0; i< ChatServer.main_OutputList.size(); ++i) {
						
						ChatServer.main_OutputList.get(i).println(main_ID + " 이(가) 입장하셨습니다.");
						ChatServer.main_OutputList.get(i).flush();
					}
					continue;
				}
				for(int i = 0; i< ChatServer.main_OutputList.size(); ++i) {
					
					ChatServer.main_OutputList.get(i).println(main_ID + ">" + text);
					ChatServer.main_OutputList.get(i).flush();
				}
			}
			ChatServer.main_OutputList.remove(new PrintWriter(main_socket.getOutputStream()));
			main_socket.close();
			// ClientManagerThread 스레드가 종료되면 main_OutputList에서 해당 소켓의 outputstream을 제거하고 소켓을 닫음
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void setSocket(Socket socket) {
		main_socket = socket;
	}

}
