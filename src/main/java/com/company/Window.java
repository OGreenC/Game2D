package com.company;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(){
        setTitle("GameV2");
        setContentPane(new Game(1280,720));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


}
