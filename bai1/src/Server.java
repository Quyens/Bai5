import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        try {
            // Tạo server socket và lắng nghe kết nối tới cổng 8080
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for connections...");

            // Chấp nhận kết nối từ client
            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());

            // Lấy luồng đầu vào để nhận dữ liệu từ client
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Lấy luồng đầu ra để gửi dữ liệu tới client
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Đọc dữ liệu từ client và ghi ra console
            String input;
            while ((input = reader.readLine()) != null) {
                System.out.println("Client: " + input);

                // Đọc dữ liệu từ bàn phím và gửi tới client
                BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
                String serverInput = keyboardReader.readLine();
                writer.println(serverInput); // Gửi dữ liệu tới client
            }

            // Đóng kết nối
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
