/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kata5;

import kata5.Histogram;
import kata5.Mail;
import view.HistogramDisplay;
import view.MailHistogramBuilder;
import view.MailListReader;

import java.sql.*;
import java.util.List;

public class Kata5 {

    private String fileName;
    private List<Mail> mailList;
    private Histogram<String> histogram;
    private HistogramDisplay histogramDisplay;

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection c = DriverManager.getConnection("jdbc:sqlite:KATA5.sqlite");
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery("SELECT * FROM PEOPLE");

            while (resultSet.next()) {
                System.out.println("ID=" + resultSet.getInt("id"));
                System.out.println("Nombre=" + resultSet.getString("name"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void execute() {
        input();
        proccess();
        output();
    }

    private void input() {
        fileName = "emails.txt";
        mailList = MailListReader.read(fileName);
    }

    private void proccess() {
        histogram = MailHistogramBuilder.build(mailList);
    }

    private void output() {
        histogramDisplay = new HistogramDisplay(histogram);
        histogramDisplay.execute();
    }
}
