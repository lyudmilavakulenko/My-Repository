import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.util.function.Predicate;

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


    public List <TreeNode> allChild () {

        List <TreeNode> allChild = new ArrayList<>();
        allChild.add(root);
        Iterator <TreeNode> iterator = root.getChildren().iterator();
        while (iterator.hasNext()) {
            TreeNode node = iterator.next();
            if (node.getChildren()!=null){
                allChild.addAll(root.getChildren());
                root = node;

            }
        }
        return allChild;
    }

    public TreeNode findRecChild2(T data) {

        if (root.getData().equals(data)) {
            return root;

        } else {
            Iterator<TreeNode> iterator = root.getChildren().iterator();
            while (iterator.hasNext()) {
                TreeNode nextChild = iterator.next();
                if (!data.equals(nextChild.getData())) {
                    root = nextChild;
                    TreeNode treeNode = findRecChild2(data);
                    if (treeNode.getData().equals(data)) {
                        return treeNode;
                    }
                }
                return nextChild;
            }

        }
        return null;
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

   /* public List<TreeNode> find (Predicate<T> predicate) {





    }
*/

}