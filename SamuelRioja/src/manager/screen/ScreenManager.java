package manager.screen;


import manager.PlayShopManager;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;


public class ScreenManager {

    private JFrame window = new JFrame();

    private Stack<ScreenContainer> history = new Stack<>();

    public ScreenManager(PlayShopManager playShopManager) {
        window.setResizable(false);
        setScreen(new HomeScreen(playShopManager));
        window.setVisible(true);
    }

    private void setScreen(ScreenContainer view) {
        setCurrentView(view);
        history.push(view);
    }

    private void setCurrentView(ScreenContainer view) {
        window.setTitle(view.getTitle());
        window.setJMenuBar(view.getMenu());
        window.setContentPane(view.getBody(getDefaultPanel()));
        window.pack();
    }

    private Panel getDefaultPanel() {
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(480, 720));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        return panel;
    }

    public void refresh() {
        setCurrentView(history.peek());
    }

    public void back() {
        history.pop();
        if (!history.isEmpty())
            setCurrentView(history.peek());
        else
            System.exit(0);
    }
}
