package contacts;

public class Main {
    public static void main(String[] args) {

        Context context = new Context();

        try {
            String pathFile = args[0];
            context.setFilePath(pathFile);
        } catch (ArrayIndexOutOfBoundsException e) {
            String pathFile = "contacts.txt";
            context.setFilePath(pathFile);
        }

        System.out.print("[menu] Enter action (add, list, search, count, exit): ");

        while (true) {
            context.setAction();
            context.executeAction();
        }
    }
}


