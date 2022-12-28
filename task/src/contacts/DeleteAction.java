package contacts;

import java.util.ArrayList;

class DeleteAction extends Action {
    public DeleteAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        contacts = (ArrayList) SerializationUtils.deserialize(filePath);
        int record = Record.getRecordNumber();

        try {
            contacts.remove(record - 1);
            System.out.println("The record removed!");
            SerializationUtils.serialize(contacts, filePath);
        } catch (NumberFormatException e) {
            System.out.println("Invalid record number");
        }

        System.out.print(mainMenu);
    }
}
