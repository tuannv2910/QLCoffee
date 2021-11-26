/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.awt.Color;
import static java.awt.Color.pink;
import static java.awt.Color.white;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ADMIN
 */
public class CheckHelper {
    //check tên món ăn
    public static boolean checkTenMon(JTextField txt) {
        txt.setBackground(Color.white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,50}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.pink);
            Helper.DialogHelper.alert(txt.getRootPane(), txt.getText() + "Bạn nhập sai định dạng tên món ăn");
            return false;
        }
    }
    
    //check tiền
    public static boolean checkMoney(JTextField txt) {
        txt.setBackground(white);
        try {
            float hp = Float.parseFloat(txt.getText());
            if (hp >= 0) {
                return true;
            } else {
                txt.setBackground(pink);
                Helper.DialogHelper.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là lớn hơn hoặc bằng 0.");
                return false;
            }
        } catch (NumberFormatException e) {
            txt.setBackground(pink);
            Helper.DialogHelper.alert(txt.getRootPane(), txt.getName() + " giá tiền phải là số thực.");
            return false;
        }
    }
    
    
    //check trống dữ liệu trên form text
    public static boolean checkNullText(JTextField txt) {
        if (txt.getText().trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(null, "Không được để trống trường dữ liệu !");
            return false;
        }
    }

    //check trống dữ liệu trên pass
    public static boolean checkNulPass(JPasswordField txt) {
        txt.setBackground(white);
        String pass = new String(txt.getPassword());
        if (pass.trim().length() > 0) {
            return true;
        } else {
            txt.setBackground(pink);
            Helper.DialogHelper.alert(txt.getRootPane(), "Không được để trống trường dữ liệu password !");
            return false;
        }

    }

    public static boolean checkEmail(JTextField txt) {
        txt.setBackground(white);
        String email = txt.getText();
        String rgx = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
        if (email.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(null, "Email không đúng định dạng !");
            return false;
        }
    }
    
    //check định dạng của pass.
    public static boolean checkPass(JPasswordField txt) {
        txt.setBackground(white);
        if (txt.getPassword().length > 2 && txt.getPassword().length < 17) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "phải có từ 3-16 kí tự.");
            return false;
        }
    }
    
    //check độ dài của mã nhân viên.
    public static boolean checkMaNV(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "[a-zA-Z0-9]{1,15}";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(Color.red);
            JOptionPane.showMessageDialog(txt, "phải có 1-15 kí tự\nchữ hoa, thường không dấu hoặc số.");
            return false;
        }
    }
    
    public static boolean checkName(JTextField txt) {
        txt.setBackground(white);
        String id = txt.getText();
        String rgx = "^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ ]{3,25}$";
        if (id.matches(rgx)) {
            return true;
        } else {
            txt.setBackground(pink);
            JOptionPane.showMessageDialog(txt, "phải là tên tiếng việt hoặc không đấu\ntừ 3-25 kí tự");
            return false;
        }
        
    }
    
}
