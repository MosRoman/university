package morovo1988.t;

import javax.persistence.*;


@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Account")
    private Account account;
    @Enumerated(EnumType.STRING)
    @Column(name = "TransactionsType", nullable = false)
    private TransactionType type;


    @Column(name = "amount", nullable = false)
    private double amount;

    public Transaction() {
    }

    public Transaction(Account account, TransactionType type, double amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", account=" + account +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }
}
