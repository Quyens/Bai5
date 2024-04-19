package com.vanquyenit.socketb2;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeClient extends Application {

    @FXML
    private Label timeLabel;

    @Override
    public void start(Stage primaryStage) {
        timeLabel = new Label();

        VBox root = new VBox(timeLabel);
        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("Clock");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Thực hiện gửi yêu cầu "time" đến server và cập nhật đồng hồ mỗi giây
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            try {
                updateTime();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTime() throws IOException {
        Socket socket = new Socket("localhost", 8080);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Gửi yêu cầu "time" đến server
        out.println("time");

        // Nhận thời gian từ server và cập nhật lên đồng hồ
        String response = in.readLine();
        timeLabel.setText(response);

        socket.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

