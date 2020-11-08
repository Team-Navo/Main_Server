package Main_Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class UserMade {
    ServerRecv serverRecv;
    ServerSend serverSend;
    CommunicatetoClient comm;
    UserInfo info;
    public void streamSetting() {
        try{
            //serverRecv = new ServerRecv(info);
            //serverSend = new ServerSend(info);
            comm=new CommunicatetoClient(info);
            new Thread(comm).start();
            //new Thread(serverRecv).start();
            //new Thread(serverSend).start();
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
    public UserMade(UserInfo info){
        this.info=info;
        streamSetting();
    }
    public void castPrivate(String s) {
        comm.setSendData(s);
    }
}
