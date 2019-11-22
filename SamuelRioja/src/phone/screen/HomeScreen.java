package phone.screen;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import phone.app.PhoneApp;
import phone.app.PlayStoreApp;

class HomeScreen implements ScreenContainer {
    private Phone phone;
    private PlayStoreApp playStoreApp;

    HomeScreen(PlayStoreApp playStoreApp, Phone phone) {
        this.phone = phone;
        this.playStoreApp = playStoreApp;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));
        for (PhoneApp app : playStoreApp.getApps().values()) {
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
        quitMenuItem.addActionListener(e -> phone.back());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }

    private Panel getAppButton(PhoneApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Button button = new Button(phoneApp.getName() + " (" + phoneApp.getInstalledVersion() + ")");
        button.addActionListener(e -> phone.setScreen(phoneApp.getScreen(phone)));
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }
}