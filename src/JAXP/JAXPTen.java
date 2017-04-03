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

/**
 * The type Jaxp ten.
 */
public class JAXPTen {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        try {
            String[] studentName = {"辛如学", "纲尚学", "楚进校"};
            String[] studentNumber = {"201001", "201002", "201003"};
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.newDocument();
            document.setXmlVersion("1.0");
            Element root = document.createElement("学生列表");
            document.appendChild(root);
            for (int k = 1; k <= studentName.length; k++) {
                Node node = document.createElement("学生");
                root.appendChild(node);
            }
            NodeList nodeList = document.getElementsByTagName("学生");
            int size = nodeList.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodeList.item(k);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    Node nodeName = document.createElement("姓名");
                    Node nodeNumber = document.createElement("学号");
                    nodeName.appendChild(document.createTextNode(studentName[k]));
                    nodeNumber.appendChild(document.createTextNode(studentNumber[k]));
                    elementNode.appendChild(nodeName);
                    elementNode.appendChild(nodeNumber);
                }
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File("student.xml");
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}