package manager.screen;

import manager.data.DataManager;
import manager.data.PhoneApp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JMenuBar;


public class HomeScreen implements ScreenContainer {
    private DataManager dataManager;

    HomeScreen(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));
        for (PhoneApp app : dataManager.getApps()) {
            panel.add(getAppButton(app));
        }
        return panel;
    }

    @Override
    public String getTitle() {
        return "Play Shop Manager";
    }

    @Override
    public JMenuBar getMenu() {
        return null;
    }

    private Panel getAppButton(PhoneApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Label appName = new Label(phoneApp.getName());
        panel.add(appName, BorderLayout.CENTER);
        Panel versionPanel = new Panel(new BorderLayout());
        TextField textField = new TextField(phoneApp.getVersion());
        Button sendButton = new Button("Update version");
        sendButton.addActionListener(e -> {
            phoneApp.setVersion(textField.getText());
            dataManager.updateVersion(phoneApp);
        });
        versionPanel.add(new Label("Version:"), BorderLayout.WEST);
        versionPanel.add(sendButton, BorderLayout.EAST);
        versionPanel.add(textField, BorderLayout.CENTER);
        panel.add(versionPanel, BorderLayout.SOUTH);
        return panel;
    }
}
