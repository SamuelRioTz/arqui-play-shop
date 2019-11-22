package phone.screen;

import java.awt.Dimension;
import java.awt.Panel;
import java.util.Stack;

import javax.swing.JFrame;

import phone.app.PlayStoreApp;

public class Phone {
    private JFrame window = new JFrame();
    private Stack<ScreenContainer> history = new Stack<>();

    public Phone(PlayStoreApp playStoreApp) {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setLocation(500, 0);
        setScreen(new HomeScreen(playStoreApp, this));
        window.setVisible(true);
    }

    void setScreen(ScreenContainer view) {
        setCurrentView(view);
        history.push(view);
    }

    private void setCurrentView(ScreenContainer view) {
        window.setTitle(view.getTitle());
        window.setJMenuBar(view.getMenu());
        window.setContentPane(view.getBody(getDefaultPanel()));
        window.pack();
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

    private Panel getDefaultPanel() {
        Panel panel = new Panel();
        panel.setPreferredSize(new Dimension(480, 720));
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        return panel;
    }
}
