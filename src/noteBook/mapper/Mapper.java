package noteBook.mapper;

import com.google.gson.Gson;
import noteBook.model.Note;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    Gson gson;

    public Mapper() {
        this.gson = new Gson();
    }

    public String objToJson(Note note) {
        return gson.toJson(note);
    }

    public List<String> notesListToJson(List<Note> notes) {
        List<String> jsonList = new ArrayList<>();
        for (Note note : notes) {
            jsonList.add(objToJson(note));
        }
        return jsonList;
    }

    public Note jsonToObj(String json) {
        return gson.fromJson(json, Note.class);
    }

    public List<Note> jsonListToNotes(List<String> strings) {
        List<Note> notes = new ArrayList<>();
        for (String json : strings) {
            notes.add(jsonToObj(json));
        }
        return notes;
    }
}
