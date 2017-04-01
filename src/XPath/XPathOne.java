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

public class XPathOne {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            InputSource source = new InputSource("ch6_xml/example6_1.xml");
            String path = "/学生列表/学生[成绩>60]/姓名";
            NodeList nodelist =
                    (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
            int size = nodelist.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodelist.item(k);
                String name = node.getNodeName();
                String content = node.getTextContent();
                System.out.print(name);
                System.out.println(":" + content);
            }
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}