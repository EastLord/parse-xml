package JAXP;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class JAXPSix{
    public static void main(String args[]){
        GiveData6 give=new GiveData6();
        try { DocumentBuilderFactory factory=
                DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser=factory.newDocumentBuilder();
            Document document=domPaser.parse(new File("ch4_xml/example4_6.xml")) ;
            Element root=document.getDocumentElement() ;
            NodeList nodeList=root.getChildNodes();
            give.output(nodeList);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class GiveData6{
    public void output(NodeList nodeList){
        int size=nodeList.getLength();
        for(int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.TEXT_NODE){
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                System.out.print(content);
            }
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                System.out.print(name+":");
                NamedNodeMap map=elementNode.getAttributes();
                for(int m=0;m<map.getLength();m++){
                    Attr attrNode=(Attr)map.item(m);
                    String attName=attrNode.getName();
                    String attValue=attrNode.getValue();
                    System.out.print("("+attName+":"+attValue+")");
                }
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}