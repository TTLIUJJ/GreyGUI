package openGUI.loginGUI;

import graGUI.GBC;
import main.SwingConsole;
import openGUI.registerGUI.RegisterGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginGUI extends JFrame {
    private JLabel usernameLabel         = new JLabel("用户");
    private JLabel passwordLabel         = new JLabel("密码");
    private LoginButton loginButton      = new LoginButton("登录");
    private JButton registerButton       = new JButton("注册");
    private JTextField usernameTextField = loginButton.getUserNameTextField();
    private JTextField passwordTextField = loginButton.getPasswordTextField();

    public LoginGUI() {
        super();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        setLayout(new GridBagLayout());
        setBounds(300,100,400,300);
        initComponent();
    }

    private void initComponent() {
        JPanel firstPanel = new JPanel();
        firstPanel.add(usernameLabel);
        firstPanel.add(usernameTextField);
        add(firstPanel, new GBC(0, 0, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

        JPanel secondPanel = new JPanel();
        secondPanel.add(passwordLabel);
        secondPanel.add(passwordTextField);
        add(secondPanel, new GBC(0, 1, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

        add(loginButton,    new GBC(0, 3, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(registerButton, new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

        initListener();
    }


    private void initListener() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                RegisterGUI registerGUI = SwingConsole.getRegisterGUI();
                registerGUI.setVisiableForRegisterGUI();
            }
        });
    }

    public void setVisiableForLoginGUI() {
        setVisible(true);
    }
}
