import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("MTX 9554 Receiver Start!!");

        int cnt = 0;

        long start = System.currentTimeMillis();
        //Thread.sleep(5000);
        Socket tcpSocket = new Socket("192.168.50.76", 9554);
        long end = System.currentTimeMillis();


        long end2 = 0L;
        System.out.println("\n\t Elapsed Time - making Tcp Socket : " + (end-start) + "\n");

        long newStart = 0L;
        try {

            // wait 5 sec and then send switching request
            Thread.sleep(5000);
            OutputStream ow = tcpSocket.getOutputStream();
            ow.write(new byte[]{0,1,2});
            newStart = System.currentTimeMillis();

            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(tcpSocket.getInputStream()));
                String result = br.readLine();
                if (result != null) {
                    if (cnt < 1){
                        end2 = System.currentTimeMillis();
                        System.out.println("result = " + result);
                        //System.out.println("Receive First Packet : " + LocalDateTime.now());
                        cnt++;
                    }
                } else {
                    // Stop this program when,
                    // RTSP server terminated
                    // OR Read Phone stops PLAY
                    break;
                }
            }// while
        } catch (Exception e) {
            System.out.println("Exception was Thrown!!");
        } finally {
            System.out.println("\n\n\t Elapsed time after tcp socket made : " + (end2 - end));
            //System.out.println("\n\n\t All Elapsed Time : " + (end2 - start));
            tcpSocket.close();

            System.out.println("\n\n\t Elapsed Time - conn already exists : " + (end2 - newStart));
            tcpSocket.close();
        }

    }// main
}// end of main class