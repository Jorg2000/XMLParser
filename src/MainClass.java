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

/**
 * Created by User on 17.05.15.
 * This code loads XML file and prints his structure.
 */

public class MainClass {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("test.xml"));
        Node node = doc.getFirstChild();
        System.out.println(xmlParse(node));
    }
    //Simple XML Parser
    public static String xmlParse(Node n){
        String res = "";
        res += "<" + n.getNodeName() + showNodeAttributes(n) +  ">";
        if (n.hasChildNodes()) {
            NodeList nodes = n.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node child = nodes.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                     res += xmlParse(child);
                }
                if (child.getNodeType() == Node.TEXT_NODE) {
                    res += child.getNodeValue();
                }
            }
        }
        res += "</" + n.getNodeName() + ">";
        return res;
    }
    // This function extracts attributes from XML node
    public static String showNodeAttributes(Node node) {
        StringBuilder sb = new StringBuilder();
        NamedNodeMap attributes = node.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {
            Node atrNode = attributes.item(i);
            sb.append(" " + atrNode.getNodeName() + " = " + "\"" + atrNode.getNodeValue() + "\"");
        }
        
        return sb.toString();;
    }
}
