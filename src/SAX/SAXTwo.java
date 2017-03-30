package SAX;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
public class SAXTwo{
    public static void main(String args[]){
        try{  File file=new File("ch5_xml/example5_2.xml");
            SAXParserFactory factory=SAXParserFactory.newInstance() ;
            SAXParser saxParser=factory.newSAXParser();
            EventHandler2 handler=new EventHandler2(file);
            saxParser.parse(file,handler);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class EventHandler2 extends DefaultHandler{
    File file;
    long timeStart=0,timeEnd=0;
    public EventHandler2(File f){
        file=f;
    }
    public void startDocument(){
        timeStart=System.currentTimeMillis();
        System.out.println("开始解析XML文件");
        System.out.println("文件长度："+file.length());
    }
    public void endDocument(){
        timeEnd=System.currentTimeMillis();
        System.out.println("解析过程结束");
        System.out.println("所用时间："+(timeEnd-timeStart)+"毫秒");
    }
}
