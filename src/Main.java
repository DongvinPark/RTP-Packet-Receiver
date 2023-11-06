import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("MTX 9554 Receiver Start!!");

        int cnt = 0;

        long start = System.currentTimeMillis();
        Socket tcpSocket = new Socket("192.168.50.76", 9554);
        long end = System.currentTimeMillis();

        long end2 = 0L;
        long newStart = 0L;
        LocalDateTime switchRequestSentTime = null;
        try {

            // wait 2 sec and then send switching request

            long boring = Integer.MAX_VALUE * 1000000000L;
            while (true){
                boring--;
                if (boring==0) break;
            }

            System.out.println("AWAKEN!!");

            newStart = System.currentTimeMillis();
            switchRequestSentTime = LocalDateTime.now();
            OutputStream ow = tcpSocket.getOutputStream();
            ow.write(new byte[]{0,1,2});

            /*while (true) {
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
                    System.out.println("Break Enter / socket connection state : " + tcpSocket.isConnected());
                    break;
                }
            }// while*/
        } catch (Exception e) {
            System.out.println("Exception was Thrown!!");
            e.printStackTrace();
        } finally {
            System.out.println("\n\t Elapsed Time - making Tcp Socket : " + (end-start) + "\n");

            System.out.println("\n\t All Elapsed Time : " + (end2 - start));

            System.out.println("\n\t Elapsed time after tcp socket made : " + (end2 - end));

            System.out.println("\n\t Elapsed Time - conn already exists : " + (end2 - newStart));
            tcpSocket.close();

            System.out.println(switchRequestSentTime);
        }

    }// main
}// end of main class