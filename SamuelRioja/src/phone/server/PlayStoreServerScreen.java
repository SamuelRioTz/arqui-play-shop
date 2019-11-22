package phone.server;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JMenuBar;

import phone.server.model.ServerApp;

public class PlayStoreServerScreen implements ScreenContainer {

    private Server server = new Server();


    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));
        for (ServerApp app : server.getApps()) {
            panel.add(getAppButton(app));
        }
        return panel;
    }

    @Override
    public String getTitle() {
        return "PlayStoreServerScreen";
    }

    @Override
    public JMenuBar getMenu() {
        return null;
    }

    private Panel getAppButton(ServerApp serverApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Label appName = new Label(serverApp.getName());
        panel.add(appName, BorderLayout.CENTER);
        Panel versionPanel = new Panel(new BorderLayout());
        TextField textField = new TextField(serverApp.getVersion());
        Button sendButton = new Button("Update version");
        sendButton.addActionListener(e -> {
            serverApp.setVersion(textField.getText());
            server.updateVersion(serverApp);
        });
        versionPanel.add(new Label("Version:"), BorderLayout.WEST);
        versionPanel.add(sendButton, BorderLayout.EAST);
        versionPanel.add(textField, BorderLayout.CENTER);
        panel.add(versionPanel, BorderLayout.SOUTH);
        return panel;
    }
}
