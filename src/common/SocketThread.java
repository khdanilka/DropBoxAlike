package common;

import java.io.*;
import java.net.Socket;


public class SocketThread extends Thread{

    private Socket socket;
    private SocketThreadListener eventListener;

    public SocketThread(SocketThreadListener eventListener,Socket socket){
        this.eventListener = eventListener;
        this.socket = socket;
        start();
    }

    @Override
    public void run() {
        eventListener.readySocketClientThread(this);

        OutputStream out = null;
        try (InputStream in = socket.getInputStream()) {

            byte[] b = new byte[100];
            in.read(b, 0, b.length);
            String str = new String(b, "UTF-8");
            File myPath = new File("./server/");
            myPath.mkdirs();
            out = new FileOutputStream("./server/" + str.trim());

            byte[] bytes = new byte[16 * 1024];
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
