package SAX;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class SAXThree{
    public static void main(String args[]){
        try {  File file=new File("ch5_xml/example5_3.xml");
            SAXParserFactory factory=SAXParserFactory.newInstance() ;
            factory.setNamespaceAware(true);
            SAXParser saxParser=factory.newSAXParser();
            EventHandler3 handler=new EventHandler3();
            saxParser.parse(file,handler);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class EventHandler3 extends DefaultHandler{
    int count=0;
    public void startElement(String uri,String localName,
                             String qName,Attributes atts){
        count++;
        System.out.print("<"+qName+" ");
        for(int k=0;k<atts.getLength();k++){
            System.out.print(atts.getLocalName(k)+"= ");
            System.out.print("\""+atts.getValue(k)+"\"");
        }
        System.out.println(">");
        if(uri.length()>0)
            System.out.println("标记隶属的名称空间是"+uri);
    }
    public void endElement(String uri,String localName,String qName){
        System.out.println("</"+qName+">");
    }
    public void endDocument(){
        System.out.printf("\n解析过程结束,共有%d个标记",count);
    }
}