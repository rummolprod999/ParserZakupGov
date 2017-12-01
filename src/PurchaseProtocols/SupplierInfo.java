package PurchaseProtocols;

public class SupplierInfo {
    public SupplierInfo() {
    }

    public String getName() {
        return (name != null) ? name : "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;

    public String getInn() {
        return (inn != null) ? inn : "";
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String inn;

    public String getKpp() {
        return (kpp != null) ? kpp : "";
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return (address != null) ? address : "";
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOgrn() {
        return (ogrn != null) ? ogrn : "";
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String kpp;
    public String address;
    public String ogrn;
}
