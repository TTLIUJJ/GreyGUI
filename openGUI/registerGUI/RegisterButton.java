package openGUI.registerGUI;

import main.SwingConsole;
import openGUI.PasswordTextField;
import openGUI.UsernameTextField;
import openGUI.loginGUI.LoginGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterButton extends JButton {
    private UsernameTextField usernameTextField;
    private PasswordTextField passwordTextField;
    private PasswordTextField againTextField;

    private String username;
    private String password;
    private String again;


    public RegisterButton (String name) {
        super(name);
        usernameTextField = new UsernameTextField();
        passwordTextField = new PasswordTextField();
        againTextField    = new PasswordTextField();

        initListener();
    }

    private void initListener() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameTextField.getTextString();
                password = passwordTextField.getTextString();
                again    = againTextField.getTextString();

                System.out.println("in register GUI:" +
                        "\nusername: " + username +
                        "\npassword: " + password +
                        "\nagain   : " + again);

                try {
                    if (username == null || password == null || again == null) {
                        JOptionPane.showConfirmDialog(null, "用户名称或者密码不能为空", "注册失败",JOptionPane.DEFAULT_OPTION);
                    }
                    else if (username.equals("admin")) {
                        JOptionPane.showConfirmDialog(null, "用户名称已存在", "注册失败",JOptionPane.DEFAULT_OPTION);
                    }
                    else if (password.length() < 6) {
                        JOptionPane.showConfirmDialog(null, "密码至少需要6位", "注册失败",JOptionPane.DEFAULT_OPTION);
                    }
                    else if(!password.equals(again)) {
                        JOptionPane.showConfirmDialog(null, "两次输入的密码不相同", "注册失败",JOptionPane.DEFAULT_OPTION);
                    }
                    else {
                        JOptionPane.showConfirmDialog(null, "注册成功！请重新登录", "注册成功",JOptionPane.DEFAULT_OPTION);

                        RegisterGUI registerGUI = SwingConsole.getRegisterGUI();
                        registerGUI.setVisible(false);

                        LoginGUI loginGUI = SwingConsole.getLoginGUI();
                        loginGUI.setVisible(true);
                    }
                } catch (Exception e1) {
                    JOptionPane.showConfirmDialog(null, "系统出现了未知错误", "注册失败",JOptionPane.DEFAULT_OPTION);
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

    public JTextField getAgainTextField() {
        return againTextField;
    }

}
