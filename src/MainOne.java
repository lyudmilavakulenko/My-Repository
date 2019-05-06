import java.io.*;
import java.util.*;
public class MainOne {

    static class Node {
        Node left;
        Node right;
        String name;
        String surname;
        int kurs;
        int stud;
        double serBal;
        String country;
        public Node() {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя студента");
            this.name = in.next();
            System.out.println("Введите фамилию студента");
            this.surname = in.next();
            System.out.println("Курс студента пожалуйста");
            this.kurs = in.nextInt();
            System.out.println("А также номер его студ. билета)");
            this.stud = in.nextInt();
            System.out.println("Средний бал, который никто не знает");
            this.serBal = in.nextDouble();;
            System.out.println("И страну, где он живёт");
            this.country = in.next();
        }
    }

    public static Node add(String str){
        Scanner in = new Scanner(System.in);
        String tmp;
        Node root = new Node();
        do{
            System.out.println("Добавить ещё одного студента? y/n");
            tmp = in.next();
            if(tmp.equals(str)){
                insert(root, new Node());
            }
        }
        while(tmp.equals(str));
        return root;
    }

    public static Node delete(Node node){

        String str = "4";
        if (node != null) {
            if(node.kurs == 4 && node.country.equals(str)){
                if(node.left != null && node.right == null){
                    node = node.left;
                    node.left = null;
                }
                if(node.left == null && node.right != null){
                    node = node.right;
                }
            }
            delete(node.left);
            // delete(node.right);
        }
        return node;
    }

    public static void run() {
        // build the simple tree from chapter 11.
        Scanner in = new Scanner(System.in);
        Node root = null;
        System.out.println("Добавить первого студента? y/n");
        String tmp = in.next();
        String str = "y";
        if(tmp.equals(str)){
            root = add(str);
        }
        System.out.println("Посмотреть список добавленых студентов? y/n");
        tmp = in.next();
        if(tmp.equals(str)){
            printNode(root);
        }
        System.out.println("Удалить третий курс Украины? y/n");
        tmp = in.next();
        if(tmp.equals(str)){
            root = delete(root);
        }
    }

    public static void insert(Node node, Node obj) {
        if (obj.serBal < node.serBal) {
            if (node.left != null) {
                insert(node.left, obj);
            } else {
                node.left = obj;
            }
        } else if (obj.serBal > node.serBal) {
            if (node.right != null) {
                insert(node.right, obj);
            } else {
                node.right = obj;
            }
        }
    }

    public static void printNode(Node node) {
        if (node != null) {
            printNode(node.left);
            System.out.println(" Элемент дерева: \n" + "Имя " + node.name + "\n" + "Фамилия " + node.surname + "\n" + "Курс " + node.kurs + "\n" + "Средний бал " +  node.serBal + "\n" + "Номер студ. билета " + node.stud);
            System.out.println("=================================");
            printNode(node.right);
        }
    }

    public static void main(String[] args) {
        new MainOne().run();
    }
}