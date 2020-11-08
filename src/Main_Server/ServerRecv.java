package Main_Server;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerRecv implements Runnable{
    BufferedReader in;
    UserInfo info;

    public ServerRecv(UserInfo info) throws IOException {
        this.info=info;
        in=new BufferedReader(new InputStreamReader(info.getClient().getInputStream()));
    }
    @Override
    public void run() {
        boolean isThread = true;
        while(isThread) {
            try {
                String recvData = in.readLine();
                if (recvData.equals("/quit")) {
                    isThread = false;
                }
                else
                    System.out.println("Client : " + recvData);

            } catch (Exception e) {
                System.out.println(e.toString());
                e.printStackTrace();
                try {
                    if(info.getClient() != null)
                        info.getClient().close();
                    in.close();
                } catch (IOException ioE) {
                    ioE.printStackTrace();
                }
                isThread = false;
            }
        }
    }
}