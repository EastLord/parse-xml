package XPath;

/**
 * Created by archer on 17-4-1.
 */

import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XPathEight {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            InputSource source = new InputSource("ch6_xml/example6_5.xml");
            String path = "/列车时刻表/列车/始发站";
            String stateName = (String) xPath.evaluate(path, source, XPathConstants.STRING);
            System.out.println("始发站标记包含的文本:");
            System.out.print(stateName);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}