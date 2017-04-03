package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

/**
 * The type Sax five.
 */
public class SAXFive {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        try {
            File file = new File("ch5_xml/example5_5.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            EventHandler5 handler = new EventHandler5();
            saxParser.parse(file, handler);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

/**
 * The type Event handler 5.
 */
class EventHandler5 extends DefaultHandler {
    /**
     * The Is computabled.
     */
    boolean isComputabled,
    /**
     * The Math.
     */
    math,
    /**
     * The English.
     */
    english;
    /**
     * The Count.
     */
    int count;
    /**
     * The Math sum.
     */
    double mathSum,
    /**
     * The English sum.
     */
    englishSum,
    /**
     * The Person sum.
     */
    personSum;
    /**
     * The Number content.
     */
    StringBuffer numberContent,
    /**
     * The Other content.
     */
    otherContent;

    public void startElement(String uri, String localName,
                             String qName, Attributes atts) {
        numberContent = new StringBuffer();
        otherContent = new StringBuffer();
        System.out.print("<" + qName + ">");
        if (qName.endsWith("成绩"))
            isComputabled = true;
        if (qName.startsWith("数学"))
            math = true;
        if (qName.startsWith("英语"))
            english = true;
        if (qName.equals("学生")) {
            personSum = 0;
            count++;
        }
    }

    public void characters(char[] ch, int start, int length) {
        String text = new String(ch, start, length);
        if (isComputabled == true)
            numberContent.append(text);
        System.out.print(text);
    }

    public void endElement(String uri, String localName, String qName) {
        System.out.print("</" + qName + ">");
        if (isComputabled) {
            String numberStr = new String(numberContent);
            numberStr = numberStr.trim();
            double d = Double.parseDouble(numberStr);
            personSum = personSum + d;
            if (math)
                mathSum = mathSum + d;
            if (english)
                englishSum = englishSum + d;
        }
        isComputabled = false;
        math = false;
        english = false;
        if (qName.equals("学生"))
            System.out.print("该学生的总成绩：" + personSum);
    }

    public void endDocument() {
        System.out.println("");
        System.out.println("共有" + count + "名学生");
        System.out.println("数学平均成绩：" + mathSum / count);
        System.out.println("英语平均成绩：" + englishSum / count);
    }
}