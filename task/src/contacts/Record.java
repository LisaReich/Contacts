package contacts;

import java.util.ArrayList;

class Record {
    public static String recordNumber;
    public static ArrayList<Contact> recordList;
    public static int getRecordNumber() {
        return Integer.parseInt(recordNumber);
    }
    public static ArrayList<Contact> getRecordList() {
        return recordList;
    }
}

