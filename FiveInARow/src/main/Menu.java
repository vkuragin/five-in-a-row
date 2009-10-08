/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *Создает панель меню.
 * @author kaligula
 */
public class Menu extends JMenuBar implements ActionListener,ItemListener{
        private GameFrame gameFrame;
        private JMenu mainMenu,helpMenu;
        private JMenuItem start, soundMenu,exit,help,about;
        private Sound sound;
        /**
         * Создает панель меню.
         * @param gameFrame ссылка на игровое поле.
         */
        public Menu(GameFrame gameFrame) {
                super();
                this.gameFrame=gameFrame;
                initMenu();
        }
        //Инициализация элементов меню.
        private void initMenu() {
                setFont(Helper.SMALL_FONT);                
                //Создаем главное меню.
                mainMenu=new JMenu("Main");                
                add(mainMenu);
                //Создаем меню помощи.
                helpMenu=new JMenu("Help");
                add(helpMenu);

                //Создаем элементы меню.
                start=new JMenuItem("New game");
                soundMenu=new JCheckBoxMenuItem("Sound");
                exit=new JMenuItem("Exit");
                about=new JMenuItem("About");
                help=new JMenuItem("Help");

                //Вешаем на них лисенеры.
                start.addActionListener(this);
                soundMenu.addActionListener(this);
                soundMenu.addItemListener(this);
                exit.addActionListener(this);
                about.addActionListener(this);
                help.addActionListener(this);

                //Настраиваем элементы меню.
                
                //Добавляем элементы в меню.
                mainMenu.add(start);
                mainMenu.add(soundMenu);
                mainMenu.addSeparator();
                mainMenu.add(exit);                
                helpMenu.add(help);
                helpMenu.add(about);
        }

        /**
         * Обработка событий меню.
         * @param e
         */
        public void actionPerformed(ActionEvent e) {
                //Название произошедшего события.
                String action=e.getActionCommand();
                //Выход из программы.
                if(action.equalsIgnoreCase("exit")) {
                        System.exit(0);
                }
                //Перезапуск игры.
                else if(action.equalsIgnoreCase("new game")) {
                                try {
                                        gameFrame.restartGame();
                                } catch (IOException ex) {
                                        new Error(this.gameFrame,"IOException within restartGame()");
                                }                        
                }                
        }

        public void itemStateChanged(ItemEvent e) {
                int state=e.getStateChange();
                Object menuItem=e.getItem();
                if(menuItem.equals(soundMenu)) {
                        if(state==ItemEvent.SELECTED) {
                                sound=new Sound(gameFrame, soundMenu); 
                                sound.startPlayback();
                        }
                        else {
                                
                                sound.stopPlayback();
                        }
                }
        }
}
