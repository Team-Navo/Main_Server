package Main_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

public class CommunicatetoClient implements Runnable {
    UserInfo info;
    PrintWriter out;
    BufferedReader in;
    String sendData;
    String recvData;
    public CommunicatetoClient(UserInfo info) throws IOException {
        this.info=info;
        out=new PrintWriter(info.getClient().getOutputStream(), true);
        in=new BufferedReader(new InputStreamReader(info.getClient().getInputStream()));
    }
    @Override
    public void run() {
        boolean isThread=true;
        while(isThread) {
            try {
                recvData=in.readLine();
                out.println(sendData);
                Thread.sleep(100);
            } catch(Exception e) {
                e.printStackTrace();
                try{
                    if(info.getClient()!=null) info.getClient().close();
                    in.close();
                    out.close();
                }catch(IOException ioE) {
                    ioE.printStackTrace();
                }
                isThread=false;
            }
        }
    }
    public void setSendData(String s) {
        this.sendData=s;
    }
}
