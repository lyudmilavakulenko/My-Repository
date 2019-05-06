import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class DOMExample2 {
    private static ArrayList <Animal> animals = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException  {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File ("resource/xml_file2.xml"));
        NodeList animalElements = document.getDocumentElement().getElementsByTagName("animal");

        for (int i=1; i<animalElements.getLength(); i++){
            Node animal = animalElements.item(i);

            NamedNodeMap attributs = animal.getAttributes();

            animals.add(new Animal(attributs.getNamedItem("name").getNodeValue(), attributs.getNamedItem("view").getNodeValue(), attributs.getNamedItem("color").getNodeValue()));

        }
        for (Animal animal : animals)
            System.out.println(String.format("Информация о животном: имя - %s, вид - %s, цвет - %s", animal.getName(), animal.getView(), animal.getColor()));


    }
}
