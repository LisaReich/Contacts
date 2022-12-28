package contacts;

import java.util.Scanner;
import java.util.regex.Pattern;

class Context {
    private Action action;
    String filePath;
    Scanner scanner = new Scanner(System.in);

    public Action chooseAction(Scanner scanner) {
        String actionType = scanner.nextLine();
        switch (actionType) {
            case "add":
                action = new AddAction(getFilePath()); break;
            case "list":
                action = new ListAction(getFilePath()); break;
            case "count":
                action = new CountAction(getFilePath()); break;
            case "search":
            case "again":
                action = new SearchAction(getFilePath()); break;
            case "back":
            case "menu":
                action = new MenuAction(getFilePath()); break;
            case "edit":
                action = new EditAction(getFilePath()); break;
            case "delete":
                action = new DeleteAction(getFilePath()); break;
            case "exit":
                action = new ExitAction(getFilePath()); break;
            default: {
                if (Pattern.compile("[1-9][\\d]*").matcher(actionType).matches()) {
                    action = new NumberAction(getFilePath());
                    Record.recordNumber = actionType;
                    break;
                } else {
                    action = new MenuAction(getFilePath());
                }
            }
        }

        return action;
    }

    public void setAction() {
        this.action = chooseAction(scanner);
    }

    public void executeAction() {
        action.execute();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

