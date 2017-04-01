package XPath;

/**
 * Created by archer on 17-4-1.
 */

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.Scanner;

public class XPathFour {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            Scanner reader = new Scanner(System.in);
            String fileName = "ch6_xml/example6_3.xml";
            InputSource source = new InputSource(fileName);
            System.out.print("输入XPath表达式:");
            String path = reader.nextLine();
            NodeList nodelist =
                    (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
            int size = nodelist.getLength();
            System.out.println("节点集中的节点个数:" + size);
            System.out.println("节点的名字依次为:");
            for (int k = 0; k < size; k++) {
                Node node = nodelist.item(k);
                String name = node.getNodeName();
                System.out.println("第" + (k + 1) + "个节点的名字:" + name);
            }
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}