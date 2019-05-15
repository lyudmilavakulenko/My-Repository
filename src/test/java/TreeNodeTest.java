
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;

public class TreeNodeTest {

    @org.testng.annotations.Test
    public void testAddChild() {
        TreeNode <String> node = new TreeNode <>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        node.addChild(child);
        assertEquals(node.getNumberOfChildren(), 1);
    }

    @org.testng.annotations.Test
    public void testAddChild1() {
        TreeNode <String> node = new TreeNode <>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        node.addChild("Child");
        assertEquals(node.getNumberOfChildren(), 1);
    }

    @org.testng.annotations.Test
    public void testAddChildren() {
        TreeNode <String> node = new TreeNode <>("Node");
        node.addChildren(Arrays.asList(new TreeNode<>("Child1"),
            new TreeNode<>("Child2"),
            new TreeNode<>("Child3")));
        assertEquals(node.getNumberOfChildren(), 3);
    }

    @org.testng.annotations.Test
    public void testSetAndGetChildren() {
        TreeNode <String> node = new TreeNode<>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        List<TreeNode> children = new ArrayList<>();
        children.add(child);
        node.setChildren(children);
        assertEquals(node.getChildren(), children);
    }

    @org.testng.annotations.Test
    public void testSetAndGetData() {
        TreeNode<String> node = new TreeNode<String>("Data");
        String data = "data";
        node.setData(data);
        assertEquals(node.getData(), data);
    }

    @org.testng.annotations.Test
    public void testSetAndGetParent() {
        TreeNode <String> node = new TreeNode<>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        child.setParent(node);
        assertEquals(child.getParent(), node);
    }

    @org.testng.annotations.Test
    public void testGetChildAt() {
        TreeNode <String> node = new TreeNode<>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        TreeNode <String> child2 = new TreeNode<>("Child2");
        node.addChild(child);
        node.addChild(child2);
        assertEquals(node.getChildAt(1), child2);

    }

    @org.testng.annotations.Test
    public void testHasChildren() {
        TreeNode <String> node = new TreeNode<>("Node");
        TreeNode <String> child = new TreeNode<>("Child");
        List<TreeNode> children = new ArrayList<>();
        children.add(child);
        node.setChildren(children);
        assertTrue(node.hasChildren());

    }

    @org.testng.annotations.Test
    public void testGetNumberOfChildren() {
    }

    @org.testng.annotations.Test
    public void testRemoveChild() {
    }

    @org.testng.annotations.Test
    public void testRemoveChild1() {
    }

    @org.testng.annotations.Test
    public void testFindRoot() {
    }

    @org.testng.annotations.Test
    public void testFindRoot1() {
    }
}