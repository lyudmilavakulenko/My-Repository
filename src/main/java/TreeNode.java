import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

/**
 * hhh.class TreeNode
 * @param <T>;
 */
public class TreeNode<T> {
    private T data = null;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private TreeNode parent = null;

    public TreeNode(T data) {
        this.data = data;
    }

    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    /**
     * hhh.method to add node
     *
     * @param data;
     */
    public void addChild(T data) {
        TreeNode<T> newChild = new TreeNode<T>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    /**
     * hhh.method to add nodes
     *
     * @param children;
     */
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


    public TreeNode getChildAt(int index) {
        return children.get(index);
    }

    public boolean hasChildren() {
        return (getChildren().size() > 0);
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }


    public void removeChild(TreeNode child) {
        children.remove(child);
    }

    /**
     * hhh.method to delete node
     *
     * @param data;
     */
    public void removeChild(T data) {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            if (nextChild.data.equals(data)) {
                childIterator.remove();
            }
        }
    }

    /**
     * hhh.search node Root
     *
     * @return TreeNode root;
     */
    public TreeNode findRoot() {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            while (nextChild.getParent() != null) {
                nextChild = nextChild.getParent();
                if (nextChild.getParent() == null) {
                    return nextChild;
                }
            }

        }
        return null;
    }


    /**
     * hhh.search node Root
     *
     * @param data;
     * @return TreeNode;
     */
    public TreeNode findRoot(T data) {
        Iterator<TreeNode> childIterator = children.iterator();
        while (childIterator.hasNext()) {
            TreeNode nextChild = childIterator.next();
            while (nextChild.getParent() != null) {
                nextChild = nextChild.getParent();
                if (nextChild.getParent() == null) {
                    return nextChild;
                }
            }

        }
        return null;
    }

    /**
     * hhh.search node recursively
     *
     * @param data;
     * @return TreeNode;
     */
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

    /**
     * hhh.searching a node using a stack
     *
     * @param data;
     * @return TreeNode;
     */
    public TreeNode findRecChild(T data) {

        Iterator<TreeNode> iterator = children.iterator();
        TreeNode root = iterator.next().findRoot();

        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.getData().equals(data)) {
                return node;
            }
            List<TreeNode> nodes = new ArrayList<TreeNode>(node.getChildren());
            Collections.reverse(nodes);
            for (TreeNode child : nodes) {
                stack.push(child);
            }
        }
        return null;
    }

    /**
     * hhh.searching a node using a queue
     *
     * @param child;
     * @return TreeNode4
     */
    public TreeNode findChild(TreeNode child) {
        TreeNode root = child.findRoot();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        if (root.equals(child)) {
            return child;
        }
        queue.remove(root);
        List<TreeNode> rootChildren = new ArrayList<TreeNode>(root.getChildren());
        for (TreeNode rootChild : rootChildren) {
            queue.add(rootChild);
        }
        while (!queue.isEmpty()) {
            TreeNode result = ((LinkedList<TreeNode>) queue).getFirst();
            List<TreeNode> resultChildren = new ArrayList<TreeNode>(result.getChildren());
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










