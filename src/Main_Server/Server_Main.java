package Main_Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server_Main {
    private ServerSocket serverSocket;
    private static ArrayList<UserMade> clientList = new ArrayList<>();
    // 대기실 리스트 - <대기방 클래스 : 유저 5명 최대>
    private static ArrayList<RoomMade> Room;
    //방만들기 요청이 들어올 시 명령어를 입력한 유저를 호스트로 지정, 다른 유저들이 들어올 수 있도록 방을 만듦.
    private boolean isServerOn=true;
    public void serverSetting() {
        try{
            System.out.println("[Server] ---JAVA GAME SERVER 가동---");
            serverSocket = new ServerSocket(10002); // 생성&바인드
            System.out.println("[Server] socket create and bind");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Server_Main() {
        serverSetting();
    }
    public static void main(String[] args) {
        Server_Main s = new Server_Main();
        UserInfo info;
        while(s.isServerOn) {
            try {
                System.out.println("[Server] Starting Server..");
                clientList.removeIf(ch -> ch.info.getClient().isClosed());
                System.out.println("[Server] Waiting for client connection...");
                System.out.println("[Server] Connected Client : " + clientList.size());
                info=new UserInfo(s.serverSocket.getInetAddress(),"",s.serverSocket.accept());
                System.out.println("[Server] Connected to client : "+info/*info.getClient().toString()*/);
                UserMade client = new UserMade(info);
                Server_Main.clientList.add(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}