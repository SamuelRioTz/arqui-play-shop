package phone.server;

import javax.swing.*;
import java.awt.*;

public interface ScreenContainer {

    Panel getBody(Panel panel);

    String getTitle();

    JMenuBar getMenu();

}
