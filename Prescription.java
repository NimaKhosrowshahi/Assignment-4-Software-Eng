package com.Nima.eyeCLear;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"client", "optometrist"};
    private String postRemarks;

    public Prescription(String firstName, String lastName, String address, float sphere, float axis, float cylinder, Date examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    // Method to add prescription
    public boolean addPrescription() {
        // Validate conditions
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }
        if (address.length() < 20) {
            return false;
        }
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // Format the examination date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        String formattedDate = sdf.format(examinationDate);

        // Write to TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Name: " + firstName + " " + lastName + "\n");
            writer.write("Address: " + address + "\n");
            writer.write("Sphere: " + sphere + "\n");
            writer.write("Cylinder: " + cylinder + "\n");
            writer.write("Axis: " + axis + "\n");
            writer.write("Examination Date: " + formattedDate + "\n");
            writer.write("Optometrist: " + optometrist + "\n");
            writer.write("----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Method to add remark
    public boolean addRemark(String remark, String category) {
        // Validate remark length (6-20 words) and first letter of first word capitalized
    	int length = remark.trim().length();
        if (length < 6 || length > 20 || !Character.isUpperCase(remark.charAt(0))) {
            return false;
        }

        // Check remark category
        boolean validCategory = false;
        for (String type : remarkTypes) {
            if (category.equalsIgnoreCase(type)) {
                validCategory = true;
                break;
            }
        }
        if (!validCategory) {
            return false;
        }

        // Add remark to field and write to TXT file, replacing the old remark
        postRemarks = remark;  // Store the remark
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("remark.txt"))) {  // Overwrite the file for the latest remark
            writer.write("Prescription ID: " + prescID + "\n");
            writer.write("Remark: " + postRemarks + "\n");
            writer.write("Category: " + category + "\n");
            writer.write("----------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}
    

        

