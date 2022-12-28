package contacts;

import java.io.File;


class ExitAction extends Action {
    public ExitAction(String filePath) {
        super(filePath);
    }

    @Override
    public void execute() {
        File file = new File(filePath);
        file.delete();

        System.exit(0);
    }
}

