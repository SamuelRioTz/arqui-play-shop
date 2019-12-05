package server.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import playshoplib.FileUtil;
import playshoplib.PhoneApp;

import java.util.HashMap;
import java.util.Map;


public class DataManager {
    private FileUtil fileUtil = new FileUtil("./database.server.txt");
    private Map<String, PhoneApp> apps;
    private Gson gson = new Gson();

    public DataManager() {
        apps = gson.fromJson(fileUtil.read(), new TypeToken<Map<String, PhoneApp>>() {
        }.getType());
    }

    private void saveCurrentState() {
        fileUtil.write(gson.toJson(apps));
    }


    String getAllApps() {
        return gson.toJson(apps.values().toArray());
    }

    String getApp(String appName) {
        return gson.toJson(apps.get(appName));
    }


    boolean addApp(String input) {
        boolean response = false;
        if (apps.get(input) == null) {
            PhoneApp newVersionApp = new PhoneApp(input, 1.0, true);
            apps.put(newVersionApp.getName(), newVersionApp);
            saveCurrentState();
            response = true;
        }
        return response;
    }

    boolean updateApp(String input) {
        boolean response = false;
        String[] params = input.split("/");
        UpdateApp updateApp = gson.fromJson(params[1], UpdateApp.class);
        PhoneApp appToUpdate = apps.get(params[0]);
        if (updateApp != null) {
            PhoneApp phoneApp = updateApp.updateApp(appToUpdate);
            if (phoneApp != null) {
                apps.replace(phoneApp.getName(), phoneApp);
                saveCurrentState();
                response = true;
            }
        }
        return response;
    }

    String searchApp(String input) {
        Map<String, PhoneApp> response = new HashMap<>();
        for (PhoneApp app : apps.values()) {
            if (app.getName().toLowerCase().contains(input.toLowerCase()))
                response.put(app.getName(), app);
        }
        return gson.toJson(response);
    }
}
