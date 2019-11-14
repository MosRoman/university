//package morovo1988.t;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Scanner;
//
//public class Controler {
//    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA");
//    private static EntityManager em = emf.createEntityManager();
//    private static Scanner sc = new Scanner(System.in);
//
//
//    public Controler() {
//        em.getTransaction().begin();
//        try {
//            em.persist(new Exchange(Currency.USD, Currency.EUR, 0.87));
//            em.persist(new Exchange(Currency.USD, Currency.UAH, 25.99));
//            em.persist(new Exchange(Currency.EUR, Currency.USD, 1.13));
//            em.persist(new Exchange(Currency.EUR, Currency.UAH, 29.55));
//            em.persist(new Exchange(Currency.UAH, Currency.EUR, 0.033));
//            em.persist(new Exchange(Currency.UAH, Currency.USD, 0.038));
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        }
//    }
//
//
//    public void addClient() {
//
//        System.out.println("Enter Client name : ");
//        String name = sc.nextLine();
//        Client d = new Client(name);
//
//        em.getTransaction().begin();
//        try {
//            em.persist(d);
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        }
//        createAccounts(d);
//    }
//
//    public Currency currencyType() {
//
//        System.out.println("Currency type :USD,EUR,UAH ");
//        Currency cur = null;
//        String s = sc.nextLine();
//        switch (s.toUpperCase()) {
//            case "USD":
//                cur = Currency.USD;
//                break;
//            case "EUR":
//                cur = Currency.EUR;
//                break;
//            case "UAH":
//                cur = Currency.UAH;
//                break;
//            default:
//                System.out.println("No such account");
//        }
//        return cur;
//    }
//
//    public void createAccounts(Client d) {
//        Account a = new Account(d, Currency.USD, 0);
//        Account b = new Account(d, Currency.EUR, 0);
//        Account c = new Account(d, Currency.UAH, 0);
//        a.addTransaction(new Transaction(a, TransactionType.Create, 0));
//        b.addTransaction(new Transaction(b, TransactionType.Create, 0));
//        c.addTransaction(new Transaction(c, TransactionType.Create, 0));
//        d.addAccount(a);
//        d.addAccount(b);
//        d.addAccount(c);
//        em.getTransaction().begin();
//        try {
//            em.persist(d);
//
//
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            em.getTransaction().rollback();
//        }
//    }
//
//    public void amountMoneyInUAH() {
//        Client c = findClient();
//        double amount = 0;
//        for (Account a : c.getAccon()) {
//            if (a.getCur().equals(Currency.UAH)) {
//                amount += a.getQuantity();
//            }
//            if (a.getCur().equals(Currency.EUR)) {
//                Query query = em.createQuery("Select d From Exchange d Where d.sell =:EUR AND d.buy= :UAH", Exchange.class);
//                query.setParameter("EUR", Currency.EUR);
//                query.setParameter("UAH", Currency.UAH);
//                Exchange e = (Exchange) query.getSingleResult();
//                amount += a.getQuantity() * e.getPriceSell();
//
//            }
//            if (a.getCur().equals(Currency.USD)) {
//                Query query = em.createQuery("Select d From Exchange d Where d.sell =:USD AND d.buy= :UAH", Exchange.class);
//                query.setParameter("USD", Currency.USD);
//                query.setParameter("UAH", Currency.UAH);
//                Exchange e = (Exchange) query.getSingleResult();
//                amount += a.getQuantity() * e.getPriceSell();
//
//            }
//
//        }
//        System.out.println(amount);
//    }
//
//    public void viewClients() {
//        Query query = em.createQuery("SELECT c FROM Client c", Client.class);
//        List<Client> list = (List<Client>) query.getResultList();
//
//        for (Client c : list)
//            System.out.println(c);
//    }
//
//    public void replenishAccount() {
//        Client c = findClient();
//        Currency cur = currencyType();
//        System.out.println("How much : ");
//        String am = sc.nextLine();
//        double amount = Double.parseDouble(am);
////        try {
//        for (Account ac : c.getAccon()) {
//            if (ac.getCur() == cur) {
//
//                ac.addTransaction(new Transaction(ac, TransactionType.Replenish, amount));
//                em.getTransaction().begin();
//                try {
//                    ac.setQuantity(ac.getQuantity() + amount);
//                    em.persist(ac);
//                    em.getTransaction().commit();
//                } catch (Exception e) {
//                    em.getTransaction().rollback();
//                }
//            }
//        }
////        } catch (NoResultException e) {
////            System.out.println("Account not found");
////        } catch (NonUniqueResultException e) {
////            System.out.println("Non unique result");
////        }
//    }
//
//    public void transferMoney() {
//        System.out.println("Name of the sender ");
//        Client c1 = findClient();
//        System.out.println("Name of the receiver ");
//        Client c2 = findClient();
//        Currency cur = currencyType();
//        System.out.println("How much : ");
//        String am = sc.nextLine();
//        double amount = Double.parseDouble(am);
//        for (Account c : c1.getAccon()) {
//            if (c.getCur() == cur && amount <= c.getQuantity()) {
//                c.setQuantity(c.getQuantity() - amount);
//                c.addTransaction(new Transaction(c, TransactionType.Transfer, amount));
//                for (Account a : c2.getAccon()) {
//                    if (a.getCur() == cur) {
//                        a.setQuantity(a.getQuantity() + amount);
//                        a.addTransaction(new Transaction(a, TransactionType.Replenish, amount));
//                    }
//                    em.getTransaction().begin();
//                    try {
//                        em.persist(a);
//                        em.persist(c);
//                        em.getTransaction().commit();
//                    } catch (Exception e) {
//                        em.getTransaction().rollback();
//                    }
//                }
//            }
//        }
//    }
//
//
//    public Client findClient() {
//
//        System.out.println("Enter client name : ");
//        String name = sc.nextLine();
//        Client c = null;
//        try {
//            Query q = em.createQuery("select c From Client c where c.name =:name", Client.class);
//            q.setParameter("name", name);
//            c = (Client) q.getSingleResult();
//        } catch (NoResultException e) {
//            System.out.println("Client not found");
//
//        } catch (NonUniqueResultException e) {
//            System.out.println("Non unique result");
//
//        }
//        return c;
//    }
//
//    public void convertCurrency() {
//        Client cl = findClient();
//        System.out.println("What currency to convert ? ");
//        Currency sell = currencyType();1
//        System.out.println("In which currency to convert? ");
//        Currency buy = currencyType();
//        System.out.println("How much ?");
//        String am = sc.nextLine();
//        double amount = Double.parseDouble(am);
//        Query query = em.createQuery("Select d From Exchange d Where d.sell =:sell AND d.buy= :buy", Exchange.class);
//        query.setParameter("sell", sell);
//        query.setParameter("buy", buy);
//        Exchange e = (Exchange) query.getSingleResult();
//        double price = e.getPriceSell();
//        for (Account b : cl.getAccon()) {
//            if (b.getCur() == sell && b.getQuantity() >= amount) {
//                b.addTransaction(new Transaction(b, TransactionType.Conversion, amount));
//                b.setQuantity(b.getQuantity() - amount);
//            }
//            if (b.getCur() == buy) {
//                b.addTransaction(new Transaction(b, TransactionType.Replenish, amount * price));
//                b.setQuantity(b.getQuantity() + (price * amount));
//            }
//            em.getTransaction().begin();
//            try {
//                em.persist(b);
//                em.getTransaction().commit();
//            } catch (Exception ex) {
//                em.getTransaction().rollback();
//            }
//        }
//    }
//
//
//    public void close() {
//        em.close();
//        emf.close();
//        sc.close();
//
//    }
//
//}
