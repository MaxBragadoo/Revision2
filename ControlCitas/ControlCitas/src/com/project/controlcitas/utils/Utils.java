package com.project.controlcitas.utils;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Utils {

    public static String MESSAGE;
    public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Advertencia", 2);
    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", 0);
    }

    public static void showExcepción(String message) {
        JOptionPane.showMessageDialog(null, "Excepción: " + message, "Excepción", 0);
    }

    public static void showExcepcion() {
        JOptionPane.showMessageDialog(null, MESSAGE, "Excepción", 0);
    }

    public static void showInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", 1);
    }

}
