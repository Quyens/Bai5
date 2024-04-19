import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Kết nối tới server
            Socket socket = new Socket("localhost", 8080);

            // Lấy luồng đầu ra để gửi dữ liệu tới server
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            // Lấy luồng đầu vào để nhận dữ liệu từ server
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Đọc dữ liệu từ bàn phím và gửi tới server
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = keyboardReader.readLine()) != null) {
                writer.println(input); // Gửi dữ liệu tới server

                String serverResponse = reader.readLine(); // Đọc phản hồi từ server
                System.out.println("Server: " + serverResponse);
            }

            // Đóng kết nối
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
