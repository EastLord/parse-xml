package test;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by archer on 17-4-6.
 */
public class testXPath {
    public static void main(String args[]) {
        XPathWindow win = new XPathWindow("src/test/test_xml/test.xml");
        /**
         *  输出“图书质量”大于8的图书名称(三种实现方式)
         *  1   /邱田东/喜欢的图书列表/图书/图书评价[@图书质量>8]/preceding-sibling::*[2]
         *  2   /邱田东/喜欢的图书列表/图书/图书名[following-sibling::*[2][@图书质量>8]]
         *  3   /邱田东/喜欢的图书列表/图书/图书评价[@图书质量>8]/../图书名
         */
        /**
         *  输出“心理学1”类图书名字(三种实现方式)
         *  1   /邱田东/喜欢的图书列表/图书/图书种类[text()='心理学1']/preceding-sibling::*[1]
         *  2   /邱田东/喜欢的图书列表/图书/图书名[following-sibling::*[1][text()='心理学1']]
         *  3   /邱田东/喜欢的图书列表/图书/图书种类[text()='心理学1']/../图书名
         */
        /**
         *  输出“阅读时间”含有4的图书名字(三种实现方式)
         *  1   /邱田东/喜欢的图书列表/图书/阅读时间[contains(text(),'4')]/preceding-sibling::*[3]
         *  2   /邱田东/喜欢的图书列表/图书/图书名[following-sibling::*[3][contains(text(),'4')]]
         *  3   /邱田东/喜欢的图书列表/图书/阅读时间[contains(text(),'4')]/../图书名
         */
    }
}
class XPathWindow extends JFrame implements ActionListener {
    XPathFactory xPathFactory;
    XPath xPath;
    InputSource source;
    JTextField inputXPath;
    JTextArea showResult;
    JButton button;

    public XPathWindow(String fileName) {
        try {
            xPathFactory = XPathFactory.newInstance();
            xPath = xPathFactory.newXPath();
            source = new InputSource(fileName);
            inputXPath = new JTextField(30);
            showResult = new JTextArea();
            button = new JButton("确定");
            button.addActionListener(this);
            inputXPath.addActionListener(this);
            JPanel north = new JPanel();
            north.add(new Label("XPath表达式:"));
            north.add(inputXPath);
            north.add(button);
            add(north, BorderLayout.NORTH);
            add(new JScrollPane(showResult), BorderLayout.CENTER);
            setBounds(500, 300, 500, 300);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception exp) {
            System.out.println(exp);
        }
    }

    public void actionPerformed(ActionEvent e) {
        showResult.setText(null);
        String path = inputXPath.getText();
        try {
            NodeList nodelist =
                    (NodeList) xPath.evaluate(path, source, XPathConstants.NODESET);
            int size = nodelist.getLength();
            showResult.append("节点集中的节点个数:" + size + "\n");
            showResult.append("节点的名字以及节点的值依次为:\n");
            for (int k = 0; k < size; k++) {
                Node node = nodelist.item(k);
                String name = node.getNodeName();
                showResult.append("第" + (k + 1) + "个节点的名字:" + name + ",");
                String value = node.getTextContent();
                showResult.append("第" + (k + 1) + "个节点的值:" + value + "\n");
            }
        } catch (Exception exp) {
            showResult.setText(null);
            showResult.append("异常:" + exp);
        }
    }
}