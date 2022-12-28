package contacts;


class NumberAction extends Action {
    public NumberAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        contacts = Record.getRecordList();
        int record = Record.getRecordNumber();
        Contact.getInfo(contacts.get(record - 1));
        System.out.print(recordMenu);
    }
}

