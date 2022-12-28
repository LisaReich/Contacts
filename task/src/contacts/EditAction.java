package contacts;

import java.util.ArrayList;

class EditAction extends Action {
    public EditAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        contacts = (ArrayList) SerializationUtils.deserialize(filePath);

        int record = Record.getRecordNumber();

        Contact contact = contacts.get(record - 1);
        contacts.remove(record - 1);

        System.out.print("Select a field (");
        for (int i = 0; i < contact.getFields().size(); i++) {
            if (i == contact.getFields().size() - 1) {
                System.out.print(contact.getFields().get(i).getName() + "): ");
            } else {
                System.out.print(contact.getFields().get(i).getName() + ", ");
            }
        }

        String field = scanner.nextLine();
        System.out.printf("Enter: %s", field);

        String value = scanner.nextLine();
        contact.setValue(field, value);
        contact.setEditionDateTime();

        contacts.add(0, contact);

        SerializationUtils.serialize(contacts, filePath);

        System.out.print("Saved\n");
        Contact.getInfo(contact);

        System.out.print(recordMenu);
    }
}
