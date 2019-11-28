package phoneapp.screen;

import phoneapp.data.PhoneApp;
import phoneapp.data.PlayShopDataManager;

import javax.swing.*;
import java.awt.*;


public class PlayStoreAppScreen implements ScreenContainer {
    private PlayShopDataManager playShopDataManager;

    public PlayStoreAppScreen(PlayShopDataManager playShopDataManager) {
        this.playShopDataManager = playShopDataManager;
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
                playShopDataManager.installApp(phoneApp);
                button.setLabel("Desinstalar");
            } else {
                playShopDataManager.uninstallApp(phoneApp);
                button.setLabel("Instalar");
            }
            playShopDataManager.refresh();
        });
        if (phoneApp.isInstalled() && phoneApp.isNewVersion()) {
            Button updateButton = new Button("Actualizar");
            updateButton.addActionListener(e -> {
                phoneApp.install();
                playShopDataManager.refresh();
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
        for (PhoneApp app : playShopDataManager.getApps().values()) {
            if (!(app instanceof PlayShopDataManager))
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
        quitMenuItem.addActionListener(e -> playShopDataManager.close());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }
}
