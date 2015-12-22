package calculator;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by VeraL on 28.11.2015.
 *
 */

@Entity
@Table(name = "Client")
public class Customer {


    private long id;
    private String customerName;
    private String adress;
    private String phone;

    private Wardrobe wardrobe;

    public Customer() {}

    public Customer(String customerName, String adress, String phone) {
        this.customerName = customerName;
        this.adress = adress;
        this.phone = phone;
    }

    public Customer(String customerName, String adress, String phone, Wardrobe wardrobe) {
        this.customerName = customerName;
        this.adress = adress;
        this.phone = phone;
        this.setWardrobe(wardrobe);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(customerName).append(' ')
                .append(adress).append(' ')
                .append(phone).append(' ')
                .append(wardrobe.getDate()).append(' ')
                .append(wardrobe.getHeight()).append(' ')
                .append(wardrobe.getLength()).append(' ')
                .append(wardrobe.getWidth()).append(' ')
                .append(wardrobe.getDoors()).append(' ')
                .append(wardrobe.getFlakeboard()).append(' ')
                .append(wardrobe.getFacade()).append(' ')
                .append(wardrobe.getCurrency()).append(' ')
                .append(wardrobe.getMargin()).append(' ')
                .append(wardrobe.getPrice()).append(' ')
                .toString();
    }
    @Id
    @GeneratedValue
    @Column(name = "Id")
    public long getId() {
        return id;
    }
    @Column(name = "Name", nullable = false)
    public String getCustomerName() {
        return customerName;
    }

    @Column(name = "Phone", nullable = false)
    public String getPhone() {
        return phone;
    }
    @Column(name = "Adress", nullable = true)
    public String getAdress() {
        return adress;
    }


    @OneToOne(cascade = CascadeType.ALL )

    public Wardrobe getWardrobe() {
        return wardrobe;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setWardrobe(Wardrobe wardrobe) {
        this.wardrobe = wardrobe;
    }
}
