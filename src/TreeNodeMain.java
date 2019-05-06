import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.io.*;
import java.util.*;

public class TreeNodeMain {

    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("Root");

        TreeNode<String> child1 = new TreeNode<>("Child1");
        TreeNode<String> grandChild5 = new TreeNode<>("GrandChild5");
        child1.addChild(grandChild5);
        grandChild5.addChild("Grandchild6");
        child1.addChild("Grandchild1");
        child1.addChild("Grandchild2");

        TreeNode<String> child2 = new TreeNode<>("Child2");
        child2.addChild("Grandchild3");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild("Child3");

        root.addChildren(Arrays.asList(
                new TreeNode<>("Child4"),
                new TreeNode<>("Child5"),
                new TreeNode<>("Child6")
        ));

        for (TreeNode node : root.getChildren()) {
            System.out.println(node.getData());
        }

        for (TreeNode node : child1.getChildren()) {
            System.out.println(node.getData());
        }


        root.removeChild("Child4");
        for (TreeNode node : root.getChildren()) {
            System.out.println(node.getData());
        }

        System.out.println(" ");

        System.out.println(child1.findRoot().getData().toString());
        System.out.println(grandChild5.findRoot().getData().toString());
        System.out.println(" ");
        System.out.println(root.findRecChild("Child5").getData().toString());
        System.out.println(root.findRecChild("Grandchild2").getData().toString());
        System.out.println(" ");
        /*System.out.println(root.findChild(root).getData().toString());
        System.out.println(root.findChild(child1).getData().toString());
        System.out.println(root.findChild(grandChild5).getData().toString());*/

        System.out.println(root.findRecChild2("Grandchild6").getData().toString());

        System.out.println("After Stream");

        root.example();



    }
}