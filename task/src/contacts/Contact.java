package contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoUnit.MINUTES;

abstract class Contact implements Serializable {

    private static final long serialVersionUID = -1578892346962025930L;
    private LocalDateTime creationDateTime;
    private LocalDateTime editionDateTime;

    protected String name;
    protected String number;

    Contact(String name, String number) {
        this.name = name;
        this.number = (isNumberValid(number) ? number : "[no number]");
    }

    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return number;
    }
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
    public LocalDateTime getEditionDateTime() {
        return editionDateTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String number) {
        this.number = (isNumberValid(number) ? number : "[no number]");
    }
    public void setCreationDateTime() {
        this.creationDateTime = LocalDateTime.now().truncatedTo(MINUTES);
    }
    public void setEditionDateTime() {
        this.editionDateTime = LocalDateTime.now().truncatedTo(MINUTES);
    }

    public abstract ArrayList<Field> getFields();

    public abstract String getValue(String field);

    public abstract void setValue(String field, String value);

    public static void getInfo(Contact contact) {

        for (Field f : contact.getFields()) {
            if (f.getName().equals("birth")) {
                System.out.print("Birth date: ");
            } else if (f.getName().equals("name")) {
                if (contact instanceof Organization) {
                    System.out.print("Organization name: ");
                } else {
                    System.out.print("Name: ");
                }
            } else {
                System.out.print(capitalize(f.getName()) + ": ");
            }
            System.out.print(contact.getValue(f.getName()) + "\n");
        }


        System.out.print("Time created: " +
                contact.getCreationDateTime() + "\n");
        System.out.print("Time last edit: " +
                contact.getEditionDateTime() + "\n");
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    protected boolean isNumberValid(String number) {
        boolean isValid = true;

        Pattern pattern = Pattern.compile("\\+?((\\([A-Za-z\\d]+\\)((-|\\s)" +
                "[A-Za-z\\d]{2,})?)" +
                "|([A-Za-z\\d]+((-|\\s)\\([A-Za-z\\d]{2,}\\))?)" +
                "|([A-Za-z\\d]+((-|\\s)[A-Za-z\\d]{2,})?))" +
                "((-|\\s)[A-Za-z\\d]{2,})*");
        Matcher matcher = pattern.matcher(number);

        return (matcher.matches() == isValid);
    }
}
