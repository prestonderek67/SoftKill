import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class SoftKill extends JComponent {
    private JFrame frame;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JTextArea descriptArea;
    private JScrollPane descriptScroll;
    private JTextField targetIPArea;
    private JLabel targetIPLabel;
    private Font descriptFont;
    private Font IPFont;
    private Font mainFont;

    //Driver Code
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SoftKill window = new SoftKill();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //Ctor to push init()
    public SoftKill() {
        init();
    }

    private void init() {
        //frame init
        frame = new JFrame("SoftKill");
        frame.setSize(800, 650);
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //Font init
        mainFont = new Font("Cantarell Extra Bold", Font.PLAIN, 20);
        descriptFont = new Font("Cantarell Extra Bold", Font.PLAIN, 18);
        IPFont = new Font("Cantarell Extra Bold", Font.PLAIN, 28);

        //Left panel init
        panelLeft = new JPanel();
        panelLeft.setBackground(Color.GRAY);
        panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
        panelLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add padding
        frame.add(panelLeft, BorderLayout.WEST);

        //Right panel init
        panelRight = new JPanel();
        panelRight.setLayout(new BorderLayout());
        frame.add(panelRight, BorderLayout.CENTER);

        //Description Text Area init
        descriptArea = new JTextArea(15, 30); // Set preferred size
        descriptArea.setBackground(Color.GRAY);
        descriptScroll = new JScrollPane(descriptArea);
        descriptScroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        descriptArea.setEditable(false);
        descriptArea.setLineWrap(true);
        descriptArea.setFont(descriptFont);
        panelRight.add(descriptScroll, BorderLayout.NORTH);

        //Target IP address init
        JPanel targetIPPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Align left
        targetIPLabel = new JLabel("Target IP:");
        targetIPLabel.setFont(IPFont);
        targetIPPanel.add(targetIPLabel);
        targetIPArea = new JTextField("127.0.0.1", 15); // Adjust width
        targetIPArea.setFont(IPFont);
        targetIPPanel.add(targetIPArea);
        targetIPPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        panelRight.add(targetIPPanel, BorderLayout.CENTER);

        //Soft Kill/Destroy button init
        JButton softKillButton = new JButton("SOFT KILL");
        softKillButton.setForeground(Color.BLACK);
        softKillButton.setBackground(Color.RED);
        softKillButton.setFont(mainFont.deriveFont(Font.BOLD, 55)); // Set font and size
        softKillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPaymentFrame();
            }
        });
        panelRight.add(softKillButton, BorderLayout.SOUTH);

        //Buttons to be added and their information when selecting the category you want
        panelLeft.add(Box.createVerticalStrut(5));
        addButton("Individual Computer", new String[]{"Standard Service", "Standard + Data Save", "Above Services + More!"});
        panelLeft.add(Box.createVerticalStrut(30));
        addButton("Individual Cell Phone", new String[]{"Standard Service", "Standard + Photo Copies", "Above Services + More!"});
        panelLeft.add(Box.createVerticalStrut(30));
        addButton("Mainframe Server", new String[]{"Standard Service", "Standard + Company Owner", "Above Services + More!"});
        panelLeft.add(Box.createVerticalStrut(30));
        addButton("Custom Request", new String[]{"Contact Us"});
        panelLeft.add(Box.createVerticalStrut(10));
    }

    //Function to add new buttons when clicking on left categories
    private void addButton(String name, String[] options) {
        JButton button = new JButton(name);
        button.setFont(mainFont); // Set font
        button.setBackground(Color.lightGray);
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align left
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)); // Set maximum height and width
        panelLeft.add(button);

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setVisible(false);

        for (String option : options) {
            JButton optionButton = new JButton(option);
            optionButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE)); // Set maximum height and width
            optionButton.setFont(mainFont.deriveFont(Font.PLAIN, 14));
            if (Objects.equals(option, "Contact Us")) {
                optionButton.setBackground(Color.RED);
                optionButton.setForeground(Color.WHITE);
            } else if (Objects.equals(option, options[0])) {
                optionButton.setBackground(Color.ORANGE);
            } else if (Objects.equals(option, options[1])) {
                optionButton.setBackground(Color.YELLOW);
            } else if (Objects.equals(option, options[2])) {
                optionButton.setBackground(Color.GREEN);
            }
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    descriptArea.setText("Selected: " + name + " - " + option);
                    if (Objects.equals(option, "Contact Us")) {
                        openContactFrame();
                    }
                }
            });
            optionPanel.add(optionButton);
        }
        panelLeft.add(optionPanel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionPanel.setVisible(!optionPanel.isVisible());
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    private void openPaymentFrame() {
        JFrame paymentFrame = new JFrame("Payment Processing");
        paymentFrame.setBounds(200, 200, 400, 300);
        paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        paymentFrame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Payment processing for destroying IP: " + targetIPArea.getText(), SwingConstants.CENTER);
        paymentFrame.add(label, BorderLayout.CENTER);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(paymentFrame, "Payment processed.");
                paymentFrame.dispose();  // Close the payment frame after processing
            }
        });
        paymentFrame.add(payButton, BorderLayout.SOUTH);

        paymentFrame.setVisible(true);
    }

    private void openContactFrame() {
        JFrame contactFrame = new JFrame("Contact Us");
        contactFrame.setBounds(200, 200, 400, 300);
        contactFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        contactFrame.setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Vertical alignment
        centerPanel.add(Box.createVerticalStrut(100));

        JLabel label = new JLabel("Contact Us with your special Soft Kill requests!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Horizontal center alignment
        centerPanel.add(label);

        JLabel label2 = new JLabel("We will get back to you within 10 years!");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT); // Horizontal center alignment
        centerPanel.add(label2);

        // Add centerPanel to the center of the contactFrame
        contactFrame.add(centerPanel, BorderLayout.CENTER);

        JButton contactButton = new JButton("Submit");
        contactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(contactFrame, "Request Submitted.");
                contactFrame.dispose(); //Close frame after submitting
            }
        });
        contactFrame.add(contactButton, BorderLayout.SOUTH);

        contactFrame.setVisible(true);
    }
}


