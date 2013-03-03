package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: rain
 * Date: 2/26/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServerUI extends JFrame{
    private JPanel server;
    private JButton exitButton;
    public JButton restartButton;
    private JList list;
    private Vector<Vector> clients;

    public ServerUI(String title) throws HeadlessException {
        super(title);
        init();
        setListener();

        //显示组和Ip
        clients = new Vector<Vector>();
    }

    public void init(){
        this.setContentPane(server);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public void setListener(){
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                ServerUI.this.dispose();
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                restart();
            }
        });
    }

    public boolean restart(){

        ServerUI.this.dispose();
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        new Server();
        return true;
    }

    //client contains group and ip
    public JList refleshClientShow(Vector client){

        clients.add(client);
        list.setListData(clients);
        return list;
    }

}
