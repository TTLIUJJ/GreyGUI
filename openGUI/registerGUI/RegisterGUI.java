package openGUI.registerGUI;

import graGUI.GBC;
import main.SwingConsole;
import openGUI.loginGUI.LoginButton;
import openGUI.loginGUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterGUI extends JFrame {
    private JLabel usernameLabel          = new JLabel("    用户");
    private JLabel passwordLabel          = new JLabel("    密码");
    private JLabel againLabel             = new JLabel("再次输出");
    private LoginButton loginButton       = new LoginButton("登录");
    private RegisterButton registerButton = new RegisterButton("确认");
    private JTextField usernameTextField  = registerButton.getUserNameTextField();
    private JTextField passwordTextField  = registerButton.getPasswordTextField();
    private JTextField againTextField     = registerButton.getAgainTextField();

    public RegisterGUI() {
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

        JPanel thirdPanel = new JPanel();
        thirdPanel.add(againLabel);
        thirdPanel.add(againTextField);
        add(thirdPanel, new GBC(0, 2, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

        add(loginButton,    new GBC(0, 4, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));
        add(registerButton, new GBC(0, 5, 1, 1).setFill(GBC.BOTH).setWeight(0, 0));

        initListener();
    }

    private void initListener() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LoginGUI loginGUI = SwingConsole.getLoginGUI();
                loginGUI.setVisiableForLoginGUI();
            }
        });
    }

    public void setVisiableForRegisterGUI() {
        setVisible(true);
    }
}
