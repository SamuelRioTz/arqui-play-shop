package phone.app;

import phone.screen.Phone;
import phone.screen.ScreenContainer;
import phone.screen.SimpleAppScreen;

public class SimpleApp implements PhoneApp {
    private String name;
    private String version;
    private String installed = null;
    private Phone phone;

    SimpleApp(String name, String version) {
        this.name = name;
        this.version = version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getInstalledVersion() {
        return installed;
    }

    @Override
    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean isInstalled() {
        return installed != null;
    }

    @Override
    public boolean isNewVersion() {
        return !installed.equals(version);
    }

    @Override
    public void install() {
        installed = version;
    }

    @Override
    public void uninstall() {
        installed = null;
    }

    @Override
    public void close() {
        phone.back();
    }

    @Override
    public ScreenContainer getScreen(Phone phone) {
        this.phone = phone;
        return new SimpleAppScreen(this);
    }

}
