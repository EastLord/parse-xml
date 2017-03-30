package JAXP;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class JAXPTwo {
    public static void main(String[] args) {
        try{
            DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser=factory.newDocumentBuilder();
            org.w3c.dom.Document document=domParser.parse(new File("ch4_xml/example4_2.xml"));
            NodeList nodeList=document.getChildNodes();
            output(nodeList);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public static void output(NodeList nodeList){
        int size=nodeList.getLength();
        for (int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.TEXT_NODE){
                Text textNode=(Text)node;
                String content =textNode.getWholeText();
                System.out.print(content);
            }
            if (node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode =(Element)node;
                String name=elementNode.getNodeName();
                System.out.print(name+":");
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);
            }
        }
    }
}

