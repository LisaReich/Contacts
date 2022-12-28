package contacts;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Action {

    final Scanner scanner = new Scanner(System.in);
    String filePath;
    ArrayList<Contact> contacts = new ArrayList<>();
    String mainMenu = "\n[menu] Enter action (add, list, search, count, exit): ";
    String recordMenu = "\n[record] Enter action (edit, delete, menu): ";
    String listMenu = "\n[list] Enter action ([number], back): ";
    String searchMenu = "\n[search] Enter action ([number], back, again): ";

    public Action(String filePath) {
        this.filePath = filePath;
    }

    public abstract void execute();

}
