package phone.server;

import java.awt.Dimension;
import java.awt.Panel;

import javax.swing.JFrame;


public class PlayStoreServer {

    private JFrame window = new JFrame();

    public PlayStoreServer() {
        window.setResizable(false);
        setScreen(new PlayStoreServerScreen());
        window.setVisible(true);
    }

    private void setScreen(ScreenContainer view) {
        setCurrentView(view);
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
}
