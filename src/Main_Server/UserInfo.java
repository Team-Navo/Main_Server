package Main_Server;

import java.net.InetAddress;
import java.net.Socket;

public class UserInfo {
    //String userIP;
    String userName;
    Socket clientSocket;
    InetAddress userIP;
    //회원정보 등록하는 테이블
    public UserInfo(InetAddress userIP, String userName, Socket clientSocket) {
        this.userIP=userIP;
        this.userName=userName;
        this.clientSocket=clientSocket;
    }
    public UserInfo() {
        this.userIP=null;
        this.userName=null;
        this.clientSocket=null;
    }
    public Socket getClient() {
        return clientSocket;
    }
    @Override
    public String toString() {
        return "[ClientIP : "+userIP+", Name : "+userName+"]";
    }
}