package manager.screen;

import manager.data.DataManager;
import playshoplib.PhoneApp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.*;


public class HomeScreen implements ScreenContainer {
    private DataManager dataManager;

    HomeScreen(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public Panel getBody(Panel panel) {
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(10, 1));

        Panel searchPanel = new Panel(new BorderLayout());
        TextField textField = new TextField("");
        Button searchButton = new Button("Buscar");
        searchButton.addActionListener(e ->
                dataManager.searchApp(textField.getText())
        );
        searchPanel.add(textField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.WEST);
        panel.add(searchPanel);

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
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Exit");
        quitMenuItem.addActionListener(e -> dataManager.back());
        fileMenu.add(quitMenuItem);
        JMenuItem refreshMenuItem = new JMenuItem("Refresh");
        refreshMenuItem.addActionListener(e -> dataManager.downloadApps());
        fileMenu.add(refreshMenuItem);
        JMenuItem addMenuItem = new JMenuItem("Add");
        addMenuItem.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Add new PhoneApp");
            if (name != null) dataManager.addApp(name);
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
                    dataManager.upgradeVersion(managerPhoneApp, Double.parseDouble(textField.getText()))
            );

            Button DeactivateButton = new Button("Deactivate");
            DeactivateButton.addActionListener(e ->
                    dataManager.deactivateApp(managerPhoneApp)
            );
            buttonsPanel.add(DeactivateButton, BorderLayout.NORTH);
            buttonsPanel.add(sendButton, BorderLayout.SOUTH);

            panel.add(buttonsPanel, BorderLayout.WEST);
        }
        return panel;
    }
}
