import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int N = 0;
        String X = "";
        for(int i=31775;i<=32355;i++) {
            try {
                Socket socket = new Socket("172.21.48.145", i);
                InputStream sis = socket.getInputStream();
                OutputStream sos = socket.getOutputStream();

                InputStreamReader sisr = new InputStreamReader(sis);
                OutputStreamWriter sosw = new OutputStreamWriter(sos);

                BufferedReader br = new BufferedReader(sisr);
                BufferedWriter bw = new BufferedWriter(sosw);

                bw.write("765649\n");
                bw.newLine();
                bw.flush();

                String tmp = "";
                tmp = br.readLine();
                N++;
                X=tmp;


                sis.close();
                sos.close();

                sisr.close();
                sosw.close();

                socket.close();
            }catch (Exception e){
                System.out.println(e);
            }


        }

        System.out.println(N+" "+X);
    }
}
