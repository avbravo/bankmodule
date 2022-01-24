/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peopleinmotion.horizonreinicioremoto.utils;

import static com.diogonunes.jcolor.Ansi.colorize;
import com.diogonunes.jcolor.AnsiFormat;
import static com.diogonunes.jcolor.Attribute.BLUE_BACK;
import static com.diogonunes.jcolor.Attribute.BOLD;
import static com.diogonunes.jcolor.Attribute.BRIGHT_YELLOW_TEXT;
import static com.diogonunes.jcolor.Attribute.CYAN_TEXT;
import static com.diogonunes.jcolor.Attribute.GREEN_BACK;
import static com.diogonunes.jcolor.Attribute.GREEN_TEXT;
import static com.diogonunes.jcolor.Attribute.RED_BACK;
import static com.diogonunes.jcolor.Attribute.WHITE_TEXT;
import static com.diogonunes.jcolor.Attribute.YELLOW_TEXT;
import static com.peopleinmotion.horizonreinicioremoto.utils.JsfUtil.nameOfMethod;

/**
 *
 * @author avbravo
 */
public class ConsoleUtil {
    // <editor-fold defaultstate="collapsed" desc="void consoleInfo(String text)">
    public static void info(String text) {
        try {
            AnsiFormat fInfo = new AnsiFormat(CYAN_TEXT());
            System.out.println(fInfo.format(text));
               System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="consoleNormal(String text)">

    public static void normal(String text) {
        try {

            System.out.println(text);
               System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="void consoleError(String text)">

    public static void error(String text) {
        try {
            AnsiFormat fError = new AnsiFormat(YELLOW_TEXT(), RED_BACK());
            System.out.println(fError.format(text));
               System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" void consoleWarning(String text)">

    public static void warning(String text) {
        try {
            AnsiFormat fWarning = new AnsiFormat(GREEN_TEXT(), BLUE_BACK(), BOLD());
            System.out.println(fWarning.format(text));
               System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" void consoleGreenBackground(String text)">

    public static void greenBackground(String text) {
        try {
           System.out.print(colorize(text, BOLD(), BRIGHT_YELLOW_TEXT(), GREEN_BACK()));
            System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" void consoleGreenBackground(String text)">

    public static void greenBackgroundWhiteText(String text) {
        try {
           System.out.print(colorize(text, BOLD(), WHITE_TEXT(), GREEN_BACK()));
            System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="  void consoleRedBackground(String text)">

    public static void redBackground(String text) {
        try {
         System.out.println(colorize(text, BOLD(), BRIGHT_YELLOW_TEXT(), RED_BACK()));
            System.out.println("\n");
        } catch (Exception e) {
            JsfUtil.errorMessage(nameOfMethod() + " " + e.getLocalizedMessage());
        }
    }
// </editor-fold>
}
