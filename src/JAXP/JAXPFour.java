package JAXP;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
public class JAXPFour{
    public static void main(String args[]){
        GiveData give = new GiveData();
        try{ DocumentBuilderFactory factory=
                DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser=factory.newDocumentBuilder();
            Document document=domPaser.parse(new File("ch4_xml/example4_4.xml")) ;
            Element root=document.getDocumentElement() ;
            NodeList nodeList=root.getChildNodes();
            give.output(nodeList);
            System.out.println("一共有"+give.m+"个Text节点");
        }
        catch(Exception e){}
    }
}
class GiveData{
    int m = 0;
    public void output(NodeList nodeList){    //这是一个递归方法
        int size = nodeList.getLength();
        for(int k=0;k< size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.TEXT_NODE){
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                m++;
                System.out.print(content);
            }
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                System.out.print(name+":");
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}