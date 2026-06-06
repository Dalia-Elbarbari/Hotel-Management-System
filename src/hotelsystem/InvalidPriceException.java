/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelsystem;

/**
 *
 * @author dell
 */public class InvalidPriceException extends Exception {
    @Override
    public String toString() {
        return "Error: Price must be positive!";
    }
}

