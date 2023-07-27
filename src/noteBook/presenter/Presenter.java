package noteBook.presenter;

import com.google.gson.Gson;
import noteBook.dao.impl.JsonDB;
import noteBook.dao.impl.TempDataBase;
import noteBook.mapper.Mapper;
import noteBook.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Presenter {
    TempDataBase temporary;
    JsonDB jsonDataBase;
    Gson gson;
    Mapper mapper;

    public Presenter() {
        this.temporary = new TempDataBase();
        this.jsonDataBase = new JsonDB();
        this.gson = new Gson();
        this.mapper = new Mapper();
    }

    public void createNewNote(String title, String body) {
        Note note = new Note(title, body);
        temporary.add(note);
    }

    public void saveChanges() {
        jsonDataBase.save(mapper.notesListToJson(temporary.getNotes()));
    }

    public void fillTempDataBase() {
        temporary.fillTheList(mapper.jsonListToNotes(jsonDataBase.read()));
    }

    public void read() {
        temporary.read();
    }

    public boolean isEmptyNotes() {
        return temporary.getNotes().size() == 0;
    }

    public boolean isIndexExists(int index) {
        return temporary.getNotes().size() > index;
    }

    public void editNote(int index, String title, String body) {
        temporary.getNotes().get(index).setTitle(title);
        temporary.getNotes().get(index).setBody(body);
        temporary.getNotes().get(index).setDate(new Date());
    }

    public Note getNote(int index) {
        return temporary.getNotes().get(index);
    }

    public void removeNote(int index) {
        temporary.remove(index);
    }

    public List<Note> findByDate(Date after, Date before) {
        List<Note> notes = temporary.getNotes();
        List<Note> foundNotes = new ArrayList<>();
        boolean flag = true;
        for (Note note : notes) {
            Date date = note.getDate();
            if (date.after(after) && date.before(before)) {
                foundNotes.add(note);
            }
        }
        return foundNotes;
    }

    public boolean checkDate(String inputDate) {
        List<String> dateList = new ArrayList<String>(Arrays.asList(inputDate.split("\\.")));
        if (dateList.size() != 3) {
            return false;
        } else if (dateList.get(0).length() != 2
                && Integer.parseInt(dateList.get(0)) > 31) {
            return false;
        } else if (dateList.get(1).length() != 2
                && Integer.parseInt(dateList.get(1)) > 12) {
            return false;
        } else if (dateList.get(2).length() != 4) {
            return false;
        } else return true;
    }

    public void printNotes(List<Note> notes) {
        if (notes.isEmpty()) System.out.println("There were no notes in the specified interval\n");
        else {
            System.out.println("Notes at specified intervals: ");
            for (int i = 0; i < notes.size(); i++) {
                System.out.printf("id: %s, %s\n", i, notes.get(i));
            }
        }
    }
}