/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

/**
 *
 * @author ANPHUOC
 */
import java.io.*;

public class ImageChecker {
    public static boolean checkImage(String path) {
        String imagePath = path; 
        File imageFile = new File(imagePath);
        
        return imageFile.exists();
    }
}

