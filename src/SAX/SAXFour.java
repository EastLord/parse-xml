package SAX;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class SAXFour{
    public static void main(String args[]){
        try {  File file=new File("ch5_xml/example5_4.xml");
            SAXParserFactory factory=SAXParserFactory.newInstance() ;
            SAXParser saxParser=factory.newSAXParser();
            EventHandler4 handler=new EventHandler4();
            saxParser.parse(file,handler);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class EventHandler4 extends DefaultHandler{
    int textEventCount;
    public void characters(char[] ch,int start,int length){
        textEventCount++;
        String text=new String(ch,start,length);
        text=text.trim();
        if(text.length()==0)
            System.out.println("第 "+textEventCount+" 次文本事件处理的文本是空白字符");
        else
            System.out.println("第 "+textEventCount+" 次文本事件处理的文本是\""+text+"\"");
    }
}
