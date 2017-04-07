package shiyan;

/**
 * Created by archer on 17-4-7.
 */

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
 * The type shiyan1_1.
 */
public class shiyan1_1 {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        try {
            String[] bookName = {"幽默大全", "时事评论", "感动中国"};
            String[] bookVal = {"轻松幽默", "犀利独到", "耐人寻味"};
            String[] bookScore = {"90","80","95"};
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domPaser = factory.newDocumentBuilder();
            Document document = domPaser.newDocument();
            document.setXmlVersion("1.0");
            Element root = document.createElement("喜欢的图书列表");
            document.appendChild(root);
            for (int k = 1; k <= bookName.length; k++) {
                Node node = document.createElement("图书");
                root.appendChild(node);
            }
            NodeList nodeList = document.getElementsByTagName("图书");
            int size = nodeList.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodeList.item(k);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementNode = (Element) node;
                    Element nodeName = document.createElement("图书名");
                    Element nodeVal = document.createElement("图书评价");
                    nodeVal.setAttribute("图书质量",bookScore[k]);
                    nodeName.appendChild(document.createTextNode(bookName[k]));
                    nodeVal.appendChild(document.createTextNode(bookVal[k]));
                    elementNode.appendChild(nodeName);
                    elementNode.appendChild(nodeVal);
                }
            }
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            File file = new File("src/shiyan/shiyan_xml/book.xml");
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transformer.transform(domSource, xmlResult);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}