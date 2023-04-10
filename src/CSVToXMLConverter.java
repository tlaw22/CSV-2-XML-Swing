import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class CSVToXMLConverter extends JFrame {

    private JTextField csvFilePathTextField;
    private JButton convertButton;
    private JTextArea outputTextArea;
    private JPanel contentPane;

    public CSVToXMLConverter() {
        super("CSV to XML Converter");

        // Create the CSV file path text field
        csvFilePathTextField = new JTextField(30);

        // Create the convert button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the CSV file path from the text field
                String csvFilePath = csvFilePathTextField.getText();

                // Try to convert the CSV file to an XML file
                try {
                    convertCSVToXML(csvFilePath);
                    outputTextArea.setText("The CSV file has been successfully converted to an XML file.");
                    JOptionPane.showMessageDialog(null, "The XML file has been created successfully.");
                } catch (IOException ex) {
                    outputTextArea.setText("An error occurred while converting the CSV file to an XML file.");
                }
            }
        });

        // Create the output text area
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        // Add the components to the panel
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(csvFilePathTextField, BorderLayout.NORTH);
        contentPane.add(convertButton, BorderLayout.CENTER);
        contentPane.add(outputTextArea, BorderLayout.SOUTH);

        // Set the frame's size and location
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void convertCSVToXML(String csvFilePath) throws IOException {
        // Create a new File object for the CSV file
        File csvFile = new File(csvFilePath);

        // Create a new FileWriter object for the XML file
        FileWriter xmlFileWriter = new FileWriter("data.xml");

        // Write the XML header to the file
        xmlFileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        // Read the CSV file line by line
        Scanner csvScanner = new Scanner(csvFile);
        while (csvScanner.hasNextLine()) {
            // Get the current line of the CSV file
            String csvLine = csvScanner.nextLine();

            // Split the CSV line into columns
            String[] columns = csvLine.split(",");

            // Write the XML element for the current row
            xmlFileWriter.write("<row>\n");
            for (int i = 0; i < columns.length; i++) {
                xmlFileWriter.write("<column>" + columns[i] + "</column>\n");
            }
            xmlFileWriter.write("</row>\n");
        }

        // Close the FileWriter object
        xmlFileWriter.close();
    }

    public static void main(String[] args) {
        // Create a new CSVToXMLConverter frame
        CSVToXMLConverter frame = new CSVToXMLConverter();

        // Display the frame
        frame.setVisible(true);
    }
}