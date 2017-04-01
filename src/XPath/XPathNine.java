package XPath;

/**
 * Created by archer on 17-4-1.
 */

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XPathNine {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            InputSource source = new InputSource("ch6_xml/example8_9.xml");
            double sum = 0;
            String path = "成绩表/学生";
            NodeList nodelist =
                    (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
            int size = nodelist.getLength();
            for (int i = 1; i <= size; i++) {
                path = "成绩表/学生[" + i + "]/成绩";
                Double number = (Double) xPath.evaluate(path, source, XPathConstants.NUMBER);
                double score = number.doubleValue();
                sum = sum + score;
            }
            double aver = sum / size;
            System.out.println("学生的平均成绩" + aver);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}