import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;


/**
 * hhh.class Tree
 * @param <T>;
 */
public class Tree<T> {


    private TreeNode<T> root;

    public Tree(TreeNode root) {
        this.root = root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }

    /**
     * hhh.getRoot
     *
     * @return TreeNode Root;
     */
    public TreeNode<T> getRoot() {
        return root;
    }

    /**
     * hhh.search the tree using the queue
     *
     * @param child;
     * @return TreeNode;
     */
    public TreeNode findChild(TreeNode child) {

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

    /**
     * hhh.recursive tree search
     *
     * @param data;
     * @return TreeNode;
     */
    public TreeNode findRecChild(T data) {
        TreeNode result = null;
        if (root != null) {
            result = helpFind(root, data);
        }
        return result;

    }

    /**
     * hhh.helper method for recursive search
     *
     * @param currentNode;
     * @param data;
     * @return TreeNode;
     */
    public TreeNode helpFind(TreeNode currentNode, T data) {
        TreeNode result = null;
        int iq = 0;
        if (currentNode.getData().equals(data)) {
            result = currentNode;
        } else if (currentNode.hasChildren()) {
            iq = 0;
            while (result == null && iq < currentNode.getNumberOfChildren()) {
                result = helpFind(currentNode.getChildAt(iq), data);
                iq++;
            }
        }
        return result;
    }

    /**
     * hhh.method to get a list of all tree children
     *
     * @return;
     */
    public List<TreeNode> getAllChild() {

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<TreeNode> allChild = new ArrayList<TreeNode>();
        queue.add(root);
        allChild.add(root);
        queue.remove(root);
        List<TreeNode> rootChildren = new ArrayList<TreeNode>(root.getChildren());
        for (TreeNode rootChild : rootChildren) {
            queue.add(rootChild);
            allChild.add(rootChild);
        }
        while (!queue.isEmpty()) {
            TreeNode result = ((LinkedList<TreeNode>) queue).getFirst();
            List<TreeNode> resultChildren = new ArrayList<TreeNode>(result.getChildren());
            for (TreeNode resultGrandChild : resultChildren) {
                ((LinkedList<TreeNode>) queue).add(resultGrandChild);
                allChild.add(resultGrandChild);
            }

            ((LinkedList<TreeNode>) queue).removeFirst();
        }
        return allChild;

    }

    /**
     * hhh.predicate search method
     *
     * @param predicate;
     * @return;
     */
      public List<TreeNode> findSomething(Predicate<TreeNode> predicate) {

        List<TreeNode> allChildren = getAllChild();
        Collections.addAll(getAllChild());

        List<TreeNode> filteredChildren = allChildren.stream().filter(predicate).collect(Collectors.toList());
        return filteredChildren;
    }

    /**
     * hhh.XMLtree building method
     *
     * @param root;
     */
    public void buildXMLTree(TreeNode root) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement(root.getData().toString());
            rootElement.setAttribute("Data", root.getData().toString());
            doc.appendChild(rootElement);

            if (root.getChildren() != null) {
                for (int y = 0; y < root.getChildren().size(); y++) {
                    TreeNode childRoot = root.getChildAt(y);
                    Element child = doc.createElement(childRoot.getData().toString());
                    child.setAttribute("Data", childRoot.getData().toString());
                    rootElement.appendChild(child);

                    while (childRoot.hasChildren()) {
                        int iq = 0;
                        TreeNode grandChild = childRoot.getChildAt(iq);
                        Element childNext = doc.createElement(grandChild.getData().toString());
                        childNext.setAttribute("Data", grandChild.getData().toString());
                        child.appendChild(childNext);
                        childRoot = grandChild;
                        child = childNext;
                        iq++;
                    }
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("C:\\Users\\Людмила\\IdeaProjects\\Lyuda\\treeXML.xml"));

            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hhh.XMLtree reading method
     */
    public void readXML() {
        File xmlFile = new File("C:\\Users\\Людмила\\IdeaProjects\\Lyuda\\treeXML.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFile);
            Node rootNode = doc.getDocumentElement();
            rootNode.normalize();
            System.out.println(" Элеменn Root [" + rootNode.getNodeName() + "]");
            printXMLTree(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hhh.XMLtree printing method
     *
     * @param root;
     */
    public static void printXMLTree(Node root) {
        NodeList rootChildren = root.getChildNodes();
        for (int i = 0; i < rootChildren.getLength(); i++) {
            Node node = rootChildren.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                String textInformation = node.getNodeValue().replace("\n", "").trim();
                if (!textInformation.isEmpty()) {
                    System.out.println("Внутри элемента текст: " + node.getNodeValue());
                }
            }
            else {
                System.out.println("Элемент: " + node.getNodeName() + " атрибуты:");
                NamedNodeMap attributes = node.getAttributes();
                for (int k = 0; k < attributes.getLength(); k++) {
                    System.out.print("   Имя " + attributes.item(k).getNodeName());
                    System.out.println(", значение: " + attributes.item(k).getNodeValue());
                }
            }
            if (node.hasChildNodes()) {
                printXMLTree(node);
            }
        }
    }

}




/*public void convertToXML (Tree tree, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(tree, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }*/









