package main;

import graGUI.GreyGUI;
import openGUI.loginGUI.LoginGUI;
import openGUI.registerGUI.RegisterGUI;

import javax.swing.*;

public class SwingConsole {
    private static LoginGUI loginGUI;
    private static RegisterGUI registerGUI;
    private static GreyGUI greyGUI;

    public static LoginGUI getLoginGUI() {
        return loginGUI;
    }

    public static RegisterGUI getRegisterGUI() {
        return registerGUI;
    }

    public static GreyGUI getGreyGUI() {
        return greyGUI;
    }

    public static void main(String []args) throws Exception {
        loginGUI    = new LoginGUI();
        registerGUI = new RegisterGUI();
        greyGUI = new GreyGUI();

        greyGUI.laterInitListener();

        SwingConsole.run(loginGUI, 400, 300);
        loginGUI.setVisible(true);
    }

    public static void run(final JFrame frame, final int width, final int height) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setTitle("灰色关联计算软件");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setVisible(false);
            }
        });
    }
}
