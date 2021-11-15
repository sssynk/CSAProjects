import java.util.Scanner;

public class MainTree {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some ints: ");
        int in = sc.nextInt();
        Tree tree = new Tree(in);
        while(in != -134) {
            in = sc.nextInt();
            tree.add(in);
        }
        while(true) {
            System.out.println("Number to find: ");
            in = sc.nextInt();
            tree.find(in);
            tree.printTreeHorizontal();
        }
    }

}