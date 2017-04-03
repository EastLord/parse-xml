package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * The type Sax eight.
 */
public class SAXEight {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        try {
            File file = new File("ch5_xml/example5_7.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser saxParser = factory.newSAXParser();
            EventHandler8 handler = new EventHandler8();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * The type Event handler 8.
 */
class EventHandler8 extends DefaultHandler {
    /**
     * The Count.
     */
    int count = 0;

    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        System.out.print(text);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) {
        count++;
        System.out.print("第" + count + "个空白区");
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        System.out.print("<" + localName + ">");
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.print("</" + localName + ">");
    }

    public void endDocument() {
        System.out.println("解析过程结束,报告了" + count + "次空白");
    }
}