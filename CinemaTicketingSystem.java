import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CinemaTicketingSystem extends JFrame implements ActionListener {

    private String[] movies = {"The Matrix", "The Lord of the Rings", "Harry Potter and the Philosopher's Stone"};
    private String[] dates = {"2022-02-21", "2022-02-22", "2022-02-23"};
    private String[] times = {"10:00 AM", "02:00 PM", "06:00 PM"};

    private JComboBox<String> movieList;
    private JComboBox<String> dateList;
    private JComboBox<String> timeList;
    private JToggleButton[] seats;
    private boolean[] seatAvailability;
    private JButton submitButton;

    public CinemaTicketingSystem() {
        initComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Cinema Ticketing System");
    }

    private void initComponents() {
        // Create movie selection panel
        JPanel moviePanel = new JPanel(new GridLayout(1, 3, 5, 5));
        moviePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        movieList = new JComboBox<>(movies);
        dateList = new JComboBox<>(dates);
        timeList = new JComboBox<>(times);
        moviePanel.add(movieList);
        moviePanel.add(dateList);
        moviePanel.add(timeList);

        // Create seat selection panel
        JPanel seatPanel = new JPanel(new GridLayout(5, 10, 5, 5));
        seats = new JToggleButton[50]; // Initialize the seats array
        seatAvailability = new boolean[50];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new JToggleButton(Integer.toString(i + 1));
            seats[i].addActionListener(this);
            seatPanel.add(seats[i]);
        }

        // Create submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Add panels to window
        add(moviePanel, BorderLayout.NORTH);
        add(seatPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        // Set window properties
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (seats == null) {
            initComponents(); // Initialize the seats array
        }
        if (e.getSource() == submitButton) {
            if (movieList.getSelectedIndex() == -1 || dateList.getSelectedIndex() == -1 || timeList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a movie, date, and time.");
                return;
            }

            // Get selected movie, date, and time
            String movie = (String) movieList.getSelectedItem();
            String date = (String) dateList.getSelectedItem();
            String time = (String) timeList.getSelectedItem();

            // Get selected seats
            String seatNumbers = "";
            for (int i = 0; i < seats.length; i++) {
                if (seats[i].isSelected()) {
                    seatNumbers += (i + 1) +                     ", ";
                    seatAvailability[i] = false;
                    seats[i].setEnabled(false);
                    seats[i].setSelected(false);
                    seats[i].setBackground(Color.RED);
                } else {
                    seatAvailability[i] = true;
                    seats[i].setBackground(null);
                }
            }

            // Show ticket information
            JOptionPane.showMessageDialog(this, "You have booked seat(s) " + seatNumbers + " for the movie \"" + movie + "\" on " + date + " at " + time + ".");

        } else {
            // Toggle seat availability
            for (int i = 0; i < seats.length; i++) {
                if (e.getSource() == seats[i]) {
                    seats[i].setSelected(!seats[i].isSelected());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        new CinemaTicketingSystem();
    }
}

