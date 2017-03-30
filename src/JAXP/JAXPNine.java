package JAXP;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
public class JAXPNine{
    public static void main(String args[]){
        ModifyNode modify=new ModifyNode();
        try{  DocumentBuilderFactory factory=
                DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser= factory.newDocumentBuilder();
            Document document=domParser.parse(new File("ch4_xml/example4_9.xml")) ;
            Element root=document.getDocumentElement() ;
            NodeList nodeList=root.getChildNodes();
            modify.modifyNode(nodeList,document);
            TransformerFactory transFactory=TransformerFactory.newInstance();
            Transformer transformer=transFactory.newTransformer();
            DOMSource domSource=new DOMSource(document);
            File file=new File("newXML.xml");
            FileOutputStream out=new FileOutputStream(file);
            StreamResult xmlResult=new StreamResult(out);
            transformer.transform(domSource,xmlResult);
            out.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
class ModifyNode{
    int m=0;
    Document document;
    public void modifyNode(NodeList nodeList,Document document){
        this.document=document;
        int size=nodeList.getLength();
        for(int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                if(name.equals("银行")){
                    Node textN=document.createTextNode("18:30");
                    Node elementN=document.createElement("关门时间");
                    elementN.appendChild(textN);
                    elementNode.appendChild(elementN);
                }
                NodeList nodes=elementNode.getChildNodes();
                modifyNode(nodes,document);
            }
        }
    }
} 