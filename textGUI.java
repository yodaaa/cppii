import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import java.util.*;
import java.io.*;

public class textGUI extends JFrame implements ActionListener {
    Frame frm = new Frame("Kitty on your lap");
    FileDialog fd;
    public static void main(String[] args) {
      new textGUI();
      File newfile = new File("sample.txt");
      try {
        newfile.createNewFile();
      }
      catch (IOException e) {
        System.out.println(e);
      }
    }
    JTextField textFiled1 = new JTextField(42);
    JTextField textFiled2 = new JTextField("", 10);
    String[] combodata = {"nop", "ldi", "opr1", "opr2", "print", "read", "jump", "cjump", "store", "halt", "del", "prec", "call", "dclv", "rtrn", "stof", "lodf"};
    String[] opr2 = {"add", "sub", "mul", "dvd", "mod", "less", "lsep", "gtep", "gtr", "equ", "ntep"};
    JComboBox comboBox1 = new JComboBox(combodata);
    JComboBox comboBox2 = new JComboBox();
    JComboBox comboBox3 = new JComboBox();
    JTextArea area1 = new JTextArea(10, 20);
    JButton button = new JButton("追加");
    JButton button1 = new JButton("関数名追加");
    JButton button2 = new JButton("保存");
    JButton button3 = new JButton("読み込み");
    ArrayList  list = new ArrayList<String>();
    textGUI() {
      button.addActionListener(this);
      button1.addActionListener(this);
      button2.addActionListener(this);
      button3.addActionListener(this);
      comboBox1.addActionListener(this);
      getContentPane().setLayout(new FlowLayout());
      JPanel p = new JPanel();
      getContentPane().add(textFiled1);
      getContentPane().add(button1);
      getContentPane().add(comboBox3);
      getContentPane().add(comboBox1);
      getContentPane().add(comboBox2);
      p.add(area1);
      getContentPane().add(button);
      getContentPane().add(button3);
      getContentPane().add(button2);
      Container contentPane = getContentPane();
      comboBox2.addItem("");
      comboBox3.addItem("");
      contentPane.add(p, BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 300);
      setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
      if (comboBox1.getSelectedIndex() != -1 && e.getSource() != button && e.getSource() != button1 && e.getSource() != button2 && e.getSource() != button3) {
        String checkcombo = (String)comboBox1.getSelectedItem();
        comboBox2.removeAllItems();
        if (checkcombo == combodata[2]) {
          comboBox2.addItem("_");
          comboBox2.addItem("not");
        }
        else if (checkcombo == combodata[3]) {
          for (int i = 0; i < 11; i++) {
            comboBox2.addItem(opr2[i]);
          }
        }
        else {
          comboBox2.addItem("_");
        }
        for (int i = 0; i < list.size(); i++) {
          comboBox2.addItem(list.get(i));
        }
      }
      if (e.getSource() == button1) {
        String str = textFiled1.getText();
        textFiled1.setText("");
        list.add(str);
        comboBox2.addItem(str);
        comboBox3.addItem(str);
      }
      else if (e.getSource() == button2) {
        fd = new FileDialog(frm , "保存フォルダ" , FileDialog.SAVE);
        fd.setVisible(true);
        String dir = fd.getDirectory(); //ディレクトリーの取得
        String fileName = fd.getFile();
        getContentPane().add(area1);
        try {
          FileWriter fw = new FileWriter(dir + fileName);
          area1.write(fw);
        }
        catch (IOException ioe) {
          System.out.println(ioe);
        }
      }
      else if (e.getSource() == button3) {
        fd = new FileDialog(frm , "読み込みフォルダ" , FileDialog.LOAD);
        fd.setVisible(true);
        String dir = fd.getDirectory(); //ディレクトリーの取得
        String fileName = fd.getFile();
        if (fileName == null)  System.exit(0); //ファイル名の設定が無ければ処理中止
        //指定したファイルからデータを読み取り表示
        try { //ファイルをか使うには、例外処理が必要
          String s;//読み込んだデータを保持する文字列
          FileReader rd = new FileReader(dir + fileName); //読み取り用として、ファイルとアプリを繋ぐ
          BufferedReader br = new BufferedReader(rd); //BufferdReaderの作成
          s = br.readLine(); //最初の１行を読み込む
          while (s != null) {
            area1.append(s + "\n"); //読み込んだ文字列をラベルで表示
            s = br.readLine(); //次の１行を読む
          }
          br.close();//閉じる
          rd.close();
        } catch (IOException ie) {
          //エラーが発生したら　エラーを表示
          System.out.println("Err=" + ie);
        }
      }
      else if (e.getSource() == button) {
        if (comboBox3.getSelectedItem() != "") {
          area1.append(comboBox3.getSelectedItem() + ":");
        }
        area1.append(comboBox1.getSelectedItem() + "," + comboBox2.getSelectedItem() + "\n");
        comboBox2.removeAllItems();
      }
    }
}
