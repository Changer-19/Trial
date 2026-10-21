//Name: Neel Patel
//Date: 29, October, 2026
//Purpose: A two-player racing dice game app where players take turns rolling two dice, adding the sum to their score, and racing to be the first to reach 100 points.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class DiceApp extends JPanel implements ActionListener
{
    //For screens
    Panel p_card;
    Panel card1, card2, card3, card4, card5;
    CardLayout cdLayout = new CardLayout ();

    //Game screen components
    JLabel turnLabel;
    JLabel titleBannerLabel;
    JLabel a, b;  
    JButton rollButton;
    int dicea = 1, diceb = 2;
    char turn = '1'; // '1' for Player 1, '2' for Player 2
    JTextField name, name2;
    String player1Name = "Player 1";
    String player2Name = "Player 2";
    JLabel playerAScore, playerBScore;
    int scoreA = 0, scoreB = 0;

    // Colors (Cyber Theme)
    Color backgroundColour = new Color (15, 15, 30); // Deep cyber dark navy
    Color buttonColour = new Color (35, 35, 55);     // Dark futuristic slate
    Color buttonText = new Color (0, 255, 255);      // Neon Cyan
    Color titleColour = new Color (255, 0, 127);     // Neon Magenta/Pink
    Color textColour = new Color (220, 220, 245);    // Cyber light text
    Font titleFont = new Font ("Arial", Font.BOLD, 26);
    Font promptFont = new Font ("Arial", Font.BOLD, 18);
    Dimension boardSquare = new Dimension (65, 65);

    public DiceApp ()
    {
        p_card = new Panel ();
        p_card.setLayout (cdLayout);
        // add all card panels to set up the screen navigation flow
        opening ();
        instructions ();
        settings ();
        gameScreen ();
           
        setLayout (new BorderLayout ());
        add ("Center", p_card);
    }

    // method to format fallback buttons and avoid code repetition
    public void formatGameButtons (JButton button, String ac)
    {
        button.setActionCommand (ac);
        button.addActionListener (this);
        button.setBackground (buttonColour);
        button.setForeground (buttonText);
        button.setFocusPainted (false);
    }

    public void opening ()
    { // Sets up the opening splash screen using opening.png as a clickable image button with a null border[cite: 9, 10]
        card1 = new Panel (new BorderLayout ());
        card1.setBackground (backgroundColour);
        
        ImageIcon openingIcon = createImageIcon ("opening.png");
        JButton next;
        if (openingIcon != null)
        {
            Image img = openingIcon.getImage ().getScaledInstance (375, 500, Image.SCALE_SMOOTH);
            next = new JButton (new ImageIcon (img));
        }
        else
        {
            next = new JButton ("Enter Game");
            formatGameButtons (next, "2");
        }
        
        next.setActionCommand ("2");
        next.addActionListener (this);
        next.setBorder (null);
        next.setContentAreaFilled (false);
        card1.add ("Center", next);
        p_card.add ("1", card1);
    }

    public void instructions ()
    { // Builds the instructions screen using instructions.png as a clickable image button[cite: 10]
        card2 = new Panel (new BorderLayout ());
        card2.setBackground (backgroundColour);
        
        ImageIcon instructIcon = createImageIcon ("instructions.png");
        JButton next;
        if (instructIcon != null)
        {
            Image img = instructIcon.getImage ().getScaledInstance (375, 500, Image.SCALE_SMOOTH);
            next = new JButton (new ImageIcon (img));
        }
        else
        {
            next = new JButton ("To Settings");
            formatGameButtons (next, "3");
        }
        
        next.setActionCommand ("3");
        next.addActionListener (this);
        next.setBorder (null);
        next.setContentAreaFilled (false);
        card2.add ("Center", next);
        p_card.add ("2", card2);
    }

    public void settings ()
    { // Sets up the settings screen using a clean solid background color where both players input their custom names
        card3 = new Panel ();
        card3.setBackground (backgroundColour);
        JLabel title = new JLabel ("Choose your settings:");
        title.setFont (titleFont);
        title.setForeground (titleColour);
        
        Panel p = new Panel ();
        p.setBackground (backgroundColour);
        JLabel namePmt = new JLabel ("Player 1 name:");
        namePmt.setFont (promptFont);
        namePmt.setForeground (textColour);
        name = new JTextField (10);
        name.setFont (promptFont);
        name.setBackground (buttonColour);
        name.setForeground (buttonText);
        name.setCaretColor (buttonText);
        
        Panel p2 = new Panel ();
        p2.setBackground (backgroundColour);
        JLabel choicePmt = new JLabel ("Player 2 name:");
        choicePmt.setFont (promptFont);
        choicePmt.setForeground (textColour);
        name2 = new JTextField (10);
        name2.setFont (promptFont);
        name2.setBackground (buttonColour);
        name2.setForeground (buttonText);
        name2.setCaretColor (buttonText);
        
        JButton entrance = new JButton ("To the game");
        entrance.setPreferredSize (new Dimension (300, 50));
        formatGameButtons (entrance, "getNames");

        card3.add (title);
        p.add (namePmt);
        p.add (name);
        p2.add (choicePmt);
        p2.add (name2);
        card3.add (p);
        card3.add (p2);
        card3.add (entrance);
        p_card.add ("3", card3);
    }

    public void gameScreen ()
    { // Constructs the main gameplay interface using image banners and rectangular image-based buttons
        card4 = new Panel (new BorderLayout (0, 8));
        card4.setBackground (backgroundColour);

        // Top header panel containing the image banner title and picture placeholder
        Panel topPanel = new Panel (new FlowLayout (FlowLayout.CENTER, 8, 8));
        topPanel.setBackground (backgroundColour);
        
        titleBannerLabel = new JLabel ();
        ImageIcon titleIcon = createImageIcon ("title_banner.png");
        if (titleIcon != null)
        {
            Image img = titleIcon.getImage ().getScaledInstance (240, 45, Image.SCALE_SMOOTH);
            titleBannerLabel.setIcon (new ImageIcon (img));
        }
        else
        {
            titleBannerLabel.setText ("FIRST TO 100 WIN!!!");
            titleBannerLabel.setFont (titleFont);
            titleBannerLabel.setForeground (titleColour);
        }
        topPanel.add (titleBannerLabel);

        JLabel pictureLabel = new JLabel ();
        ImageIcon picIcon = createImageIcon ("picture.png");
        if (picIcon != null)
        {
            Image img = picIcon.getImage ().getScaledInstance (45, 45, Image.SCALE_SMOOTH);
            pictureLabel.setIcon (new ImageIcon (img));
            topPanel.add (pictureLabel);
        }

        // Center content panel with balanced vertical spacing
        Panel centerPanel = new Panel (new GridLayout (4, 1, 0, 8));
        centerPanel.setBackground (backgroundColour);

        // Turn indicator using rectangular image form (sky blue for P1, baby pink for P2)
        Panel turnPanel = new Panel ();
        turnPanel.setBackground (backgroundColour);
        turnLabel = new JLabel ();
        ImageIcon turnIcon = createImageIcon (turn == '1' ? "turn_p1.png" : "turn_p2.png");
        if (turnIcon != null)
        {
            Image img = turnIcon.getImage ().getScaledInstance (200, 35, Image.SCALE_SMOOTH);
            turnLabel.setIcon (new ImageIcon (img));
        }
        else
        {
            turnLabel.setText ((turn == '1' ? player1Name : player2Name) + "'s Turn");
            turnLabel.setFont (promptFont);
            turnLabel.setForeground (buttonText);
        }
        turnPanel.add (turnLabel);
        centerPanel.add (turnPanel);

        // Panel to display both dice side by side
        Panel dicePanel = new Panel (new FlowLayout (FlowLayout.CENTER, 15, 0));
        dicePanel.setBackground (backgroundColour);
        a = new JLabel (createImageIcon ("d" + dicea + ".png"));
        a.setPreferredSize (boardSquare);
        dicePanel.add (a);
        b = new JLabel (createImageIcon ("d" + diceb + ".png"));
        b.setPreferredSize (boardSquare);
        dicePanel.add (b);
        centerPanel.add (dicePanel);

        // Panel to display text-based score readouts for players
        Panel scorePanel = new Panel (new GridLayout (2, 1, 0, 2));
        scorePanel.setBackground (backgroundColour);

        playerAScore = new JLabel (player1Name + " score: " + scoreA, JLabel.CENTER);
        playerAScore.setFont (new Font ("Arial", Font.BOLD, 15));
        playerAScore.setForeground (textColour);

        playerBScore = new JLabel (player2Name + " score: " + scoreB, JLabel.CENTER);
        playerBScore.setFont (new Font ("Arial", Font.BOLD, 15));
        playerBScore.setForeground (textColour);

        scorePanel.add (playerAScore);
        scorePanel.add (playerBScore);
        centerPanel.add (scorePanel);

        // Rectangular Roll Button configuration
        Panel rollPanel = new Panel ();
        rollPanel.setBackground (backgroundColour);
        ImageIcon rollBtnIcon = createImageIcon ("roll_btn.png");
        if (rollBtnIcon != null)
        {
            Image img = rollBtnIcon.getImage ().getScaledInstance (160, 34, Image.SCALE_SMOOTH);
            rollButton = new JButton (new ImageIcon (img));
            rollButton.setBorder (null);
            rollButton.setContentAreaFilled (false);
        }
        else
        {
            rollButton = new JButton ("Roll Both Dice");
            formatGameButtons (rollButton, "roll");
        }
        rollButton.setActionCommand ("roll");
        rollButton.addActionListener (this);
        rollButton.setPreferredSize (new Dimension (160, 34));
        rollPanel.add (rollButton);
        centerPanel.add (rollPanel);

        // Bottom panel containing rectangular Again, Instructions, and Settings buttons
        Panel bottomPanel = new Panel (new FlowLayout (FlowLayout.CENTER, 6, 6));
        bottomPanel.setBackground (backgroundColour);
        
        // Again / Reset Button (95x30 Rectangle)
        ImageIcon againIcon = createImageIcon ("again_btn.png");
        JButton reset;
        if (againIcon != null)
        {
            Image img = againIcon.getImage ().getScaledInstance (95, 30, Image.SCALE_SMOOTH);
            reset = new JButton (new ImageIcon (img));
            reset.setBorder (null);
            reset.setContentAreaFilled (false);
        }
        else
        {
            reset = new JButton ("Again");
            formatGameButtons (reset, "reset");
        }
        reset.setActionCommand ("reset");
        reset.addActionListener (this);
        reset.setPreferredSize (new Dimension (95, 30));
        bottomPanel.add (reset);

        // Instructions Button (110x30 Rectangle)
        ImageIcon instructBtnIcon = createImageIcon ("instruct_btn.png");
        JButton instruct;
        if (instructBtnIcon != null)
        {
            Image img = instructBtnIcon.getImage ().getScaledInstance (110, 30, Image.SCALE_SMOOTH);
            instruct = new JButton (new ImageIcon (img));
            instruct.setBorder (null);
            instruct.setContentAreaFilled (false);
        }
        else
        {
            instruct = new JButton ("Instructions");
            formatGameButtons (instruct, "instruct");
        }
        instruct.setActionCommand ("instruct");
        instruct.addActionListener (this);
        instruct.setPreferredSize (new Dimension (110, 30));
        bottomPanel.add (instruct);

        // Settings Button (95x30 Rectangle)
        ImageIcon settingsBtnIcon = createImageIcon ("settings_btn.png");
        JButton settings;
        if (settingsBtnIcon != null)
        {
            Image img = settingsBtnIcon.getImage ().getScaledInstance (95, 30, Image.SCALE_SMOOTH);
            settings = new JButton (new ImageIcon (img));
            settings.setBorder (null);
            settings.setContentAreaFilled (false);
        }
        else
        {
            settings = new JButton ("Settings");
            formatGameButtons (settings, "settings");
        }
        settings.setActionCommand ("settings");
        settings.addActionListener (this);
        settings.setPreferredSize (new Dimension (95, 30));
        bottomPanel.add (settings);

        // Assemble everything into card4 using BorderLayout
        card4.add ("North", topPanel);
        card4.add ("Center", centerPanel);
        card4.add ("South", bottomPanel);
        p_card.add ("4", card4);
    }

    // Win screen fully covered by win_p1.png or win_p2.png acting as a 375x500 clickable image button
    public void winScreen (String winnerName)
    {
        card5 = new Panel (new BorderLayout ());
        card5.setBackground (backgroundColour);
        
        String winImgName = winnerName.equals (player1Name) ? "win_p1.png" : "win_p2.png";
        ImageIcon winIcon = createImageIcon (winImgName);
        JButton winButton;
        
        if (winIcon != null)
        {
            Image img = winIcon.getImage ().getScaledInstance (375, 500, Image.SCALE_SMOOTH);
            winButton = new JButton (new ImageIcon (img));
        }
        else
        {
            winButton = new JButton (winnerName + " Wins! Click to Play Again");
            formatGameButtons (winButton, "reset");
        }
        
        winButton.setActionCommand ("reset");
        winButton.addActionListener (this);
        winButton.setBorder (null);
        winButton.setContentAreaFilled (false);
        
        card5.add ("Center", winButton);
        p_card.add ("5", card5);
    }

    public void winMethod (String winnerName)
    {
        winScreen (winnerName);
        cdLayout.show (p_card, "5");
    }

    public void reset_method ()
    {
        scoreA = 0;
        scoreB = 0;
        turn = '1';
        if (rollButton != null)
        {
            rollButton.setEnabled (true);
        }
        gameScreen ();
        p_card.revalidate ();
        p_card.repaint ();
        cdLayout.show (p_card, "4");
    }

    public void actionPerformed (ActionEvent e)
    { // Handles all button click events, screen transitions, score updates, and turn changes
        if (e.getActionCommand ().equals ("1"))
            cdLayout.show (p_card, "1");
        else if (e.getActionCommand ().equals ("2"))
            cdLayout.show (p_card, "2");
        else if (e.getActionCommand ().equals ("3"))
            cdLayout.show (p_card, "3");
        else if (e.getActionCommand ().equals ("4"))
            cdLayout.show (p_card, "4");

        else if (e.getActionCommand ().equals ("getNames"))
        {
            if (name != null && !name.getText ().trim ().isEmpty ())
                player1Name = name.getText ().trim ();
            if (name2 != null && !name2.getText ().trim ().isEmpty ())
                player2Name = name2.getText ().trim ();

            gameScreen ();
            p_card.revalidate ();
            p_card.repaint ();
            cdLayout.show (p_card, "4");
        }
        else if (e.getActionCommand ().equals ("reset"))
        {
            reset_method ();
        }
        else if (e.getActionCommand ().equals ("settings"))
        {
            cdLayout.show (p_card, "3");
        }
        else if (e.getActionCommand ().equals ("instruct"))
        {
            cdLayout.show (p_card, "2");
        }

        else if (e.getActionCommand ().equals ("roll"))
        {
            // Generate random roll values for both dice and update icons
            dicea = (int) (Math.random () * 6) + 1;
            a.setIcon (createImageIcon ("d" + dicea + ".png"));

            diceb = (int) (Math.random () * 6) + 1;
            b.setIcon (createImageIcon ("d" + diceb + ".png"));

            int rollSum = dicea + diceb;

            // Add roll sum to active player's score and switch turn
            if (turn == '1')
            {
                scoreA += rollSum;
                turn = '2';
            }
            else
            {
                scoreB += rollSum;
                turn = '1';
            }

            playerAScore.setText (player1Name + " score: " + scoreA);
            playerBScore.setText (player2Name + " score: " + scoreB);
            
            // Update turn label image icon dynamically based on active player turn
            ImageIcon currentTurnIcon = createImageIcon (turn == '1' ? "turn_p1.png" : "turn_p2.png");
            if (currentTurnIcon != null)
            {
                Image img = currentTurnIcon.getImage ().getScaledInstance (200, 35, Image.SCALE_SMOOTH);
                turnLabel.setIcon (new ImageIcon (img));
            }
            else
            {
                turnLabel.setText ((turn == '1' ? player1Name : player2Name) + "'s Turn");
            }

            // Check for winner
            if (scoreA >= 100)
            {
                winMethod (player1Name);
            }
            else if (scoreB >= 100)
            {
                winMethod (player2Name);
            }
        }
    }

    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL = DiceApp.class.getResource (path);
        if (imgURL != null)
            return new ImageIcon (imgURL);
        else
            return null;
    }

    public static void main (String[] args)
    {
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame frame = new JFrame ("DiceApp");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new DiceApp ();
        newContentPane.setOpaque (true);
        frame.setContentPane (newContentPane);
        frame.setSize (375, 500);
        frame.setVisible (true);
    }
}