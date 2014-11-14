//http://ics3ui.sgrondin.ca/ss07/index.html
//https://github.com/MrGFHCI/2013094UISummatives/tree/master/PhilsBlackJack

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.GradientPaint;

public class FraGui4 extends JFrame {

    Color NewGreen = new Color(26, 134, 58);
    Color NewBlue = new Color(32, 0, 210);
    Color NewRed = new Color(255, 17, 23);
    private BlackjackHand playerHand;
    private JLabel[] arLblPlayer;
    private BlackjackHand dealerHand;
    private JLabel[] arLblDealer;
    private Deck deck;
    private JPanel panBoard;
    private JTextField txtMoney;
    private int nTotal = 1000;
    private int nBet = 0;
    private JPanel panBet;
    private JPanel panInput; // hit, stand, new, etc.
    private JPanel panStatus;
    private JLabel lblStatus; // status sMessage label
    private String sMessage = "Please place your bet.";
    private String sMoney;// Money update?
    private String sBet;
    private JLabel lblMoney;
    private JLabel lblBet;
    private JButton btnHit;
    private JButton btnStand;
    private JButton btnNewGame;
    private JButton btnBet;
    private final JRadioButton btn5 = new JRadioButton("$5");
    private final JRadioButton btn10 = new JRadioButton("$10");
    private final JRadioButton btn20 = new JRadioButton("$20");
    private final JRadioButton btn50 = new JRadioButton("$50");
    private final JRadioButton btn100 = new JRadioButton("$100");
    private boolean bInGame = false;


    public FraGui4() {
        init();
    }//constructor

    public void init() {
        //setSize(900, 400);
        //setLayout(new BorderLayout()); // JFrame layout
        lblStatus = new JLabel(sMessage);
        lblMoney = new JLabel(sMoney);
        lblBet = new JLabel(sBet);
        txtMoney = new JTextField("", 4);
        arLblPlayer = new JLabel[6];
        arLblDealer = new JLabel[6];
        panBoard = new JPanel(new GridLayout(2, 6)); // 2 players - up to 6 cards each
        panInput = new JPanel(); // defaults to FlowLayout
        panBet = new JPanel(new GridLayout(7, 1));
        panStatus = new JPanel();
        ButtonGroup betgroup = new ButtonGroup();
        add(panStatus, BorderLayout.NORTH);
        add(panBet, BorderLayout.EAST);
        add(panBoard, BorderLayout.CENTER);
        add(panInput, BorderLayout.SOUTH);
        Font font = new Font("Copperplate Gothic Bold", Font.PLAIN, 18);
        panBoard.setBackground(NewGreen);
        panBet.setBackground(NewGreen);
        panInput.setBackground(NewGreen);
        panStatus.setBackground(NewGreen);
        panStatus.add(lblStatus);
        lblStatus.setFont(font);
        lblStatus.setForeground(Color.white);

        for (int i = 0; i < 6; i++) {
            arLblPlayer[i] = new JLabel();
            arLblDealer[i] = new JLabel();
        }

        for (int i = 0; i < 6; i++) {
            panBoard.add(arLblDealer[i]);
        }

        for (int i = 0; i < 6; i++) {
            panBoard.add(arLblPlayer[i]);
        }
      
        String sName;
        sName = JOptionPane.showInputDialog("Enter Your Name");
        
        arLblDealer[0].setText("Dealer's Cards");
        arLblPlayer[0].setText(sName);
        
        btnHit = new JButton("Hit");
        btnHit.addActionListener(new HitActionListener());

        btnStand = new JButton("Stand");
        btnStand.addActionListener(new StandActionListener());

        btnNewGame = new JButton("New game");
        btnNewGame.addActionListener(new NewgameActionListener());

        btnBet = new JButton("Bet");
        btnBet.addActionListener(new BetActionListener());
        panInput.add(btnBet);
        btn5.setBackground(NewGreen);
        btn10.setBackground(NewGreen);
        btn20.setBackground(NewGreen);
        btn50.setBackground(NewGreen);
        btn100.setBackground(NewGreen);

        betgroup.add(btn5);
        betgroup.add(btn10);
        betgroup.add(btn20);
        betgroup.add(btn50);
        betgroup.add(btn100);
        panBet.add(lblMoney);
        panBet.add(lblBet);
        panBet.add(btn5);
        panBet.add(btn10);
        panBet.add(btn20);
        panBet.add(btn50);
        panBet.add(btn100);

        sBet = "0";
        sMoney = "Money: $" + nTotal;
        lblMoney.setText(sMoney);
        lblMoney.setFont(font);
        lblMoney.setForeground(Color.white);
    }

    public void newGame() {
        // initialize the deck and hands (or do this in a newGame method)

        bInGame = true;
        deck = new Deck();
        playerHand = new BlackjackHand();
        dealerHand = new BlackjackHand();
        deck.shuffle();
        //deals cards
        dealerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());

        if (dealerHand.getBlackjackValue() == 21) {
            sMessage = "Sorry, you lose.  Dealer has Blackjack.";
            //bInGame = false;
        } else if (playerHand.getBlackjackValue() == 21) {
            sMessage = "You win!  You have Blackjack.";
            //bInGame = false;
        } else {
            sMessage = "You must place a bet first.";
            bInGame = true;
        }
        redraw();
    }

    public void redraw() {
        // update playing area
        // 1) look at cards in playerHand and dealerHand and add/update labes in the "Grid"
        for (int i = 1; i < dealerHand.getCardCount() + 1; i++) {
            Card dealerCard = dealerHand.getCard(i - 1);
            arLblDealer[i].setIcon(new ImageIcon(GetFileName(dealerCard)));
        }
        for (int i = 1; i < playerHand.getCardCount() + 1; i++) {
            Card playerCard = playerHand.getCard(i - 1);
            arLblPlayer[i].setIcon(new ImageIcon(GetFileName(playerCard)));
        }

        if (bInGame) {
            arLblDealer[1].setIcon(new ImageIcon("back-red-75-1.png"));
        }

        // 2) update any status messages
        lblStatus.setText(sMessage);
        lblMoney.setText(sMoney);

    }

    public static String GetFileName(Card thisCard) {
        String sSuit = thisCard.getSuitAsString();
        String sValue = thisCard.getValueAsString();
        String FileName = sSuit + "-" + sValue + "-75.png";
        return FileName;
    }

    // actionListeners for buttons!
    class HitActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            {
                if (nBet == 0) {
                    sMessage = "Must place a bet first";
                    redraw();
                    return;
                }
                if (bInGame == false) {
                    sMessage = "Click \"New Game\" to start a new game.";
                    redraw();
                    return;
                }
                playerHand.addCard(deck.dealCard());
                if (playerHand.getBlackjackValue() > 21) {
                    sMessage = "You've busted!  Sorry, you lose.";
                    bInGame = false;
                    nTotal = nTotal - nBet;
                    nBet = 0;
                } else if (playerHand.getCardCount() == 5) {
                    sMessage = "You win by taking 5 cards without going over 21.";
                    bInGame = false;
                    nTotal = nTotal + nBet;
                    nBet = 0;
                } else {
                    sMessage = "Hit or Stand?";
                }
                redraw();
            }
        }
    }

    class StandActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (nBet == 0) {
                sMessage = "Must place a bet first";
                redraw();
                return;
            }
            if (bInGame == false) {
                sMessage = "Click \"New Game\" to start a new game.";
                redraw();
                return;
            }
            bInGame = false;
            while (dealerHand.getBlackjackValue() <= 16 && dealerHand.getCardCount() < 5) {
                dealerHand.addCard(deck.dealCard());
            }
            if (dealerHand.getBlackjackValue() > 21) {
                sMessage = "You win!  Dealer has busted with " + dealerHand.getBlackjackValue() + ".";
                nTotal = nTotal + nBet;
                nBet = 0;
            } else if (dealerHand.getCardCount() == 5) {
                sMessage = "Sorry, you lose.  Dealer took 5 cards without going over 21.";
                nTotal = nTotal - nBet;
                nBet = 0;
            } else if (dealerHand.getBlackjackValue() > playerHand.getBlackjackValue()) {
                sMessage = "Sorry, you lose, " + dealerHand.getBlackjackValue()
                        + " to " + playerHand.getBlackjackValue() + ".";
                nTotal = nTotal - nBet;
                nBet = 0;
            } else if (dealerHand.getBlackjackValue() == playerHand.getBlackjackValue()) {
                sMessage = "Sorry, you lose.  Dealer wins on a tie.";
                nTotal = nTotal - nBet;
                nBet = 0;
            } else {
                sMessage = "You win, " + playerHand.getBlackjackValue()
                        + " to " + dealerHand.getBlackjackValue() + "!";
                nTotal = nTotal + nBet;
                nBet = 0;

            }
            redraw();
        }
    }

    class NewgameActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (bInGame == true) {
                sMessage = "You still have to finish this game!";
                redraw();
                return;
            } else {
                btn5.setEnabled(true);
                btn10.setEnabled(true);
                btn20.setEnabled(true);
                btn50.setEnabled(true);
                btn100.setEnabled(true);
                panInput.remove(btnHit);
                panInput.remove(btnStand);
                panInput.remove(btnNewGame);
                txtMoney.setText("");
                sBet = " ";
                lblBet.setText(sBet);
                sMoney = "Money: $" + nTotal;
                lblMoney.setText(sMoney);
                sMessage = "Please place your bet";
                lblStatus.setText(sMessage);
                //clear board
                for (int i = 0; i < 6; i++) {
                    arLblPlayer[i].setIcon(null);
                    arLblDealer[i].setIcon(null);
                }

                // arLblDealer[0].setText("Dealer's Cards");
                //arLblPlayer[0].setText(sName);
            }
        }
    }

    class BetActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnBet) {
                if (btn5.isSelected() == true) {
                    nBet = 5;
                }
                if (btn10.isSelected() == true) {
                    nBet = 10;
                }
                if (btn20.isSelected() == true) {
                    nBet = 20;
                }
                if (btn50.isSelected() == true) {
                    nBet = 50;
                }
                if (btn100.isSelected() == true) {
                    nBet = 100;
                }
                if (nTotal == 0) {
                    sMessage = "Sorry game over, you're all out of money.";
                    lblStatus.setText(sMessage);
                    return;
                } else if (nBet > nTotal) {
                    sMessage = "Sorry insufficent funds";
                    lblStatus.setText(sMessage);
                } else {
                    newGame();
                    panInput.add(btnHit);
                    panInput.add(btnStand);
                    panInput.add(btnNewGame);
                    btn5.setEnabled(false);
                    btn10.setEnabled(false);
                    btn20.setEnabled(false);
                    btn50.setEnabled(false);
                    btn100.setEnabled(false);
                    lblBet.setText("Your bet is $" + nBet);
                    sMessage = "Hit or Stand?";
                    lblStatus.setText(sMessage);
                }
            }
        }
    }
}
