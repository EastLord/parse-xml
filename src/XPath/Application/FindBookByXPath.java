package XPath.Application;

/**
 * Created by archer on 17-4-1.
 */

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindBookByXPath implements ActionListener {
    XPathFactory xPathFactory;
    XPath xPath;
    String positionPath;
    InputSource source;
    JTextField inputBookName, inputBookAuthor, inputBookISBN, inputPublish;
    JTextArea showResult;

    FindBookByXPath(JTextField inputBookName, JTextField inputBookAuthor,
                    JTextField inputBookISBN, JTextField inputPublish,
                    JTextArea showResult, String fileName) {
        this.inputBookName = inputBookName;
        this.inputBookAuthor = inputBookAuthor;
        this.inputBookISBN = inputBookISBN;
        this.inputPublish = inputPublish;
        this.showResult = showResult;
        xPathFactory = XPathFactory.newInstance();
        xPath = xPathFactory.newXPath();
        source = new InputSource(fileName);
        this.positionPath = positionPath;
    }

    public void actionPerformed(ActionEvent e) {
        showResult.setText(null);
        String bookName = inputBookName.getText().trim();
        System.out.println(bookName);
        String authorName = inputBookAuthor.getText().trim();
        String ID = inputBookISBN.getText().trim();
        String publish = inputPublish.getText().trim();
        String predicates = "[contains(名称,'" + bookName + "') and " +
                "contains(作者,'" + authorName + "') and " +
                "contains(ISBN,'" + ID + "') and " +
                "contains(出版社,'" + publish + "')]"; //谓词
        String path = "/图书列表/图书" + predicates + "/*";          //XPath路径表达式
        System.out.println(path);
        try {
            NodeList nodelist =
                    (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
            int size = nodelist.getLength();
            for (int k = 0; k < size; k++) {
                Node node = nodelist.item(k);
                String name = node.getNodeName();
                showResult.append(" " + name + ":");
                String content = node.getTextContent();
                showResult.append(content + "\n");
                if (name.startsWith("出版社"))
                    showResult.append("--------------------------\n");
            }
        } catch (Exception exp) {
            showResult.setText(null);
            showResult.append("异常:" + exp);
        }
    }
}