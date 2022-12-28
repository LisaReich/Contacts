package contacts;

class MenuAction extends Action {
    public MenuAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        System.out.print(mainMenu);
    }
}
