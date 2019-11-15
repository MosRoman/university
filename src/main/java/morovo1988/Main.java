package morovo1988;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Controler m = new Controler();
        try {
            while (true) {
                System.out.println();
                System.out.println("1: Who is head of department ");
                System.out.println("2: Show statistic in department");
                System.out.println("3: Show the average salary for department ");
                System.out.println("4: Show count of employee for");
                System.out.println("5: Global search by");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        m.headOfDepartment();
                        break;
                    case "2":
                        m.showStatisticLectors();
                        break;
                    case "3":
                        m.showSaverageSalary();
                        break;
                    case "4":
                        m.showCountEmployee();
                        break;
                    case "5":
                        m.globalSearch();
                        break;
                        default:
                        return;
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            m.close();
        }
    }
}
