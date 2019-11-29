package manager.screen;


import manager.data.DataManager;

import java.awt.Dimension;
import java.awt.Panel;
import java.util.Stack;

import javax.swing.JFrame;


public class ScreenManager {

    private JFrame window = new JFrame();

    private Stack<ScreenContainer> history = new Stack<>();

    public ScreenManager(DataManager dataManager) {
        window.setResizable(false);
        setScreen(new HomeScreen(dataManager));
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
