package Services.Customer;

public class Customer {
    private String name;
    private String identityNumber;

    public Customer(String name, String identityNumber) {
        this.name = name;
        this.identityNumber = identityNumber;
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

    @Override
    public String toString() {
        return String.format(
            """
            Nama Pelanggan   : %s
            Nomor Identitas  : %s
            """,
            name,
            identityNumber
        );
    }
}