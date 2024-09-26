import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherAppUI extends JFrame {

    private JTextField cityTextField;
    private JTextArea resultTextArea;

    public WeatherAppUI() {
        setTitle("Weather App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        JLabel cityLabel = new JLabel("Enter City:");
        cityTextField = new JTextField(15);
        JButton searchButton = new JButton("Get Weather");
        resultTextArea = new JTextArea(10, 30);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setEditable(false);

        // Aesthetic UI setup
        Font font = new Font("SansSerif", Font.BOLD, 18);
        cityLabel.setFont(font);
        cityTextField.setFont(font);
        searchButton.setFont(font);
        resultTextArea.setFont(font);

        JPanel panel = new JPanel();
        panel.add(cityLabel);
        panel.add(cityTextField);
        panel.add(searchButton);

        // Adding components to frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        // Button action listener
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = cityTextField.getText().trim();
                if (!city.isEmpty()) {
                    String response = WeatherAPI.getWeatherData(city);
                    String weatherInfo = WeatherAPI.parseWeatherData(response);
                    resultTextArea.setText(weatherInfo);
                } else {
                    resultTextArea.setText("Please enter a city.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WeatherAppUI().setVisible(true);
            }
        });
    }
}
