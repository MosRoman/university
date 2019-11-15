package morovo1988;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static morovo1988.Degree.*;

public class Controler {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
    private static EntityManager em = emf.createEntityManager();
    private static Scanner sc = new Scanner(System.in);


    public Controler() {

        em.getTransaction().begin();
        try {
            //create list lectors
            for (Lector l : listLectors()) {
                em.persist(l);
            }
            //create list departments

            for (Department d : listDepartments()) {
                em.persist(d);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public List<Lector> listLectors() {

        List<Lector> lectors = new ArrayList<>(Arrays.asList(
                new Lector("Bob", "Marli", Professor, 15000),
                new Lector("Dvayn", "Jones", Professor, 15000),
                new Lector("John", "Travolta", Associate_professor, 10000),
                new Lector("Ivan", "Petrov", Assistant, 5000),
                new Lector("John", "Bush", Associate_professor, 10000),

                new Lector("Bill", "Geyts", Professor, 1000),
                new Lector("Ivan", "Petrosyan", Assistant, 100),
                new Lector("John", "MacLey", Associate_professor, 400),
                new Lector("Mat", "LeBlanc", Assistant, 100),

                new Lector("Ross", "Geller", Professor, 15000),
                new Lector("Albert", "Nobel", Associate_professor, 10000),
                new Lector("Kelly", "Clarson", Assistant, 5000),
                new Lector("Marshal", "Lawson", Associate_professor, 10000)));
        return lectors;
    }

    public List<Department> listDepartments() {
        Query query = em.createQuery("SELECT c FROM Lector c", Lector.class);
        List<Lector> list = (List<Lector>) query.getResultList();

        return new ArrayList<>(Arrays.asList(
                new Department("History", list.get(0), list.subList(0, 5)),
                new Department("Math", list.get(5), list.subList(5, 9)),
                new Department("Physics", list.get(9), list.subList(9, 13))
        ));
    }


    public void close() {
        em.close();
        emf.close();
        sc.close();

    }

    public void headOfDepartment() {
        enterDepartmentName();
        String name = sc.nextLine();
        if (departmentExist(name)) {
            Query q = em.createQuery("From Department c where c.name =:name", Department.class);
            q.setParameter("name", name);
            Department d = (Department) q.getSingleResult();
            System.out.println("Head of department " + name + " - " + d.getHeadOfDepartment().getName() + " " + d.getHeadOfDepartment().getSurname());
        } else {
            System.out.println("Department not exist or no correct name");
        }

    }

    public void showStatisticLectors() {

        System.out.println("Statistic");
        Query q = em.createQuery("select count (*) From Lector c where c.degree =:degree", Long.class);
        q.setParameter("degree", Assistant);
        List assistant = q.getResultList();
        System.out.println("Assistant = " + assistant.get(0));


        q = em.createQuery("select count (*) From Lector c where c.degree =:degree", Long.class);
        q.setParameter("degree", Associate_professor);
        List associateProfessor = q.getResultList();
        System.out.println("Associate_professor = " + associateProfessor.get(0));


        q = em.createQuery("select count (*) From Lector c where c.degree =:degree", Long.class);
        q.setParameter("degree", Professor);
        List professor = q.getResultList();
        System.out.println("Professor = " + professor.get(0));
    }

    public void showSaverageSalary() {
        enterDepartmentName();
        String name = sc.nextLine();

        if (departmentExist(name)) {
            Query q = em.createQuery("SELECT avg(l.salary) FROM Lector l JOIN l.departments c WHERE c.name = :name");
            q.setParameter("name", name);
            List list = q.getResultList();
            System.out.println("Average salary department " + name + " = " + list.get(0));
        } else {
            System.out.println("Department not exist or no correct name");
        }
    }

    public void showCountEmployee() {
        enterDepartmentName();
        String name = sc.nextLine();
        if (departmentExist(name)) {
            Query q = em.createQuery("select count (*) From Lector l JOIN l.departments d Where d.name =:name", Long.class);
            q.setParameter("name", name);
            List list = q.getResultList();
            System.out.println("Count Employee " + name + " = " + list.get(0));
        } else {
            System.out.println("Department not exist or no correct name");
        }
    }

    private void enterDepartmentName() {
        Query query = em.createQuery("from Department ");
        List<Department> departments = query.getResultList();
        System.out.print("Enter department name : ");
        for (Department d : departments) {
            System.out.print(d.getName() + ",");
        }
        System.out.println();
    }

    private Boolean departmentExist(String name) {
        Query q = em.createQuery("from Department d where d.name =:name");
        q.setParameter("name", name);
        List<Lector> lectors = q.getResultList();
        if (lectors.size() > 0) {
            return true;
        }
        return false;
    }

    public void globalSearch() {
        System.out.println("Global search by  ...");
        String name = sc.nextLine();

        Query q = em.createQuery("select l From Lector l Where l.name like :name or l.surname like :name ", Lector.class);
        q.setParameter("name", "%" + name + "%");
        if (q.getResultList().size() > 0) {
            List<Lector> list = q.getResultList();
            for (Lector l : list) {
                System.out.println(l.getName() + " " + l.getSurname());
            }
        } else {
            System.out.println("No result");
        }
    }
}