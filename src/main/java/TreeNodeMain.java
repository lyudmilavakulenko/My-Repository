
import java.util.function.Predicate;


/**
 * hhh.classTreeNodeMain
 */
public class TreeNodeMain {

    /**
     * hhh.method Main
     * @param args;
     */
    public static void main(String[] args) {

        TreeNode<String> root = new TreeNode<String>("Root");

        Tree<String> tree = new Tree<String>(root);

        TreeNode<String> child1 = new TreeNode<String>("Child1");
        TreeNode<String> grandChild5 = new TreeNode<String>("GrandChild5");
        child1.addChild(grandChild5);
        grandChild5.addChild("GrandChild6");
        child1.addChild("Grandchild1");
        child1.addChild("Grandchild2");

        TreeNode<String> child2 = new TreeNode<String>("Child2");
        child2.addChild("Grandchild3");

        root.addChild(child1);
        root.addChild(child2);
        root.addChild("Child3");

        /*root.addChildren(Arrays.asList(
            new TreeNode<>("Child4"),
            new TreeNode<>("Child5"),
            new TreeNode<>("Child6")
        ));*/

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

        System.out.println(" --- ");

        System.out.println(child1.findRoot().getData().toString());
        System.out.println(grandChild5.findRoot().getData().toString());
        System.out.println(" --- ");
        //System.out.println(root.findRecChild("Child5").getData().toString());
        System.out.println(root.findRecChild("Grandchild2").getData().toString());

        System.out.println(" --- ");

        System.out.println(root.findChild(child1).getData().toString());
        System.out.println(root.findChild(grandChild5).getData().toString());

        System.out.println(root.findRecChild2("Child2").getData().toString());
        System.out.println(root.findRecChild2("GrandChild6").getData().toString());

        System.out.println(" --- ");

        System.out.println(tree.findRecChild("Child2").getData().toString());
        System.out.println(tree.findRecChild("GrandChild6").getData().toString());

        System.out.println(" --- ");

        for (TreeNode node : tree.getAllChild()) {
            System.out.println(node.getData());
        }
        System.out.println(" --- ");

        Predicate<TreeNode> predicate1 = new Predicate<TreeNode>() {
            public boolean test(TreeNode child) {
                return child.getData().toString().contains("3");
            }
        };

        for (TreeNode node : tree.findSomething(predicate1)) {
            System.out.println(node.getData());
        }

        //tree.buildXMLTree(root);

        System.out.println(" ");
        System.out.println("Печать XML файла");
        tree.readXML();

    }
}