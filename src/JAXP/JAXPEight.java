package JAXP;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class JAXPEight{
    public static void main(String args[]){
        GiveData8 give=new GiveData8();
        try { DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true); //忽略缩进空白。
            DocumentBuilder domPaser= factory. newDocumentBuilder();
            Document document=domPaser.parse(new File("ch4_xml/example4_8.xml")) ;
            NodeList nodeList=document.getChildNodes();
            give.output(nodeList);
            System.out.printf("\n一共有%d个Text节点",give.m);
        }
        catch(Exception e){}
    }
}
class GiveData8{
    int m=0;
    public void output(NodeList nodeList){
        int size=nodeList.getLength();
        for(int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.TEXT_NODE){
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                m++;
                System.out.println(content);
            }
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}