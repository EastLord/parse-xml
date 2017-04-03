package test;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by archer on 17-3-27.
 */
public class testJAX {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        GiveData give = new GiveData();
        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.parse(new File("src/test/test_xml/test.xml"));
            NodeList nodeList = document.getChildNodes();
            give.output(nodeList);
            System.out.println("平均质量:" + give.average / give.count);
            System.out.println("总的阅读时间:" + give.alltime);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * The type Give data.
 */
class GiveData {
    /**
     * The Average.
     */
    double average = 0;
    /**
     * The Alltime.
     */
    double alltime = 0;
    /**
     * The Count.
     */
    int count = 0;

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
            }
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementNode = (Element) node;
                boolean boo = (elementNode.getNodeName()).equals("阅读时间");
                if (boo == true) {
                    alltime = alltime + Double.parseDouble(elementNode.getTextContent().trim());
                }
                String name = elementNode.getNodeName();
                System.out.print(name + ":");
                NamedNodeMap map = elementNode.getAttributes();
                for (int m = 0; m < map.getLength(); m++) {
                    Attr attrNode = (Attr) map.item(m);
                    boolean bool = (attrNode.getName().equals("图书质量"));
                    if (bool == true) {
                        average = average + Double.parseDouble(attrNode.getValue());
                        count++;
                    }
                    String attName = attrNode.getName();
                    String attValue = attrNode.getValue();
                    System.out.print("(" + attName + ":" + attValue + ")");
                }
                NodeList nodes = elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}
