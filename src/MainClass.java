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
 */
public class MainClass {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File("team0.xml"));
        System.out.println(doc.getXmlVersion());
        Node node = doc.getFirstChild();
        xmlParse(node);
    }

    public static void xmlParse(Node n){
        System.out.print("<" + n.getNodeName() + showNodeAttributes(n) +  ">");
        if (n.hasChildNodes()) {
            NodeList nodes = n.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node child = nodes.item(i);
                if (child.getNodeType() == 1) {
                    xmlParse(child);
                }
                if (child.getNodeType() == 3) {
                    System.out.print(child.getNodeValue());
                }
            }
        }
        System.out.print("</" + n.getNodeName() + ">");
    }

    public static String showNodeAttributes(Node node) {
        String res = "";
        StringBuilder sb = new StringBuilder();
        NamedNodeMap attributes = node.getAttributes();

        for (int i = 0; i < attributes.getLength(); i++) {
            Node atrNode = attributes.item(i);
            sb.append(" " + atrNode.getNodeName() + " = " + "\"" + atrNode.getNodeValue() + "\"");
        }
        res = sb.toString();
        return res;

    }

}
