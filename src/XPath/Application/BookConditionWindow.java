package XPath.Application;

/**
 * Created by archer on 17-4-1.
 */

import javax.swing.*;
import java.awt.*;

public class BookConditionWindow extends JFrame {
    JTextField inputBookName, inputBookAuthor, inputBookISBN, inputPublish;
    JTextArea showResult;
    JButton button;
    Box baseBox, boxV1, boxV2;

    BookConditionWindow() {
        inputBookName = new JTextField(10);
        inputBookAuthor = new JTextField(10);
        inputBookISBN = new JTextField(10);
        inputPublish = new JTextField(10);
        boxV1 = Box.createVerticalBox();
        boxV1.add(new Label("图书名称中包含:"));
        boxV1.add(new Label("作者姓名中包含:"));
        boxV1.add(new Label("图书ISBN中包含"));
        boxV1.add(new Label("出版社名称中包含:"));
        boxV2 = Box.createVerticalBox();
        boxV2.add(inputBookName);
        boxV2.add(inputBookAuthor);
        boxV2.add(inputBookISBN);
        boxV2.add(inputPublish);
        baseBox = Box.createHorizontalBox();
        baseBox.add(boxV1);
        baseBox.add(boxV2);
        JPanel west = new JPanel();
        west.add(baseBox);
        button = new JButton("确定");
        west.add(button);
        add(west, BorderLayout.WEST);
        showResult = new JTextArea(10, 10);
        showResult.setFont(new Font("宋体", Font.PLAIN, 12));
        add(new JScrollPane(showResult), BorderLayout.CENTER);
        FindBookByXPath findBook;     //负责使用XPath查询图书的对象
        findBook =
                new FindBookByXPath(inputBookName, inputBookAuthor, inputBookISBN, inputPublish,
                        showResult, "ch6_xml/book.xml");
        button.addActionListener(findBook);
        setBounds(10, 10, 900, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}