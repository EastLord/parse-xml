package shiyan;

/**
 * Created by archer on 17-4-7.
 */

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The type shiyan1_2.
 */
public class shiyan1_2 {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        GiveData give = new GiveData();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true); //忽略缩进空白。
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.parse(new File("src/shiyan/shiyan_xml/book.xml"));
            NodeList nodeList = document.getChildNodes();
            give.output(nodeList);
            System.out.printf("\n一共有%d个Text节点", give.Text_count);
            System.out.printf("\n一共有%d个Element节点",give.Element_count);
        } catch (Exception e) {
        }
    }
}

/**
 * The type Give data.
 */
class GiveData {
    /**
     * The Text_count.
     */
    int Text_count = 0;
    /**
     * The Element count.
     */
    int Element_count = 0;
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
                Text_count++;
                System.out.print(content);
            }
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elementNode = (Element) node;
                String name = elementNode.getNodeName();
                Element_count++;
                System.out.println(name + ":");
                NodeList nodes = elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}
