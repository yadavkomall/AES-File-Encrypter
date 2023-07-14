
import javax.swing.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.awt.FlowLayout;
import java.io.*;
import java.security.Key;

public class Encryption {

    public static void encrypt(byte[] key, JFileChooser fc) {

        File f = fc.getSelectedFile();
        //read 
        try {
            FileInputStream fs = new FileInputStream(f);
            byte[] data = new byte[fs.available()];
            fs.read(data);

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data);

            FileOutputStream fo = new FileOutputStream(f);
            fo.write(encryptedData);
            fo.close();
            JOptionPane.showMessageDialog(null, "Encryption Done!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Encryption failed");

        }
    }

    public static void decrypt(byte[] key, JFileChooser fc) {
        File f = fc.getSelectedFile();
        //read 
        try {
            FileInputStream fs = new FileInputStream(f);
            byte[] data = new byte[fs.available()];
            fs.read(data);

            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedData = cipher.doFinal(data);

            FileOutputStream fo = new FileOutputStream(f);
            fo.write(decryptedData);
            fo.close();
            JOptionPane.showMessageDialog(null, "Decryption Done!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Decryption Failed!");

        }

    }

    public static void main(String args[]) {

        JFrame f = new JFrame();
        f.setTitle("File encryption decryption");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //label
        JLabel label = new JLabel("Enter Key");

        //text field
        JTextField tf = new JTextField(10);

        //button
        JButton bt = new JButton();
        bt.setText("Select File");
        //file selection
        JFileChooser fc = new JFileChooser();
        bt.addActionListener(e -> {
            fc.showOpenDialog(null);
        });

        //encrypt button
        JButton bt2 = new JButton();
        bt2.setText("Encrypt File");
        bt2.addActionListener(e -> {
            String text = tf.getText();
            byte[] key = text.getBytes(); // Convert key to byte array
            encrypt(key, fc);
        });

        //decrypt button
        JButton bt3 = new JButton();
        bt3.setText("Decrypt File");
        bt3.addActionListener(e -> {
            String text = tf.getText();
            byte[] key = text.getBytes(); // Convert key to byte array
            decrypt(key, fc);
        });
        f.setLayout(new FlowLayout());
        f.add(label);
        f.add(tf);
        f.add(bt);
        f.add(bt2);
        f.add(bt3);
        f.setVisible(true);
    }
}
