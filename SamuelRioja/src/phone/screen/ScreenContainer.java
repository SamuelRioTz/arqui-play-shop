package phone.screen;

import java.awt.Panel;

import javax.swing.JMenuBar;

public interface ScreenContainer {

    Panel getBody(Panel panel);

    String getTitle();

    JMenuBar getMenu();

}
