package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXOne {
    public static void main(String args[]) {
        try {
            File file = new File("ch5_xml/example5_1.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EventHandler1 handler = new EventHandler1();  //事件处理器
            saxParser.parse(file, handler);
            System.out.println("事件处理器处理了" + handler.count + "个事件");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class EventHandler1 extends DefaultHandler {
    int count = 0;

    public void startElement(String uri, String localName, String qName, Attributes atts) {
        System.out.print("<" + qName + ">");
        count++;
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.print("</" + qName + ">");
        count++;
    }

    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        System.out.print(text);
        count++;
    }

    public void startDocument() {
        System.out.println("开始解析XML文件");
        count++;
    }

    public void endDocument() {
        System.out.println("解析过程结束");
        count++;
    }
}