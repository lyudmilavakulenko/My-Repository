
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.function.Predicate;
import java.util.stream.*;

public class TreeNode<T> {


    private T data = null;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;

    public TreeNode(T data) {

        this.data = data;
    }
    /*public static int compare (TreeNode node1, TreeNode node2){
        if (node1.getData() > node2.getData())
            return 1;
        return  -1;
    }*/

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        TreeNode<T> newChild = new TreeNode<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(List<TreeNode> children) {
        for (TreeNode t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void removeChild(TreeNode child) {
        children.remove(child);
    }

    public void removeChild(T data) {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            if (nextChild.data.equals(data)) {
                childIterator.remove();
            }
        }
    }

    public TreeNode findRoot() {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            while (nextChild.getParent() != null) {
                nextChild = nextChild.getParent();
                if (nextChild.getParent() == null)
                    return nextChild;
            }

        }
        return null;
    }

    public TreeNode findRoot(T data) {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            while (nextChild.getParent() != null) {
                nextChild = nextChild.getParent();
                if (nextChild.getParent() == null)
                    return nextChild;
            }

        }
        return null;
    }


    public TreeNode findRecChild2(T data) {

        Iterator<TreeNode> iterator = children.iterator();
        while (iterator.hasNext()) {
            TreeNode nextChild = iterator.next();
            if (!data.equals(nextChild.getData())) {
                TreeNode treeNode = nextChild.findRecChild2(data);
                if (treeNode.getData().equals(data)) {
                    return treeNode;
                }

            }
            return nextChild;
        }

        return null;
    }


    public TreeNode findRecChild(T data) {

        Iterator<TreeNode> iterator = children.iterator();
        TreeNode root = iterator.next().findRoot();

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.getData().equals(data)) {
                return node;
            }
            List<TreeNode> nodes = new ArrayList<>(node.getChildren());
            Collections.reverse(nodes);
            for (TreeNode child : nodes) {
                stack.push(child);
            }
        }
        return null;
    }


    public TreeNode findChild(TreeNode child) {
        TreeNode root = child.findRoot();
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

    /*public List <TreeNode> findAllNode () {
        Iterator <TreeNode> iterator = children.iterator();
        List <TreeNode> allNodes = new ArrayList<>();
        while (iterator.hasNext()){
            TreeNode nextChild = iterator.next();
            if (nextChild.getData().toString().contains("dj")){
                return children;
            }
            allNodes.add(nextChild);
            nextChild.findAllNode();
        }
        return allNodes;
    }*/

    public void example() {

        List<TreeNode> treeNodeStream = children;
        Collections.addAll(children);
        //
        Predicate <TreeNode> predicate1 = s -> s.getData().toString().contains("Ch");
        Predicate <TreeNode> predicate2 = s -> s.getData().toString().endsWith("2");
        Predicate <TreeNode> predicate3 = s -> s.findRecChild(data.toString().contains("Ch")).toString().contains("Ch");



        treeNodeStream.stream().filter(predicate3).forEach(s -> System.out.println(s.getData()));
       //treeNodeStream.stream().filter(predicate2).forEach(s -> System.out.println(s.getData()));

        // treeNodeStream.stream().map(p -> p.getData()).forEach(p -> System.out.println(p));
        //boolean contains = treeNodeStream.stream().anyMatch("C"::equals);
    }
}




  /* public List<TreeNode> find(Predicate<T> predicate) {

        Predicate predicate1 = (T data)


        }
*/














