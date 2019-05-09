import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tree <T> {

    private TreeNode<T> root;


    public Tree(TreeNode root) {
        this.root = root;

    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getNumberOfNodes() {
        int numberOfNodes = 0;

        if (root != null) {
            numberOfNodes = auxiliaryGetNumberOfNodes(root) + 1; //1 for the root!
        }

        return numberOfNodes;
    }

    private int auxiliaryGetNumberOfNodes(TreeNode<T> node) {
        int numberOfNodes = node.getNumberOfChildren();

        for (TreeNode<T> child : node.getChildren()) {
            numberOfNodes += auxiliaryGetNumberOfNodes(child);
        }

        return numberOfNodes;
    }


    public TreeNode<T> find(T data) {
        TreeNode<T> returnNode = null;

        if (root != null) {
            returnNode = helpFind(root, data);
        }

        return returnNode;
    }

    private TreeNode<T> helpFind(TreeNode<T> currentNode, T data) {
        TreeNode<T> returnNode = null;
        int i = 0;

        if (currentNode.getData().equals(data)) {
            returnNode = currentNode;
        } else if (currentNode.hasChildren()) {
            i = 0;
            while (returnNode == null && i < currentNode.getNumberOfChildren()) {
                returnNode = helpFind(currentNode.getChildAt(i), data);
                i++;
            }
        }

        return returnNode;
    }

    public TreeNode findChild(TreeNode child) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        if (root.equals(child)) {
            return child;
        }
        queue.remove(root);
        List<TreeNode> rootChildren = new ArrayList<>(root.getChildren());
        for (TreeNode rootChild : rootChildren) {
            queue.add(rootChild);
        }
        while (!queue.isEmpty()) {
            TreeNode result = ((LinkedList<TreeNode>) queue).getFirst();
            List<TreeNode> resultChildren = new ArrayList<>(result.getChildren());
            for (TreeNode resultGrandChild : resultChildren) {
                ((LinkedList<TreeNode>) queue).add(resultGrandChild);
            }
            if (result.equals(child)) {
                return result;
            }
            ((LinkedList<TreeNode>) queue).removeFirst();
        }
        return null;

    }

    public ArrayList <TreeNode> allChild () {

        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList <TreeNode> allChild = new ArrayList<>();
        queue.add(root);
        allChild.add(root);
            queue.remove(root);

            List<TreeNode> rootChildren = new ArrayList<>(root.getChildren());
            for (TreeNode rootChild : rootChildren) {
                queue.add(rootChild);
                allChild.add(rootChild);
            }
            while (!queue.isEmpty()) {
                TreeNode result = ((LinkedList<TreeNode>) queue).getFirst();
                List<TreeNode> resultChildren = new ArrayList<>(result.getChildren());
                for (TreeNode resultGrandChild : resultChildren) {
                    ((LinkedList<TreeNode>) queue).add(resultGrandChild);
                    allChild.add(resultGrandChild);
                }

                ((LinkedList<TreeNode>) queue).removeFirst();
            }
        return allChild;

        }


    public List<TreeNode> findSomething () {

        ArrayList <TreeNode> allChildren = allChild();
        Collections.addAll(allChild());

        List <TreeNode> filteredChildren = allChildren.stream().filter(s -> s.getData().toString().contains("5")).collect(Collectors.toList());
        return filteredChildren;
    }













    }
