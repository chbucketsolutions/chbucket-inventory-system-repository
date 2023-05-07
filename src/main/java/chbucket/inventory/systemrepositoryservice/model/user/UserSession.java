package chbucket.inventory.systemrepositoryservice.model.user;

import chbucket.inventory.systemrepositoryservice.model.entity.StandardEntity;

import javax.persistence.*;

@Entity
@Table(name = "user_session")
@AttributeOverride(name = "id", column = @Column(name = "id", nullable = false))
public class UserSession extends StandardEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Column(name = "token")
    private String token;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
