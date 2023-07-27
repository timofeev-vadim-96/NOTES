package noteBook.dao.impl;

import noteBook.model.Note;

import java.util.ArrayList;
import java.util.List;

public class TempDataBase {
    private final List<Note> notes;

    public TempDataBase() {
        this.notes = new ArrayList<>();
    }

    public void add(Note note) {
        notes.add(note);
    }

    public void remove(int id) {
        notes.remove(id);
    }

    public void read() {
        for (int i = 0; i < notes.size(); i++) {
            System.out.printf("id: %s, %s\n", i, notes.get(i));
        }
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void fillTheList(List<Note> notesFromJson) {
        notes.addAll(notesFromJson);
    }
}
