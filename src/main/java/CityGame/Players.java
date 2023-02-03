package CityGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Players {
    public static void main(String[] args) {
        String name = "player ";
        int i = 0;
        try (Socket socket = new Socket("127.0.0.1", 7777);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.println(name + i);
                i++;
                System.out.println(in.readLine());
                System.out.println(in.readLine());
                System.out.println(in.readLine());
                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
