package playshoplib;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class Database {
    private String databaseFile;

    public Database(String databaseFile) {
        this.databaseFile = databaseFile;
        try {
            File file = new File(databaseFile);
            if (!file.exists()) new FileOutputStream(file, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        String response = "{}";
        try {
            File file = new File(databaseFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = bufferedReader.readLine();
            if (readLine != null) response = readLine;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public void write(String input) {
        try {
            FileOutputStream file = new FileOutputStream(databaseFile);
            Writer writer = new BufferedWriter(new OutputStreamWriter(file, StandardCharsets.UTF_8));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
