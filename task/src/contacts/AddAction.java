package contacts;

import java.io.File;
import java.util.ArrayList;

class AddAction extends Action {

    public AddAction(String fileName) {
        super(fileName);
    }

    @Override
    public void execute() {
        File file = new File(filePath);

        if (file.exists()) {
            contacts = (ArrayList) SerializationUtils.deserialize(filePath);
        }

        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        if (type.equals("person")) {

            System.out.print("Enter the name: ");
            String name = scanner.nextLine();

            System.out.print("Enter the surname: ");
            String surname = scanner.nextLine();

            System.out.print("Enter the birth date: ");
            String birthDate = scanner.nextLine();

            System.out.print("Enter the gender: ");
            String gender = scanner.nextLine();

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();

            Person person = new Person(name, surname, phoneNumber, gender, birthDate);

            person.setCreationDateTime();
            person.setEditionDateTime();

            contacts.add(0, person);

        } else if (type.equals("organization")) {

            System.out.print("Enter the organization name: ");
            String name = scanner.nextLine();

            System.out.print("Enter the address: ");
            String address = scanner.nextLine();

            System.out.print("Enter the number: ");
            String phoneNumber = scanner.nextLine();

            Organization organization = new Organization(name, address, phoneNumber);

            organization.setCreationDateTime();
            organization.setEditionDateTime();

            contacts.add(0, organization);
        }

        System.out.print("The record added.\n");

        SerializationUtils.serialize(contacts, filePath);

        System.out.print(mainMenu);
    }
}