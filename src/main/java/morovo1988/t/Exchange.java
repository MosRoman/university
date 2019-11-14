package morovo1988.t;

import morovo1988.t.Currency;

import javax.persistence.*;

/**
 * Created by Дом on 29.06.2017.
 */
@Entity
@Table(name = "Exchanges")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "CurrencySell", nullable = false)
    private Currency sell;
    @Enumerated(EnumType.STRING)
    @Column(name = "CurrencyBuy", nullable = false)
    private Currency buy;
    @Column(name = "Price", nullable = false)
    private double priceSell;

    public Exchange() {
    }

    public Exchange(Currency sell, Currency buy, double priceSell) {
        this.sell = sell;
        this.buy = buy;
        this.priceSell = priceSell;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Currency getSell() {
        return sell;
    }

    public void setSell(Currency sell) {
        this.sell = sell;
    }

    public Currency getBuy() {
        return buy;
    }

    public void setBuy(Currency buy) {
        this.buy = buy;
    }

    public double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(double priceSell) {
        this.priceSell = priceSell;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "id=" + id +
                ", sell=" + sell +
                ", buy=" + buy +
                ", priceSell=" + priceSell +
                '}';
    }
}
