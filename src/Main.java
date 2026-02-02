import engine.*;
import screens.*;

public class Main {

    public static void main(String[] args) {
        UserChecker userChecker = new UserChecker();
        MainMenu mainMenu = new MainMenu(userChecker);
        mainMenu.setVisible(true);
    }
}