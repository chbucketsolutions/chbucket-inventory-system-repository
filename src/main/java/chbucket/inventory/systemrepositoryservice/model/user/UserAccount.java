package chbucket.inventory.systemrepositoryservice.model.user;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;
import chbucket.inventory.systemrepositoryservice.model.product.Product;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_account")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class UserAccount extends StandardEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private List<Product> product;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
    private List<UserSession> userSession;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
