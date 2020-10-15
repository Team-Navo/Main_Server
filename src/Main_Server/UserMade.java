package Main_Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class UserMade {
    Socket clientSocket;

    ServerRecv serverRecv;
    ServerSend serverSend;

    public void streamSetting() {
        try{
            clientSocket.getInetAddress();
            serverRecv = new ServerRecv(clientSocket);
            serverSend = new ServerSend(clientSocket);
            new Thread(serverRecv).start();
            new Thread(serverSend).start();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public UserMade(Socket clientSocket){
        this.clientSocket = clientSocket;
        streamSetting();
    }
}
