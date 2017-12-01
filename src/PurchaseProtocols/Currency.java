package PurchaseProtocols;

public class Currency {
    public String code;
    public String name;

    public Currency() {
    }

    public String getCode() {
        return (code != null) ? code : "";
    }

    public void setCode(String code) {
        this.code = code;
    }
}
