package Main_Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Test_Main {
    private Socket clientSocket;
    BufferedReader in;
    PrintWriter out;

    public static void main(String[] args) {
        new Test_Main();
    }

    public Test_Main() {
        connect();
        streamSetting();
        dataSend();
        dataRecv();
    }

    public void connect() {
        try{
            clientSocket=new Socket("yjpcpa.ddns.net",1120);
            System.out.println("[Client] Connected to server");
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void dataRecv() {
        new Thread(new Runnable() {
            boolean isThread = true;
            @Override
            public void run() {
                while(isThread)
                    try {
                        String recvData=in.readLine();
                        if(recvData.equals("/quit"))
                            isThread=false;
                        else
                            System.out.println("Server : " + recvData);
                    }
                    catch (Exception e) {
                        System.out.println(e.toString());
                        try {
                            if(clientSocket != null)
                                clientSocket.close();
                            in.close();
                        } catch (IOException ioE) {
                            ioE.printStackTrace();
                        }
                        isThread = false;
                    }
            }
        }).start();
    }
    public void dataSend() {
        new Thread(new Runnable() {
            Scanner input=new Scanner(System.in);
            boolean isThread=true;
            @Override
            public void run() {
                while(isThread)
                    try {
                        String sendData=input.nextLine();
                        if(sendData.equals("/quit"))
                            isThread=false;
                        else
                            out.println(sendData);
                    }
                    catch (Exception e) {
                        System.out.println(e.toString());
                        try {
                            if(clientSocket != null)
                                clientSocket.close();
                            out.close();
                        } catch (IOException ioE) {
                            ioE.printStackTrace();
                        }
                        isThread = false;
                    }
            }
        }).start();
    }
    public void streamSetting() {
        try{
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }
}