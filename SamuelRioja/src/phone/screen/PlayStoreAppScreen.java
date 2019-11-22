package phone.screen;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import phone.app.PhoneApp;
import phone.app.PlayStoreApp;

public class PlayStoreAppScreen implements ScreenContainer {
    private PlayStoreApp playStoreApp;

    public PlayStoreAppScreen(PlayStoreApp playStoreApp) {
        this.playStoreApp = playStoreApp;
    }

    private Panel getAppPanel(PhoneApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        Label label = new Label(phoneApp.getName() + " (" + phoneApp.getVersion() + ")");
        panel.setBackground(Color.lightGray);
        Button button = new Button();
        button.setPreferredSize(new Dimension(100, 0));
        button.setLabel(phoneApp.isInstalled() ? "Desinstalar" : "Instalar");
        button.addActionListener(e -> {
            if (!phoneApp.isInstalled()) {
                playStoreApp.installApp(phoneApp);
                button.setLabel("Desinstalar");
            } else {
                playStoreApp.uninstallApp(phoneApp);
                button.setLabel("Instalar");
            }
            playStoreApp.refresh();
        });
        if (phoneApp.isInstalled() && phoneApp.isNewVersion()) {
            Button updateButton = new Button("Actualizar");
            updateButton.addActionListener(e -> {
                phoneApp.install();
                playStoreApp.refresh();
            });
            panel.add(updateButton, BorderLayout.WEST);
        }
        panel.add(label, BorderLayout.CENTER);
        panel.add(button, BorderLayout.EAST);
        return panel;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setLayout(new BorderLayout());
        panel.setLayout(new GridLayout(10, 1));
        for (PhoneApp app : playStoreApp.getApps().values()) {
            if (!(app instanceof PlayStoreApp))
                panel.add(getAppPanel(app));
        }
        return panel;
    }

    @Override
    public String getTitle() {
        return "Play Store App";
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> playStoreApp.close());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }
}
