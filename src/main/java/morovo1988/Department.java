package morovo1988;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Lector headOfDepartment;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "departments_lectors",
            joinColumns = {@JoinColumn(name = "department_id")},
            inverseJoinColumns = {@JoinColumn(name = "lector_id")}
    )
    private List<Lector> lectors = new ArrayList<>();

    public Department(String name, Lector headOfDepartment, List<Lector> lectors) {
        this.name = name;
        this.headOfDepartment = headOfDepartment;
        this.lectors = lectors;
    }

    public void addLector(Lector lector) {
        this.lectors.add(lector);
    }

}

