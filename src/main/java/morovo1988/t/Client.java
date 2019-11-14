package morovo1988.t;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Дом on 29.06.2017.
 */
@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)

    private List<Account> accon = new ArrayList();

    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Account> getAccon() {
        return Collections.unmodifiableList(accon);
    }

    public void setAccon(List<Account> accon) {
        this.accon = accon;
    }

    public void addAccount(Account a) {
        a.setClient(this);
        accon.add(a);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accon=" + accon +
                '}';
    }
}
