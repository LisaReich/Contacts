package contacts;

import java.util.ArrayList;

class ListAction extends Action {
    public ListAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        contacts = (ArrayList) SerializationUtils.deserialize(filePath);

        if (contacts.isEmpty()) {
            System.out.println("No records founded.\n");
        } else {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), contacts.get(i).toString());
            }
        }

        Record.recordList = contacts;

        System.out.print(listMenu);
    }
}
