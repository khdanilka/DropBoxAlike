package forclients;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {


    public static void main(String[] args) throws IOException {

        String host = "127.0.0.1";
        int port = 4444;
        //String name = "text.txt";
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        File file = new File("./" + name);

        try (Socket socket = new Socket(host,port);
             InputStream in = new FileInputStream(file);
             OutputStream out = socket.getOutputStream())
        {
            writeNameToOut(out,name);
            writeDataToOut(out,in);
        }
    }

    static void writeNameToOut(OutputStream out, String name) throws IOException {

        byte[] bytes = new byte[100];
        for(int i = 0; i < bytes.length; i++){
            bytes[i] = 0;
        }
        byte[] nameB = name.getBytes("UTF-8");

        for(int i = 0; i <nameB.length; i++){
            bytes[i] = nameB[i];
        }
        out.write(bytes);
    }

    static void writeDataToOut(OutputStream out, InputStream in) throws IOException {
        byte[] bytesData = new byte[16 * 1024];
        int count;
        while ((count = in.read(bytesData)) > 0) {
            out.write(bytesData, 0, count);
        }
    }

}