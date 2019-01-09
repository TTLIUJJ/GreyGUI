package openGUI.loginGUI;

import graGUI.GreyGUI;
import main.MysqlUtil;
import main.SwingConsole;
import openGUI.PasswordTextField;
import openGUI.UsernameTextField;
import openGUI.registerGUI.RegisterGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButton extends JButton {
    private MysqlUtil mysqlUtil = MysqlUtil.getInstance();
    private UsernameTextField usernameTextField;
    private PasswordTextField passwordTextField;

    private String username;
    private String password;


    public LoginButton (String name) {
        super(name);
        usernameTextField = new UsernameTextField();
        passwordTextField = new PasswordTextField();

        initListener();
    }

    private void initListener() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameTextField.getTextString();
                password = passwordTextField.getTextString();

//                System.out.println("in register GUI:" +
//                        "\nusername: " + username +
//                        "\npassword: " + password);

                try {
                    if (username == null || password == null || username.length() == 0 || password.length() == 0) {
                        JOptionPane.showConfirmDialog(null, "用户或者密码不能为空", "登录失败",JOptionPane.DEFAULT_OPTION);
                    }
                    else if (mysqlUtil.login(username, password)) {
                        LoginGUI loginGUI = SwingConsole.getLoginGUI();
                        loginGUI.setVisible(false);

                        GreyGUI greyGUI = SwingConsole.getGreyGUI();
                        greyGUI.setVisible(true);
                    }
                    else {
                        JOptionPane.showConfirmDialog(null, "用户或者密码输入错误", "登录失败",JOptionPane.DEFAULT_OPTION);
                    }
                } catch (NullPointerException nullE) {
                    JOptionPane.showConfirmDialog(null, "系统出现了未知错误", "登录失败",JOptionPane.DEFAULT_OPTION);
                }
            }
        });
    }

    public JTextField getUserNameTextField() {
        return usernameTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

}
