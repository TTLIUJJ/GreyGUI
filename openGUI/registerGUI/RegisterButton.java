package openGUI.registerGUI;

import openGUI.PasswordTextField;
import openGUI.UsernameTextField;

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
