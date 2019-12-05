package manager.data;

public class UpdateAppRequest {
    private Double version;
    private boolean state;

    public UpdateAppRequest(Double version, boolean state) {
        this.version = version;
        this.state = state;
    }
}
