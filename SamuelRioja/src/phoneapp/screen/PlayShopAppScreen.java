package phoneapp.screen;

import phoneapp.PlayShopPhoneApp;
import phoneapp.data.SimpleApp;

import javax.swing.*;
import java.awt.*;


public class PlayShopAppScreen implements ScreenContainer {
    private PlayShopPhoneApp playShopPhoneApp;

    public PlayShopAppScreen(PlayShopPhoneApp playShopPhoneApp) {
        this.playShopPhoneApp = playShopPhoneApp;
    }

    private Panel getAppPanel(SimpleApp phoneApp) {
        Panel panel = new Panel(new BorderLayout());
        Label label = new Label(phoneApp.getName() + " (" + phoneApp.getVersion() + ")");
        panel.setBackground(Color.lightGray);
        Button button = new Button();
        button.setPreferredSize(new Dimension(100, 0));
        button.setLabel(phoneApp.isInstalled() ? "Desinstalar" : "Instalar");
        button.addActionListener(e -> {
            if (!phoneApp.isInstalled()) {
                playShopPhoneApp.installApp(phoneApp);
                button.setLabel("Desinstalar");
            } else {
                playShopPhoneApp.unInstallApp(phoneApp);
                button.setLabel("Instalar");
            }
            playShopPhoneApp.refresh();
        });
        if (phoneApp.isInstalled() && phoneApp.isUpgradeable()) {
            Button updateButton = new Button("Actualizar");
            updateButton.addActionListener(e ->
                    playShopPhoneApp.installApp(phoneApp)
            );
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
        for (SimpleApp app : playShopPhoneApp.getApps().values()) {
//            if (!(app instanceof PlayShopApp))
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
        quitMenuItem.addActionListener(e -> playShopPhoneApp.close());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }
}
