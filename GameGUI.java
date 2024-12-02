class GameGUI extends JFrame {

    GameGUI() {
        JLabel background, textLabel;
        setSize(1600, 1000);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("C:\\Users\\fatima\\Desktop\\areeba uni\\2nd semester\\DATA STRUCTURE\\data labs\\oop background.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 1600, 1000);
        add(background);

        // Add a text label
        textLabel = new JLabel("Welcome to Triple Strike Game", JLabel.CENTER);
        textLabel.setFont(new Font("Garamond", Font.PLAIN, 55)); // Set font, style, and size
        textLabel.setForeground(new Color(0xFFFBFFDE, true)); // Set text color
        textLabel.setBounds(500, 50, 800, 70);// Position the label (x, y, width, height)
        background.add(textLabel); // Add text label to the background

        // Quotation text: "I know that I am intelligent..."
        JLabel quote = new JLabel("\"Play to grow, not just to win.\"");
        quote.setFont(new Font("Garamond", Font.BOLD, 40)); // Choose a font and size
        quote.setForeground(new Color(0xFFFBFFDE, true)); // Set text color to white
        quote.setBounds(500, textLabel.getY() + textLabel.getHeight() + 20, 1000, 50); // Position below the title
        background.add(quote); // Add quote to the background


        // Add Feedback button
        JButton feedbackButton = new JButton("Feedback");
        feedbackButton.setFont(new Font("Garamond", Font.PLAIN, 25)); // Set font and size
        feedbackButton.setBackground(new Color(0xD64161)); // Background color
        feedbackButton.setForeground(Color.WHITE); // Text color
        feedbackButton.setFocusPainted(false); // Remove focus border
        feedbackButton.setFocusPainted(false); // Remove focus border
        feedbackButton.setBorderPainted(false); // Remove the border
        feedbackButton.setBounds(600, 300, 300, 40); // Position below the quote
        background.add(feedbackButton);

        // Add Feedback button
        JButton startbutton = new JButton("Let's Start The Game");
        startbutton.setFont(new Font("Garamond", Font.PLAIN, 25)); // Set font and size
        startbutton.setBackground(new Color(0x6B5B95)); // Background color
        startbutton.setForeground(Color.WHITE); // Text color
        startbutton.setFocusPainted(false); // Remove focus border
        startbutton.setFocusPainted(false); // Remove focus border
        startbutton.setBorderPainted(false); // Remove the border
        startbutton.setBounds(600, 400, 300, 40); // Position below the quote
        background.add(startbutton);


        setVisible(true);

        // Action listeners for the buttons
        startbutton.addActionListener(e -> openSymbolSelection());
        feedbackButton.addActionListener(e -> openFeedbackWindow());
    }

    private void openFeedbackWindow() {
        // Create a new JFrame for the feedback window
        JFrame feedbackFrame = new JFrame("Feedback");
        feedbackFrame.setSize(800, 600);
        feedbackFrame.setLayout(new BorderLayout());
        feedbackFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load the background image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("C:\\Users\\fatima\\Desktop\\areeba uni\\2nd semester\\DATA STRUCTURE\\data labs\\oop background.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        // Create a JLabel with the background image
        JLabel backgroundLabel = new JLabel(new ImageIcon(image));
        backgroundLabel.setLayout(new BorderLayout());

        // Create a panel for the header text
        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false); // Make this panel transparent
        textPanel.setLayout(new BorderLayout());

        // Create a JLabel for the text
        JLabel textLabel = new JLabel("Great things are never done alone", JLabel.CENTER);
        textLabel.setFont(new Font("Garamond", Font.PLAIN, 60)); // Set font, style, and size
        textLabel.setForeground(new Color(0xFFFBFFDE, true)); // Set text color
        textLabel.setOpaque(false);

        // Add the text label to the text panel
        textPanel.add(textLabel, BorderLayout.CENTER);

        // Create a canvas for user feedback
        JTextArea feedbackArea = new JTextArea();
        feedbackArea.setFont(new Font("Arial", Font.PLAIN, 16));
        feedbackArea.setBackground(new Color(0x878F99));
        feedbackArea.setSize(600 ,600);
        feedbackArea.setLineWrap(true);
        feedbackArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(feedbackArea);

        scrollPane.setPreferredSize(new Dimension(760, 300)); // Adjust size as needed
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Add Feedback button
        JButton submitButton = new JButton("Submit Button");
        submitButton.setFont(new Font("Garamond", Font.PLAIN, 25)); // Set font and size
        submitButton.setBackground(new Color(0x6B5B95)); // Background color
        submitButton.setForeground(Color.WHITE); // Text color
        submitButton.setFocusPainted(false); // Remove focus border
        submitButton.setFocusPainted(false); // Remove focus border
        submitButton.setBorderPainted(false); // Remove the border
        submitButton.setBounds(600, 400, 300, 40); // Position below the quote


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String feedback = feedbackArea.getText();
                if (feedback.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(feedbackFrame, "Please enter your feedback before submitting.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Save feedback to a file
                File feedbackFile = new File("feedback.txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFile, true))) {
                    writer.write(feedback);
                    writer.newLine();
                    writer.write("-----");
                    writer.newLine();
                    feedbackArea.setText(""); // Clear the feedback area after submission
                    JOptionPane.showMessageDialog(feedbackFrame, "Feedback submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(feedbackFrame, "An error occurred while saving feedback.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Create a panel to hold the feedback area and submit button
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new BorderLayout());
        feedbackPanel.setOpaque(false);
        feedbackPanel.add(scrollPane, BorderLayout.CENTER);
        feedbackPanel.add(submitButton, BorderLayout.SOUTH);

        // Add components to the background label
        backgroundLabel.add(textPanel, BorderLayout.NORTH);
        backgroundLabel.add(feedbackPanel, BorderLayout.CENTER);

        // Set the content pane of the feedback frame to the background label
        feedbackFrame.setContentPane(backgroundLabel);

        feedbackFrame.setLocationRelativeTo(null); // Center the window
        feedbackFrame.setVisible(true);
    }
private void openSymbolSelection() {
        JFrame matchSelectionFrame = new JFrame("Choose Your Symbol");
        matchSelectionFrame.setSize(1600, 1000);
        matchSelectionFrame.setLayout(null);
        matchSelectionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Add a background image
        JLabel background;
        ImageIcon img = new ImageIcon("C:\\Users\\fatima\\Desktop\\areeba uni\\2nd semester\\DATA STRUCTURE\\data labs\\oop background.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 1600, 1000);
        matchSelectionFrame.add(background);

        // Title text: "Choose Your Symbol"
        JLabel textLabel = new JLabel("Choose Your Symbol", JLabel.CENTER);
        textLabel.setFont(new Font("Garamond", Font.PLAIN, 60));
        textLabel.setForeground(new Color(0xFFFBFFDE, true));
        textLabel.setBounds(500, 50, 800, 70);
        background.add(textLabel);

        // Quotation text
        JLabel quote = new JLabel("The only way to do great work is to love what you do.", JLabel.CENTER);
        quote.setFont(new Font("Garamond", Font.BOLD, 40));
        quote.setForeground(new Color(0xFFFBFFDE, true));
        quote.setBounds(300, textLabel.getY() + textLabel.getHeight() + 20, 1000, 50);
        background.add(quote);

        // Button: X Symbol
        JButton XButton = new JButton("X");
        XButton.setFont(new Font("Garamond", Font.PLAIN, 20));
        XButton.setBackground(new Color(0xD64161));
        XButton.setForeground(Color.WHITE);
        XButton.setFocusPainted(false);
        XButton.setBorderPainted(false);
        XButton.setBounds(600, 300, 300, 50);
        background.add(XButton);

        // Button: O Symbol
        JButton OButton = new JButton("O");
        OButton.setFont(new Font("Garamond", Font.PLAIN, 20));
        OButton.setBackground(new Color(0x6B5B95));
        OButton.setForeground(Color.WHITE);
        OButton.setFocusPainted(false);
        OButton.setBorderPainted(false);
        OButton.setBounds(600, 400, 300, 50);
        background.add(OButton);

        // Action listener for X button
        XButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicTacToe("X");
                matchSelectionFrame.dispose();
            }
        });

        // Action listener for O button
        OButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new TicTacToe("O");
                matchSelectionFrame.dispose();
            }
        });

        matchSelectionFrame.setVisible(true);
    }




}


