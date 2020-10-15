package Main_Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server_Main {

    private ServerSocket serverSocket;
    private static ArrayList<UserMade> clientList = new ArrayList<>();
    private int logged_User=0;
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
        Socket clientSocket;
        Server_Main s = new Server_Main();
        while(s.isServerOn) {
            try {
                clientList.removeIf(ch -> ch.clientSocket.isClosed());
                System.out.println("[Server] Waiting for client connection...");
                System.out.println("[Server] Connected Client : " + clientList.size());
                clientSocket = s.serverSocket.accept();
                System.out.println("[Server] Connected to client : "+clientSocket.toString());
                UserMade client = new UserMade(clientSocket);
                Server_Main.clientList.add(client);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}