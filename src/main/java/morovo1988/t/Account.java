package morovo1988.t;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дом on 29.06.2017.
 */
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Clients")
    private Client client;
    @Enumerated(EnumType.STRING)
    @Column(name = "Currency", nullable = false)
    private Currency cur;
    @Column(name = "quantity", nullable = false)
    private double quantity;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> tran = new ArrayList();


    public Account() {

    }

    public Account(Client client, Currency cur, double quantity) {
        this.client = client;
        this.cur = cur;
        this.quantity = quantity;
    }

    public void addTransaction(Transaction t) {
        t.setAccount(this);
        tran.add(t);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Currency getCur() {
        return cur;
    }

    public void setCur(Currency cur) {
        this.cur = cur;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", client=" + client +
                ", cur=" + cur +
                ", quantity=" + quantity +
                ", tran=" + tran +
                '}';
    }
}
