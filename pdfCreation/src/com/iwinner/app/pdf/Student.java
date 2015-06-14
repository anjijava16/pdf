/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iwinner.app.pdf;

import java.util.ArrayList;

/**
 *
 * @author nishad
 */
public class Student {
    public String name;
    public ArrayList subjects, marks;
    public int total;
	public String roll;
        
    public Student() {
        subjects = new ArrayList();
        marks = new ArrayList();
        total = 0;
    }
}
