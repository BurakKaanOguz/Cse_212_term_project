package engine;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.*;


public class UserChecker {

    private String DATA = "game_data.csv";
    private String currentUser = null;


    public boolean loginOrRegister(String username, String password) {
        File file = new File(DATA);
        boolean userExists = false;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    userExists = true;
                    if (parts[1].equals(password)) {
                        currentUser = username;
                        reader.close();
                        return true;
                    } else {
                        reader.close();
                        return false;
                    }
                }
            }
            reader.close();

            if (!userExists) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(DATA, true));
                writer.write(username + "," + password + ",0,N/A,N/A");
                writer.newLine();
                writer.close();
                currentUser = username;
                return true;
            }

        } catch (IOException e) {
            System.out.println("login error");
        }
        return false;
    }


    public void updateScoreAndSession(int newScore, String gameTime) {
        if (currentUser == null) return;

        List<String> fileContent = new ArrayList<>();
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        try {
            BufferedReader reader = new BufferedReader(new FileReader(DATA));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(currentUser)) {
                    fileContent.add(currentUser + "," + parts[1] + "," + newScore + "," + date + "," + gameTime);
                } else {
                    fileContent.add(line);
                }
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
            for (String row : fileContent) {
                writer.write(row);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("save error");
        }
    }


    public String getCurrentUser() {
        return currentUser;
    }


    public boolean isLoggedIn() {
        return currentUser != null;
    }
}