package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXSix {
    public static void main(String args[]) {
        try {
            File file = new File("ch5_xml/example5_6.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();
            EventHandler6 handler = new EventHandler6();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class EventHandler6 extends DefaultHandler {
    int count = 0;
    String uri;

    public void startPrefixMapping(String prefix, String uri) {
        count++;
        System.out.print("前缀:" + prefix + " ");
        System.out.println("名称空间的名称:" + uri);
    }

    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        if (uri != null && uri.equals("清华大学出版社"))
            System.out.print(text);
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        this.uri = uri;
        if (uri.equals("清华大学出版社")) {
            System.out.print("<" + qName + ">");
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (uri.equals("清华大学出版社"))
            System.out.println("</" + qName + ">");
    }

    public void endDocument() {
        System.out.println("解析过程结束,报告了" + count + "次名称空间");
    }
}