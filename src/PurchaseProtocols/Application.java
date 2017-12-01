package PurchaseProtocols;

public class Application {
    public String applicationDate;
    public Currency currency;
    public SupplierInfo supplierInfo;
    public String price;
    public String priceInfo;
    public String applicationNumber;
    public String accepted;
    public String winnerIndication;

    public Application() {
    }

    public String getAccepted() {
        return (accepted != null) ? accepted : "";
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getWinnerIndication() {
        return (winnerIndication != null) ? winnerIndication : "";
    }

    public void setWinnerIndication(String winnerIndication) {
        this.winnerIndication = winnerIndication;
    }

    public String getApplicationNumber() {

        return (applicationNumber != null) ? applicationNumber : "";
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getPrice() {
        return (price != null) ? price : "";
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceInfo() {
        return (priceInfo != null) ? priceInfo : "";
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

}
