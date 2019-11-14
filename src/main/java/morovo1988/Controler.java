package morovo1988;

import javax.persistence.*;
import java.util.*;

import static morovo1988.Degree.Assistant;
import static morovo1988.Degree.Associate_professor;
import static morovo1988.Degree.Professor;

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
                new Lector("Bob", "Petrov", Professor, 15000),
                new Lector("John", "Petrov", Associate_professor, 10000),
                new Lector("Bob", "Petrov", Professor, 15000),
                new Lector("Ivan", "Petrov", Assistant, 5000),
                new Lector("John", "Petrov", Associate_professor, 10000),
                new Lector("Ivan", "Petrov", Assistant, 5000),
                new Lector("John", "Petrov", Associate_professor, 10000),
                new Lector("Mat", "Petrov", Assistant, 5000),
                new Lector("Albert", "Petrov", Associate_professor, 10000),
                new Lector("Bill", "Petrov", Professor, 15000),
                new Lector("Kelly", "Petrov", Assistant, 5000),
                new Lector("Marshal", "Petrov", Associate_professor, 10000),
                new Lector("Ross", "Petrov", Professor, 15000)));
        return lectors;
    }

    public List<Department> listDepartments() {
        Query query = em.createQuery("SELECT c FROM Lector c", Lector.class);
        List<Lector> list = (List<Lector>) query.getResultList();

        return new ArrayList<>(Arrays.asList(
                new Department("History", list.get(0), list.subList(0, 3)),
                new Department("Math", list.get(5), list.subList(3, 6)),
                new Department("Physics", list.get(8), list.subList(6, 9))
        ));
    }


    public void close() {
        em.close();
        emf.close();
        sc.close();

    }

    public void headOfDepartment() {
        Query query = em.createQuery("from Department ");
        List<Department> departments = query.getResultList();
        System.out.print("Enter department name : ");
        for (Department d : departments) {
            System.out.print(d.getName() + ",");
        }
        String name = sc.nextLine();
        Department d = null;
        try {
            Query q = em.createQuery("From Department c where c.name =:name", Department.class);
            q.setParameter("name", name);
            d = (Department) q.getSingleResult();
            System.out.println("Head of department " + name + " - " + d.getHeadOfDepartment().getName() + " " + d.getHeadOfDepartment().getSurname());
        } catch (NoResultException e) {
            System.out.println("Client not found");

        } catch (NonUniqueResultException e) {
            System.out.println("Non unique result");
        }

    }

    public void showStatisticLectors() {

        try {
            System.out.println("Statistic");
            Query q = em.createQuery("From Lector c where c.degree =:degree", Lector.class);
            q.setParameter("degree", Assistant);
            List<Department> assistant = q.getResultList();
            System.out.println("Assistant = " + assistant.size());


            q = em.createQuery("From Lector c where c.degree =:degree", Lector.class);
            q.setParameter("degree", Associate_professor );
            List<Department> associateProfessor = q.getResultList();
            System.out.println("Associate_professor = " + associateProfessor.size());


            q = em.createQuery("From Lector c where c.degree =:degree", Lector.class);
            q.setParameter("degree", Professor);
            List<Department> professor = q.getResultList();
            System.out.println("Professor = " + professor.size());


        } catch (NoResultException e) {
            System.out.println("no result");
        }
    }
}