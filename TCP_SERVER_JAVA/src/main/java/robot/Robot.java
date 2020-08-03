package robot;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Logger;


public class Robot {
    static private int socketNumber = 3703;
    static public Socket connection;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(socketNumber);

        while (true) {
            connection = serverSocket.accept();
            connection.setSoTimeout(45000);
            ServSocket socet = new ServSocket(connection);
            Thread t1 = new Thread(socet);
            t1.start();
        }
    }

}


class ServSocket extends Messages implements Runnable {
    private final static Logger LOGGER = Logger.getLogger(Robot.class.getName());
    public Socket connection;

    public ServSocket(Socket sok) {
        this.connection = sok;
    }

    OutputStream output;
    int tempReg=0;


    @Override
    public void run() {
        try {
            System.out.println("Connetion added..... on....." + Thread.currentThread().getName());
            output = connection.getOutputStream();
            output.write(MESSAGE_200.getBytes());
            output.flush();

            InputStream ir = connection.getInputStream();
            BufferedInputStream bf = new BufferedInputStream(ir);
            StringBuilder login = checkInput(bf);
            int requiredPassword = 0;
            requiredPassword=tempReg;
            System.out.println("tempReg=  "+tempReg+Thread.currentThread().getName());
            output.write(MESSAGE_201.getBytes());
            output.flush();
            StringBuilder password = checkInput(bf);
            System.out.println(password+Thread.currentThread().getName());
            int foundPassword = 0;
            try {
                foundPassword = Integer.parseInt(password.toString());
                System.out.println("found=  "+foundPassword+Thread.currentThread().getName());
            } catch (NumberFormatException nfe) {
                output.write(MESSAGE_500.getBytes());
                output.flush();
                connection.close();
            }
            if (login.toString().startsWith("Robot")&& requiredPassword == foundPassword) {
                output.write(MESSAGE_202.getBytes());
                output.flush();//edited
            } else {
                output.write(MESSAGE_500.getBytes());
                output.flush();
                connection.close();
                return;
            }
            output.flush();
            while (connection.isClosed() == false) {
                String str = "";
                for (int i = 0; i < 5; i++) {
                    str += (char) bf.read();
                    if (connection.isClosed()) break;
                    if (i == 0 && (!str.equals("I") && !str.equals("F"))
                            || i == 1 && (!str.equals("IN") && !str.equals("FO"))
                            || i == 2 && (!str.equals("INF") && !str.equals("FOT"))
                            || i == 3 && (!str.equals("INFO") && !str.equals("FOTO"))
                            || i == 4 && (!str.equals("INFO ") && !str.equals("FOTO "))) {
                        output.write(MESSAGE_501.getBytes());
                        output.flush();//edited
                        connection.close();
                        break;
                    }

                }
                if (str.startsWith("INFO ")) {
                    String log = checkInput(bf).toString();
                    LOGGER.info(log);
                    output.write(MESSAGE_202.getBytes());
                    output.flush();
                } else if (str.startsWith("FOTO ")) {
                    StringBuilder StringOfBytes = new StringBuilder();
                    int input;
                    while (true) {//addede
                        input = bf.read();
                        if (input == 45) {
                            output.write(MESSAGE_501.getBytes());
                            connection.close();
                            return;
                        }
                        if ((char) input == ' ') {
                            break;
                        }
                        StringOfBytes.append((char) input);
                    }
                    StringBuilder data = new StringBuilder();
                    int sumOfButes = 0;
                    int numberOfBytes = 0;
                    Boolean numeric = true;
                    try {
                        numberOfBytes = Integer.parseInt(StringOfBytes.toString());
                    } catch (NumberFormatException e) {
                        numeric = false;
                    }
                    if (numeric) {
                        for (int i = 0; i < numberOfBytes; i++) {
                            input = bf.read();
                            sumOfButes += input;
                            data.append((char) input);
                        }
                        StringBuilder controlSummString = new StringBuilder();
                        for (int i = 0; i < 4; i++) {
                            input = bf.read();
                            controlSummString.append(Integer.toHexString(input));
                        }
                        StringBuilder finalSumOfButes = new StringBuilder();
                        for (int i = 0; i < controlSummString.toString().toCharArray().length; i++) {
                            if (controlSummString.toString().toCharArray()[i] != '0') {
                                finalSumOfButes.append(controlSummString.substring(i));
                                break;
                            }
                        }
                        if (Integer.toHexString(sumOfButes).equals(finalSumOfButes.toString())) {
                            output.write(MESSAGE_202.getBytes());
                            output.flush();
                        } else {
                            output.write(MESSAGE_300.getBytes());
                            output.flush();
                        }
                    } else {
                        output.write(MESSAGE_501.getBytes());
                        output.flush();
                        connection.close();
                        return;
                    }
                } else {
                    output.write(MESSAGE_501.getBytes());
                    output.flush();
                    connection.close();
                    return;
                }

            }
        } catch (SocketTimeoutException e) {
            try {
                output.write(MESSAGE_502.getBytes());
                connection.close();
            } catch (IOException ex) {
            }
        } catch (IOException e) {
        }
    }

    private StringBuilder checkInput(BufferedInputStream bf) throws IOException {
        int current = 13;
        int next = 10;
        int input;
        int input2;
        StringBuilder output = new StringBuilder();
        input = bf.read();
        while (true) {
            if (input == -1) {
                break;
            } else {
                input2 = bf.read();
                if (input != current||(input2!=next&&input==current)||(input2==next&&input!=current)) {
                    output.append((char) input);
                    tempReg+=input;
                }else {
                    if (input2 == next) {
                        break;
                    }
                }
                input = input2;
            }
        }
        return output;
    }
}

class Messages {
    public final static String MESSAGE_200 = "200 LOGIN\r\n";
    public final static String MESSAGE_201 = "201 PASSWORD\r\n";
    public final static String MESSAGE_202 = "202 OK\r\n";
    public final static String MESSAGE_300 = "300 BAD CHECKSUM\r\n";
    public final static String MESSAGE_500 = "500 LOGIN FAILED\r\n";
    public final static String MESSAGE_501 = "501 SYNTAX ERROR\r\n";
    public final static String MESSAGE_502 = "502 TIMEOUT\r\n";
}