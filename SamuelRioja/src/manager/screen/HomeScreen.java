package manager.screen;

import manager.PlayShopManager;
import playshoplib.PhoneApp;

import javax.swing.*;
import java.awt.*;


public class HomeScreen implements ScreenContainer {
    private PlayShopManager playShopManager;

    HomeScreen(PlayShopManager playShopManager) {
        this.playShopManager = playShopManager;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));

        Panel searchPanel = new Panel(new BorderLayout());
        TextField textField = new TextField("");
        Button searchButton = new Button("Buscar");
        searchButton.addActionListener(e ->
                playShopManager.searchApp(textField.getText())
        );
        searchPanel.add(textField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.WEST);
        panel.add(searchPanel);

        for (PhoneApp app : playShopManager.getApps()) {
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
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Exit");
        quitMenuItem.addActionListener(e -> playShopManager.back());
        fileMenu.add(quitMenuItem);
        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(e -> playShopManager.downloadApps());
        fileMenu.add(refreshMenuItem);
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Add new PhoneApp");
            if (name != null) playShopManager.addApp(name);
        });
        fileMenu.add(addMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }

    private Panel getAppButton(PhoneApp managerPhoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);

        Panel bodyPanel = new Panel(new BorderLayout());
        Panel descriptionPanel = new Panel(new BorderLayout());
        Label appName = new Label(managerPhoneApp.getName());
        Label appState = new Label(managerPhoneApp.getState() ? "active" : "inactive");
        descriptionPanel.add(appName, BorderLayout.CENTER);
        descriptionPanel.add(appState, BorderLayout.EAST);

        Panel versionPanel = new Panel(new BorderLayout());
        TextField textField = new TextField(managerPhoneApp.getVersion().toString());
        versionPanel.add(new Label("Version:"), BorderLayout.WEST);
        versionPanel.add(textField, BorderLayout.CENTER);
        bodyPanel.add(versionPanel, BorderLayout.SOUTH);
        bodyPanel.add(descriptionPanel, BorderLayout.NORTH);

        panel.add(bodyPanel, BorderLayout.CENTER);
        if (managerPhoneApp.getState()) {
            Panel buttonsPanel = new Panel(new BorderLayout());
            Button sendButton = new Button("Upgrade version");
            sendButton.addActionListener(e ->
                    playShopManager.upgradeVersion(managerPhoneApp, Double.parseDouble(textField.getText()))
            );

            Button DeactivateButton = new Button("Deactivate");
            DeactivateButton.addActionListener(e ->
                    playShopManager.deactivateApp(managerPhoneApp)
            );
            buttonsPanel.add(DeactivateButton, BorderLayout.NORTH);
            buttonsPanel.add(sendButton, BorderLayout.SOUTH);

            panel.add(buttonsPanel, BorderLayout.WEST);
        }
        return panel;
    }
}
