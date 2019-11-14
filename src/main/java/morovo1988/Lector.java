package morovo1988;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Lectors")
@NoArgsConstructor
@Data

public class Lector {

    @Id
    @GeneratedValue()
    @Column(name="id")
    private long id;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Enum degree;

    private double salary;

    @ManyToMany(mappedBy = "lectors")
    private List<Department> departments = new ArrayList<>();

    public Lector( String name, String surname, Enum degree, double salary) {
          this.name = name;
        this.surname = surname;
        this.degree = degree;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Lector " +
                "Name='" + name + '\'' +
                ", Surname='" + surname + '\'' +
                ", Degree=" + degree;
    }
}
