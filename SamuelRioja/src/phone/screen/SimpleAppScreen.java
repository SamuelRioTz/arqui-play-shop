package phone.screen;

import java.awt.Panel;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import phone.app.SimpleApp;

public class SimpleAppScreen implements ScreenContainer {
    private SimpleApp simpleApp;

    public SimpleAppScreen(SimpleApp simpleApp) {
        this.simpleApp = simpleApp;
    }

    @Override
    public Panel getBody(Panel panel) {
        return panel;
    }

    @Override
    public String getTitle() {
        return simpleApp.getName();
    }

    @Override
    public JMenuBar getMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem quitMenuItem = new JMenuItem("Quit");
        quitMenuItem.addActionListener(e -> simpleApp.close());
        fileMenu.add(quitMenuItem);
        jMenuBar.add(fileMenu);
        return jMenuBar;
    }
}
