import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * The type Test sax.
 */
public class testSAX {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        try {
            File file = new File("test.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();
            EventHandler handler = new EventHandler();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * The type Event handler.
 */
class EventHandler extends DefaultHandler {
    /**
     * The Count.
     */
    int count = 0;

    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        System.out.print("<" + qName + ">");

        if (qName.equals("图书"))
            count++;
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.print("</" + qName + ">");
    }

    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        System.out.print(text);
    }

    public void ignorableWhitespace(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        System.out.print(text);
    }

    public void endDocument() {
        System.out.println("\n图书数：" + count);
    }
}