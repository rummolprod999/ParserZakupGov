package PurchaseProtocols;

public class Lot {
    public String ordinalNumber;

    public Lot() {
    }

    public String getOrdinalNumber() {
        return (ordinalNumber != null) ? ordinalNumber : "";
    }

    public void setOrdinalNumber(String ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }
}
