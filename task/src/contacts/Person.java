package contacts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Person extends Contact {
    private static final long serialVersionUID = -4822255527665197950L;
    protected String surname;
    protected String birth;
    protected String gender;

    Person(String name, String surname, String number, String gender, String birth) {
        super(name, number);
        this.surname = surname;
        this.gender = isGenderValid(gender) ? gender : "[no data]";
        this.birth = isBirthDateValid(birth) ? birth : "[no data]";
    }

    public String getSurname() { return surname; }
    public String getBirthDate() {
        return birth;
    }
    public String getGender() {
        return gender;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setBirthDate(String birth) {
        this.birth = isBirthDateValid(birth) ? birth : "[no data]";
    }
    public void setGender(String gender) {
        this.gender = isGenderValid(gender) ? gender : "[no data]";
    }

    @Override
    public ArrayList<Field> getFields() {

        Class person;

        try {
            person = Class.forName("contacts.Person");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Field> protectedFields = new ArrayList<>();
        ArrayList<Field> protectedSuperFields = new ArrayList<>();

        Field[] thisFields = person.getDeclaredFields();
        for (Field field : thisFields) {
            int modifiers = field.getModifiers();
            if (Modifier.isProtected(modifiers)) {
                protectedFields.add(field);
            }
        }

        Field[] superFields = person.getSuperclass().getDeclaredFields();
        for (Field field : superFields) {
            int modifiers = field.getModifiers();
            if (Modifier.isProtected(modifiers)) {
                protectedSuperFields.add(field);
            }
        }

        protectedFields.add(0,protectedSuperFields.get(0));
        protectedFields.add(protectedFields.size(), protectedSuperFields.get(1));

        return protectedFields;
    }

    @Override
    public String getValue(String field) {
        String result = "";
        switch (field) {
            case "name":
                result = getName();
                break;
            case "surname":
                result = getSurname();
                break;
            case "birth":
                result = getBirthDate();
                break;
            case "gender":
                result = getGender();
                break;
            case "number":
                result = getPhoneNumber();
                break;
        }
        return result;
    }

    @Override
    public void setValue(String field, String value) {
        switch (field) {
            case "name":
                setName(value);
                break;
            case "surname":
                setSurname(value);
                break;
            case "birth":
                setBirthDate(value);
                break;
            case "gender":
                setGender(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }
    }

    private boolean isBirthDateValid(String date) {
        boolean isValid = true;

        Pattern pattern = Pattern.compile("[1,2]\\d{3}\\-(0\\d|1[0-2])\\-([0-2]\\d|3[0,1])");
        Matcher matcher = pattern.matcher(date);

        return (matcher.matches() == isValid);
    }

    private boolean isGenderValid(String gender) {
        return Pattern.compile("[M,F]").matcher(gender).matches();
    }

    @Override
    public String toString() {
        return getName() + " " + getSurname();
    }
}