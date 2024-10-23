package com.Nima.eyeCLear;

import org.junit.jupiter.api.Test;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {

    @Test
    public void testAddPrescriptionSuccess() {
        Prescription p = new Prescription("Nima", "Khosrow", "123 Test Street, TestSub1, Country1", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertTrue(p.addPrescription());
    }
    @Test
    public void testAddPrescriptionNegativesSuccess() {
        Prescription p = new Prescription("Nimatwo", "Khosrow", "123 Test2 Street, TestSub2, Country2", -5.00f, 100f, -2.00f, new Date(), "Dr. Optometrist");
        assertTrue(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionFailName() {
        Prescription p = new Prescription("Jo", "Sm", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionFailAddress() {
        Prescription p = new Prescription("John", "Smith", "Short Address", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionFailAxis() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 25.00f, 190f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addPrescription());
    }
    @Test
    public void testAddPrescriptionFailPositiveSphere() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 25.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addPrescription());
    }
    @Test
    public void testAddPrescriptionFailNegativeSphere() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", -25.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionFailOptometristName() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Opto");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddRemarkSuccess() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertTrue(p.addRemark("Hello there", "client"));
    }
    
    @Test
    public void testAddRemarkSuccessOptometrist() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertTrue(p.addRemark("Customer return", "optometrist"));
    }

    @Test
    public void testAddRemarkFailTooFewWords() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addRemark("Short", "client"));
    }
    @Test
    public void testAddRemarkFailStartWithLowerCase() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addRemark("hello there", "client"));
    }

    @Test
    public void testAddRemarkFailInvalidCategory() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        assertFalse(p.addRemark("Feedback", "Manger"));
    }
    @Test
    public void testAddRemarkTrueReplace() {
        Prescription p = new Prescription("John", "Smith", "123 Street, Suburb, Country", 5.00f, 100f, 2.00f, new Date(), "Dr. Optometrist");
        p.addRemark("Glasses bad", "optometrist");
        assertTrue(p.addRemark("Glasses good", "client"));
}
}

