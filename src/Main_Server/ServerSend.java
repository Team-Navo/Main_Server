package Main_Server;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerSend implements Runnable{
    PrintWriter out;
    UserInfo info;
    public ServerSend(UserInfo info) throws IOException {
        this.info=info;
        out=new PrintWriter(info.getClient().getOutputStream(), true);
    }
    @Override
    public void run() {
        //입력부. 나중에 정보 업데이트 되는걸로 바꿔야 함.
        Scanner input=new Scanner(System.in);
        boolean isThread=true;
        while(isThread) {
            try {
                String sendData = input.nextLine();
                //연결을 끊을 때. /quit이라는 명령어가 들어가면 연결을 끊도록 설정.
                if (sendData.equals("/quit")) {
                    isThread = false;
                }
                else
                    out.println(sendData);
                    //out.flush();
            } catch (Exception e) {
                System.out.println(e.toString());
                try {
                    if(info.getClient() != null)
                        info.getClient().close();
                    out.close();
                } catch (IOException ioE) {
                    ioE.printStackTrace();
                }
                isThread = false;
            }
        }
    }
}
