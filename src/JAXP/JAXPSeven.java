package JAXP;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;

/**
 * The type Jaxp seven.
 */
public class JAXPSeven {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domParser = factory.newDocumentBuilder();
            Document document = domParser.parse(new File("ch4_xml/example4_7.xml"));
            DocumentType doctype = document.getDoctype();
            String DTDName = doctype.getName();
            System.out.println("DTD名字：" + DTDName);
            String publicId = doctype.getPublicId();
            System.out.println("public标识：" + publicId);
            String systemId = doctype.getSystemId();
            System.out.println("system标识：" + systemId);
            String internalDTD = doctype.getInternalSubset();
            System.out.println("内部DTD：" + internalDTD);
        } catch (Exception e) {
        }
    }
}