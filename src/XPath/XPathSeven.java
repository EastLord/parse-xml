package XPath;

/**
 * Created by archer on 17-4-1.
 */

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XPathSeven {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            InputSource source = new InputSource("ch6_xml/example6_5.xml");
            String path = "/列车时刻表/列车";
            Node node = (Node) xPath.evaluate(path, source, XPathConstants.NODE);
            String name = node.getNodeName();
            String content = node.getTextContent();
            System.out.print(name);
            System.out.println(":" + content);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}