package XPath;

/**
 * Created by archer on 17-4-1.
 */

import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class XPathTen {
    public static void main(String args[]) {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();
            InputSource source = new InputSource("ch6_xml/example8_9.xml");
            String countPath = "count(/成绩表/学生/成绩)";
            Double number = (Double) xPath.evaluate(countPath, source, XPathConstants.NUMBER);
            double n = number.doubleValue();
            String sumPath = "sum(/成绩表/学生/成绩)";
            Double sum = (Double) xPath.evaluate(sumPath, source, XPathConstants.NUMBER);
            double total = sum.doubleValue();
            System.out.println("总成绩:" + total);
            double aver = total / n;
            System.out.println("平均成绩:" + aver);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }
}