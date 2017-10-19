package forserver;

import java.io.*;


public class ServerSimpleGUI {


    public ServerSimpleGUI() {
    }

    public static void main(String[] args) throws IOException {

        ServerCore sc = new ServerCore(4444);
        sc.startListening();
    }
}