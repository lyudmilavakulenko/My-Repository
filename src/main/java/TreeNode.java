
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


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
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

   public void setParent(TreeNode parent) {
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
}