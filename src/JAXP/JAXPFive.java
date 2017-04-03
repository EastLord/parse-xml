package JAXP;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.*;

/**
 * The type Jaxp five.
 */
public class JAXPFive {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        GiveData5 give = new GiveData5();
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.parse(new File("ch4_xml/example4_5.xml"));
            NodeList nodeList = document.getChildNodes();
            give.output(nodeList);
            System.out.printf("平均价格:%5.2f%s", give.average / give.m, give.mess);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * The type Give data 5.
 */
class GiveData5 {
    /**
     * The Average.
     */
    double average = 0,
    /**
     * The M.
     */
    m = 0;
    /**
     * The Mess.
     */
    String mess;

    /**
     * Output.
     *
     * @param nodeList the node list
     */
    public void output(NodeList nodeList) {
        int size = nodeList.getLength();
        for (int k = 0; k < size; k++) {
            Node node = nodeList.item(k);
            if (node.getNodeType() == Node.TEXT_NODE) {
                Text textNode = (Text) node;
                String content = textNode.getWholeText();
                System.out.print(content);
                Element parent = (Element) textNode.getParentNode();
                boolean boo = (parent.getNodeName()).equals("价格");
                if (boo == true) {
                    content = textNode.getWholeText();
                    average = average + Double.parseDouble(content.trim());
                    m++;
                }
            }
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementNode = (Element) node;
                String name = elementNode.getNodeName();
                String id = elementNode.getAttribute("单位");
                if (id.length() > 0) {
                    System.out.print(name + "(" + id + ")：");
                    mess = id;
                }
                NodeList nodes = elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}