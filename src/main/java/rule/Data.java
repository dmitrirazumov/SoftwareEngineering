package rule;

public class Data {

    private int errorCode;
    private String dataMessage;

    public Data(int errorCode, String dataMessage) {
        this.errorCode = errorCode;
        this.dataMessage = dataMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDataMessage() {
        return dataMessage;
    }
}