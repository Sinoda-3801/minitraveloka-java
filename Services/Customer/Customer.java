package Services.Customer;

public class Customer {
    private String name;
    private String identityNumber;
    private String contact;

    public Customer(String name, String identityNumber, String contact) {
        this.name = name;
        this.identityNumber = identityNumber;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return """
            Nama Pelanggan   : %s
            Nomor Identitas  : %s
            Kontak           : %s
            """.formatted(name, identityNumber, contact);
    }
}
