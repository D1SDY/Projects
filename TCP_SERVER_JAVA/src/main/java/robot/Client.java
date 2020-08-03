package robot;

import java.io.*;
import java.net.Socket;
import java.net.InetAddress;

//does not have to extend Thread class
public class Client {
    public static void main(String[] args) {
        try {
            //getByName zjisti ip adresu
            Socket sock = new Socket(InetAddress.getByName("localhost"), 3703);
            OutputStream os = sock.getOutputStream();

            InputStreamReader in=new InputStreamReader(sock.getInputStream());
            BufferedReader bf=new BufferedReader(in);
            String str=bf.readLine();
            System.out.println(str);

            os.write((("Robot345\r" +
                    "hhhhjjj\r\r\rj" +
                    "\nfdjfjn\r\r\r\r\r\n687\r\nFOTO -1").getBytes()));
            System.out.println("Robot345");

            str=bf.readLine();
            System.out.println(str);
            str=bf.readLine();
            System.out.println(str);
            str=bf.readLine();
            System.out.println(str);
            os.write("INFO jfhjsdkg\r\n".getBytes());

//            os.write("674\r\n".getBytes());
//            System.out.println("674");
//
//            str=bf.readLine();
//            System.out.println(str);
//
//            os.write("INFO \\r \\n\\r \\n \\r\\nINFO \\r\\nX".getBytes());
//            str=bf.readLine();
//            System.out.println(str);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}