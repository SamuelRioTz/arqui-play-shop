package phoneapp.screen;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import phoneapp.data.PhoneApp;
import phoneapp.data.PlayShopDataManager;

public class HomeScreen implements ScreenContainer {
    private PlayShopDataManager playShopDataManager;

    HomeScreen(PlayShopDataManager playShopDataManager) {
        this.playShopDataManager = playShopDataManager;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));
        for (PhoneApp app : playShopDataManager.getApps().values()) {
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
        quitMenuItem.addActionListener(e -> playShopDataManager.back());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }

    private Panel getAppButton(PhoneApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Button button = new Button(phoneApp.getName() + " (" + phoneApp.getInstalledVersion() + ")");
        button.addActionListener(e -> {
            ScreenContainer screenContainer = phoneApp.getScreen();
            if (screenContainer != null) playShopDataManager.setScreen(screenContainer);
        });
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }
}