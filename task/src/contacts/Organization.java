package contacts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

class Organization extends Contact {

    private static final long serialVersionUID = -1809530119724627345L;
    protected String address;

    Organization(String name, String address, String number) {
        super(name, number);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public ArrayList<Field> getFields() {

        Class organization;

        try {
            organization = Class.forName("contacts.Organization");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<Field> protectedFields = new ArrayList<>();
        ArrayList<Field> protectedSuperFields = new ArrayList<>();

        Field[] thisFields = organization.getDeclaredFields();
        for (Field field : thisFields) {
            int modifiers = field.getModifiers();
            if (Modifier.isProtected(modifiers)) {
                protectedFields.add(field);
            }
        }

        Field[] superFields = organization.getSuperclass().getDeclaredFields();
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
            case "address":
                result = getAddress();
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
            case "address":
                setAddress(value);
                break;
            case "number":
                setPhoneNumber(value);
                break;
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
