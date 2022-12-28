package contacts;

import java.io.File;
import java.util.ArrayList;

class CountAction extends Action {
    public CountAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        File file = new File(filePath);

        if (file.exists()) {
            contacts = (ArrayList) SerializationUtils.deserialize(filePath);
            if (!contacts.isEmpty()) {
                System.out.printf("The Phone Book has %d records.\n", contacts.size());
            }
        } else {
            System.out.print("The Phone Book has 0 records.\n");
        }

        System.out.print(mainMenu);
    }
}