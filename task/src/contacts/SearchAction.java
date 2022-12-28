package contacts;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;

class SearchAction extends Action {
    public SearchAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        contacts = (ArrayList) SerializationUtils.deserialize(filePath);

        StringBuilder sb = new StringBuilder();
        ArrayList<Contact> foundedContacts = new ArrayList<>();

        System.out.print("Enter search query: ");
        String query = scanner.nextLine();

        for (Contact contact : contacts) {
            for (Field f : contact.getFields()) {
                sb.append(contact.getValue(f.getName()));
            }
            if (Pattern.compile(".*" + query + ".*", Pattern.CASE_INSENSITIVE).matcher(sb.toString()).matches()) {
                foundedContacts.add(contact);
            }
            sb.setLength(0);
        }

        if (!foundedContacts.isEmpty()) {
            System.out.printf("Found %d results:\n", foundedContacts.size());
            for (int i = 0; i < foundedContacts.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), foundedContacts.get(i).toString());
            }
        } else {
            System.out.println("No records founded.");
        }

        Record.recordList = foundedContacts;

        System.out.print(searchMenu);
    }
}
