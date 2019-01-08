package openGUI.loginGUI;

import graGUI.GreyGUI;
import main.SwingConsole;
import openGUI.PasswordTextField;
import openGUI.UsernameTextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginButton extends JButton {
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

                System.out.println("in login GUI:" +
                        "\nusername: " + username +
                        "\npassword: " + password);

                try {
                    if (username.equals("admin") && password.equals("123456")) {
                        LoginGUI loginGUI = SwingConsole.getLoginGUI();
                        loginGUI.setVisible(false);

                        GreyGUI greyGUI = SwingConsole.getGreyGUI();
                        greyGUI.setVisible(true);
                    }
                } catch (NullPointerException nullE) {

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
