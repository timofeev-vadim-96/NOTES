package noteBook.view;

import noteBook.presenter.Presenter;
import noteBook.util.Command;

import java.util.*;

public class View {
    Presenter presenter;
    Scanner in;

    public View() {
        this.presenter = new Presenter();
        this.in = new Scanner(System.in);
    }

    public void run() {
        presenter.fillTempDataBase(); //считываем с файла с базой данных всю информацию во временную БД
        Command com;
        instruction();
        while (true) {
            String command = prompt("Enter the command: (CREATE/SAVE/READ/EDIT/REMOVE/FIND/HELP/EXIT)\n").toUpperCase();
            commandValidation(command);
            com = Command.valueOf(command);
            if (com == Command.EXIT) return;
            switch (com) {
                case CREATE:
                    createNewNote();
                    break;
                case SAVE:
                    presenter.saveChanges();
                    System.out.println("Saved successfully");
                    break;
                case READ:
                    readNotes();
                    break;
                case EDIT:
                    editNote();
                    break;
                case REMOVE:
                    removeNote();
                    break;
                case FIND:
                    findByDate();
                    break;
                case HELP:
                    instruction();
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    private boolean commandValidation(String action) {
        try {
            Enum.valueOf(Command.class, action);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unexpected command! ");
        }
    }

    public void instruction() {
        System.out.printf(
                "****NOTES APPLICATION****\n" +
                        "INSTRUCTION:\n" +
                        "CREATE - create a new note\n" +
                        "SAVE - save changes\n" +
                        "READ - read the entire list of your notes\n" +
                        "EDIT - edit a note\n" +
                        "FIND - find a note by date\n" +
                        "HELP - show the instruction\n" +
                        "REMOVE - remove a note\n" +
                        "EXIT - exit the app\n");
    }

    public void createNewNote() {
        String title = prompt("Enter the title: ");
        String body = prompt("Enter the body: ");
        presenter.createNewNote(title, body);
    }

    public void readNotes() {
        if (presenter.isEmptyNotes()) System.out.println("Your note book is empty yet");
        else {
            System.out.println("Your notes: ");
            presenter.read();
        }
    }

    public void editNote() {
        int indexOfNote = Math.abs(Integer.parseInt(prompt("Enter the index of the note\n")));
        if (presenter.isIndexExists(indexOfNote)) {
            System.out.printf("Edible note is: %s\n", presenter.getNote(indexOfNote));
            String newTitle = prompt("Enter the new title: \n");
            String newBody = prompt("Enter the new body: \n");
            presenter.editNote(indexOfNote, newTitle, newBody);
            System.out.println("The note has been changed successfully");
        } else System.out.println("There are no notes with such an index");
    }

    public void removeNote() {
        int indexOfNote = Math.abs(Integer.parseInt(prompt("Enter the index of the note\n")));
        if (presenter.isIndexExists(indexOfNote)) {
            presenter.removeNote(indexOfNote);
            System.out.println("The note has been removed successfully");
        } else System.out.println("There are no notes with such an index");
    }

    public void findByDate() {
        String inputDateFrom = prompt("Enter the date AFTER which to search in the format \"31.12.2023\": \n");
        if (!presenter.checkDate(inputDateFrom)) {
            System.out.println("Unexpected date format");
            return;
        }
        String inputDateAfter = prompt("Enter the date BEFORE which to search in the format \"31.12.2023\": \n");
        if (!presenter.checkDate(inputDateAfter)) {
            System.out.println("Unexpected date format");
            return;
        }
        List<String> beforeList = new ArrayList<>(Arrays.asList(inputDateFrom.split("\\.")));
        Date after = new Date(Integer.parseInt(beforeList.get(2)) - 1900, Integer.parseInt(beforeList.get(1)) - 1,
                Integer.parseInt(beforeList.get(0)));
        List<String> afterList = new ArrayList<>(Arrays.asList(inputDateAfter.split("\\.")));
        Date before = new Date(Integer.parseInt(afterList.get(2)) - 1900,
                Integer.parseInt(afterList.get(1)) - 1, Integer.parseInt(afterList.get(0)));
        presenter.printNotes(presenter.findByDate(after, before));
    }
}
