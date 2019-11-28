package phoneapp.data;

import phoneapp.screen.PlayShopAppScreen;

import java.awt.*;

public class PlayShopApp {

    private String getName() {
        return "Play Store";
    }

    public Panel getAppButton(DataManager dataManager) {
        Panel panel = new Panel(new BorderLayout());
        panel.setBackground(Color.lightGray);
        Button button = new Button(getName() + " ( 1.0 )");
        button.addActionListener(e ->
                dataManager.setScreen(new PlayShopAppScreen(dataManager))
        );
        panel.add(button, BorderLayout.CENTER);
        return panel;
    }
}
