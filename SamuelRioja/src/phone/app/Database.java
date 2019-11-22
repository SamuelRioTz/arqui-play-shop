package phone.app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Database {
    private List<PhoneApp> apps = new ArrayList<>();
    private Map<String, String> loadApps = new HashMap<>();

    Database() {
        try {
            File file = new File("./database.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] temp = st.split("=");
                if (temp.length == 2) {
                    loadApps.put(temp[0], temp[1]);
                }
                System.out.println(st);
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
    }

    String getVersion(String appName) {
        return loadApps.get(appName);
    }

    void installApp(PhoneApp app) {
        apps.add(app);
    }

    void unInstallApp(PhoneApp app) {
        apps.remove(app);
    }

    void saveApps() {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("database.txt"), StandardCharsets.UTF_8));
            for (PhoneApp app : apps) {
                writer.write(app.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
