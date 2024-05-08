package PUZZLE;

import Data.Player;
import Logic.Sound;
import Data.Ranking;
import Data.DatabaseConnection;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.sql.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import Data.Game;

public class menu extends javax.swing.JFrame {
    private Connection conn;
    private Game game;
    private Player play;
    private CardLayout card= new CardLayout();
    private JPanel cardPanel;
    private Ranking rank;
    private Sound soundInstance;
    

    public menu() throws UnsupportedAudioFileException, IOException, LineUnavailableException  {        
        initComponents();      
        conn = DatabaseConnection.getConnection();
        play = new Player();
        game = new Game(conn,play);
        soundInstance = Sound.getInstance();
        this.setLocationRelativeTo(null);
        jLabel1.setLayout(null);      
        cardPanel = new JPanel();
        rank=new Ranking(conn,jTable1);
        cardPanel.setLayout(new CardLayout());
        getContentPane().add(cardPanel, "cardPanel");
        cardPanel.add(jLabel1, "menuCard");
        cardPanel.add(jPanel1, "rankingCard");
        jLabel1.add(jLabel2);
        jLabel1.add(jLabel3);
        jLabel1.add(jSound);
        jLabel2.setBounds(180,220, 150, 30);
        jLabel3.setBounds(180, 270, 150, 30);
        jSound.setBounds(10, 20, 70, 70);
        setVisible(true);
        soundInstance.playMusicLoop();
    }
    
    public void showMenu(){
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSound = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PUZZLE/img/play.png"))); // NOI18N
        jLabel2.setText("CHƠI");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 30)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PUZZLE/img/trophy.png"))); // NOI18N
        jLabel3.setText("BXH");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        jSound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PUZZLE/img/megaphone.png"))); // NOI18N
        jSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSoundMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jSoundMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jSoundMouseExited(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PUZZLE/img/menu.jpg"))); // NOI18N
        getContentPane().add(jLabel1, "card2");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PUZZLE/img/logout.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        Login login = new Login(game,play);
        if(!login.getOnGame()){
            showMenu();
        }
        if(game.getPlayGame()){
            showMenu();
        }
        if(game.getPlayGame()){
            this.hide();
            game.startGame(this);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "rankingCard");
        rank.updateRankingTable();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "menuCard");    
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jSoundMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSoundMouseEntered

    }//GEN-LAST:event_jSoundMouseEntered

    private void jSoundMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSoundMouseExited

    }//GEN-LAST:event_jSoundMouseExited

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
        jLabel2.setForeground(Color.RED);
        jLabel2.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setForeground(Color.BLACK);
        jLabel2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel2MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setForeground(Color.YELLOW);
        jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        jLabel3.setForeground(Color.BLACK);
        jLabel3.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jSoundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSoundMouseClicked
        if (Sound.getInstance().isSoundOn()) {
            Sound.getInstance().toggleSound();
        } else {
            Sound.getInstance().toggleSound();
        }
    }//GEN-LAST:event_jSoundMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jSound;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
