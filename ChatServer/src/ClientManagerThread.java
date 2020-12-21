import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManagerThread extends Thread{
	
	private Socket main_socket;
	private String main_ID;	// ������ ä��ID ����
	
	@Override
	public void run() {
		super.run();
		
		try {
			BufferedReader tempbf = new BufferedReader(new InputStreamReader(main_socket.getInputStream()));
			String text;
			
			while(true) {
				text = tempbf.readLine();
				// Ŭ���̾�Ʈ ������ inputstream�� ����ϱ� ���� �����͸� �޾ƿ��� �κ�
				
				
				// �ش� ������ �������� üũ         
				if(text == null) {
					System.out.println(main_ID + " ��(��) �������ϴ�.");
					for(int i = 0; i< ChatServer.main_OutputList.size(); ++i) {
						
						ChatServer.main_OutputList.get(i).println(main_ID + " ��(��) �������ϴ�.");
						// Ư��ID�� ���� ���� �����Ӿƴ� ��� Ŭ���̾�Ʈ���� �˸�
						ChatServer.main_OutputList.get(i).flush();
						// ������ ����ִ� �Լ�
					}
					break;
			    }
				
				String[] split = text.split("highkrs12345");
				
				if(split.length == 2 && split[0].equals("ID")){
					main_ID = split[1];
					System.out.println(main_ID + " ��(��) �����ϼ̽��ϴ�.");
					for(int i =0; i< ChatServer.main_OutputList.size(); ++i) {
						
						ChatServer.main_OutputList.get(i).println(main_ID + " ��(��) �����ϼ̽��ϴ�.");
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
			// ClientManagerThread �����尡 ����Ǹ� main_OutputList���� �ش� ������ outputstream�� �����ϰ� ������ ����
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void setSocket(Socket socket) {
		main_socket = socket;
	}

}
