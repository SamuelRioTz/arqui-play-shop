package server.data;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Database {

    private Map<String, PhoneApp> apps = new HashMap<>();
    private Gson gson = new Gson();

    public Database() {
        apps.put("app uno", new PhoneApp("app uno", "1.0", "active"));
        apps.put("app dos", new PhoneApp("app dos", "2.0", "active"));
        apps.put("app tres", new PhoneApp("app tres", "3.0", "active"));
        apps.put("app cuatro", new PhoneApp("app cuatro", "4.0", "inactive"));
    }

    public void addApp(PhoneApp app) {
        apps.put(app.name, app);
    }

    public void deactivateApp(String name) {
        PhoneApp phoneApp = apps.get(name);
        if (phoneApp != null) {
            phoneApp.state = "inactivate";
        }
    }

    String getAllApps() {
        return gson.toJson(apps);
    }

    String getAllActiveApps() {
        Map<String, PhoneApp> response = new HashMap<>();
//        List<PhoneApp> response = new ArrayList<>();
        for (PhoneApp app : apps.values()) {
            if (app.state.equals("active")) response.put(app.name,app);
        }
        return gson.toJson(response);
    }
}
