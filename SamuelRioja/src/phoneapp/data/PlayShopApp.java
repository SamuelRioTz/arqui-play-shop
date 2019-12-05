package phoneapp.data;

import phoneapp.PlayShopPhoneApp;
import phoneapp.screen.PlayShopAppScreen;

import java.awt.*;

public class PlayShopApp {

    private String getName() {
        return "Play Store";
    }

    public Panel getAppButton(PlayShopPhoneApp playShopPhoneApp) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Button button = new Button(getName());
        button.addActionListener(e ->
                playShopPhoneApp.setScreen(new PlayShopAppScreen(playShopPhoneApp))
        );
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }
}
