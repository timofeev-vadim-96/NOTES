package noteBook.dao.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonDB {
    private final String path = "JsonDataBase.json";

    public JsonDB() {
        createDB();
    }

    public void save(List<String> notes) {
        try {
            Writer writer = new FileWriter(path);
            for (String note : notes) {
                writer.write(note + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> read() {
        List<String> notes = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return notes;
    }

    private void createDB() {
        try {
            File jsonDB = new File(path);
            if (!jsonDB.exists()) {
                jsonDB.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
