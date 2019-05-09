


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.function.Predicate;

public class TreeNode<T> {


    private T data = null;
    private List<TreeNode> children = new ArrayList<>();
    private TreeNode parent = null;

    public TreeNode(T data) {

        this.data = data;
    }

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

    public int getNumberOfChildren() {
        return getChildren().size();
    }
    public boolean hasChildren() {
        return (getNumberOfChildren() > 0);
    }
    public TreeNode<T> getChildAt(int index)  {
        return children.get(index);
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

         if (data.equals(this.data)) {
            return this;
        } else {
            for (TreeNode child : children) {
                TreeNode result = child.findRecChild2(data);
                if (result != null) {
                    return result;
                }
            }
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


    }























