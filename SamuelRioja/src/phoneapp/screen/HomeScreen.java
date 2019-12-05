package phoneapp.screen;

import phoneapp.PlayShopPhoneApp;
import phoneapp.data.SimpleApp;

import javax.swing.*;
import java.awt.*;

public class HomeScreen implements ScreenContainer {
    private PlayShopPhoneApp playShopPhoneApp;

    HomeScreen(PlayShopPhoneApp playShopPhoneApp) {
        this.playShopPhoneApp = playShopPhoneApp;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));
        panel.add(playShopPhoneApp.getPlayShopApp().getAppButton(playShopPhoneApp));
        for (SimpleApp app : playShopPhoneApp.getApps().values()) {
            if (app.isInstalled())
                panel.add(getAppButton(app));
        }
        return panel;
    }

    @Override
    public String getTitle() {
        return "My Phone";
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> playShopPhoneApp.back());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }

    private Panel getAppButton(SimpleApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Button button = new Button(phoneApp.getName() + " (" + phoneApp.getInstalledVersion() + ")");
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }
}