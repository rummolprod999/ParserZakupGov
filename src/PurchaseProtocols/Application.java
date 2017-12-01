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
    public String rejectionReason;
    public String lastPrice;
    public String lastPriceInfo;
    public String applicationRate;
    public String applicationPlace;

    public Application() {
    }

    public String getLastPrice() {
        return (lastPrice != null) ? lastPrice : "";
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getLastPriceInfo() {
        return (lastPriceInfo != null) ? lastPriceInfo : "";
    }

    public void setLastPriceInfo(String lastPriceInfo) {
        this.lastPriceInfo = lastPriceInfo;
    }

    public String getApplicationRate() {
        return (applicationRate != null) ? applicationRate : "";
    }

    public void setApplicationRate(String applicationRate) {
        this.applicationRate = applicationRate;
    }

    public String getRejectionReason() {
        return (rejectionReason != null) ? rejectionReason : "";
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getAccepted() {
        return (accepted != null) ? accepted : "";
    }

    public void setAccepted(String accepted) {
        this.accepted = accepted;
    }

    public String getWinnerIndication() {
        return (winnerIndication != null) ? winnerIndication : (applicationPlace != null) ? applicationPlace : "";
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
