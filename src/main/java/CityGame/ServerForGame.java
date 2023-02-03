package CityGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForGame {
    public static void main(String[] args) {
        String city = "???";
        String newCity = null;
        try (ServerSocket serverSocket = new ServerSocket(7777)) {
            System.out.println("Сервер работает");
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                while (true) {
                    String name = in.readLine();
                    if (name.equals("player 0")) {
                        out.println("--------< Игра в города началась! >--------\n" +
                                "Добро пожаловать " + name + "\n"
                                + "Введите город " + city);
                        city = in.readLine();
                        out.println("OK");
                    } else {
                        out.println(String.format("Добро пожаловать " + name + "\n" +
                                "Последний введённый город в игре: -> " + city + "\n" +
                                "Вы должны назвать город на букву -< " + city.charAt(city.length() - 1) + " >-"));
                        newCity = in.readLine();
                        if (newCity.charAt(0) == city.charAt(city.length() - 1)) {
                            out.println("OK");
                            city = newCity;
                        } else {
                            out.println("NOT OK");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
