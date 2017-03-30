package JAXP;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class JAXPThree{
    public static void main(String args[]){
        try { DocumentBuilderFactory  factory=
                DocumentBuilderFactory. newInstance();
            DocumentBuilder domPaser=factory.newDocumentBuilder();
            Document  document=domPaser.parse(new File("ch4_xml/example4_3.xml")) ;
            Element root=document.getDocumentElement() ;
            NodeList nodeList=root.getChildNodes();
            int size=nodeList.getLength();
            for(int k=0;k<size;k++){
                Node node=nodeList.item(k);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element elementNode=(Element)node;
                    String name=elementNode.getNodeName();
                    String id=elementNode.getAttribute("分类");
                    String content=elementNode.getTextContent();
                    System.out.print(name);
                    System.out.print("("+id+")");
                    System.out.println("："+content);
                }
            }
        }
        catch(Exception e){}
    }
}