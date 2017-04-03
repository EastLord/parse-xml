package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXSeven {
    public static void main(String args[]) {
        try {
            File file = new File("ch5_xml/example5_7.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(true);
            SAXParser saxParser = factory.newSAXParser();
            EventHandler7 handler = new EventHandler7();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class EventHandler7 extends DefaultHandler {
    public void warning(SAXParseException e) throws SAXException {
        String warningMessage = e.getMessage();
        int row = e.getLineNumber();
        int colums = e.getColumnNumber();
        System.out.println("警告：" + warningMessage + "位置：" + row + "," + colums);
        System.out.println("publicId:" + e.getPublicId());
        System.out.println("systemId:" + e.getSystemId());
    }

    public void error(SAXParseException e) throws SAXException {
        String errorMessage = e.getMessage();
        int row = e.getLineNumber();
        int colums = e.getColumnNumber();
        System.out.println("一般错误：" + errorMessage + "位置：" + row + "," + colums);
        System.out.println("publicId:" + e.getPublicId());
        System.out.println("systemId:" + e.getSystemId());
    }

    public void fatalError(SAXParseException e) throws SAXException {
        String fatalErrorMessage = e.getMessage();
        int row = e.getLineNumber();
        int colums = e.getColumnNumber();
        System.out.println("致命错误：" + fatalErrorMessage + "位置：" + row + "," + colums);
        System.out.println("publicId:" + e.getPublicId());
        System.out.println("systemId:" + e.getSystemId());
        throw new SAXException("致命错误，停止解析");
    }

    public void startDocument() {
        System.out.println("开始解析XML文件");
    }

    public void endDocument() {
        System.out.println("解析过程结束");
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        System.out.print("<" + qName + ">");
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
}