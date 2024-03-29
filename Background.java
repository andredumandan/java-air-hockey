/**
	
    This java files is a DrawingObject that draws the Air Hockey Field. It uses the draw method from the DrawingObject interface and utilizes a Graphics2D object to draw the shapes.

	@author Andre Matthew G. Dumandan (201836)
	@version May 16, 2021
**/

/*
	I have not discussed the Java language code in my program 
	with anyone other than my instructor or the teaching assistants 
	assigned to this course.

	I have not used Java language code obtained from another student, 
	or any other unauthorized source, either modified or unmodified.

	If any Java language code or documentation used in my program 
	was obtained from another source, such as a textbook or website, 
	that has been clearly noted with a proper citation in the comments 
	of my program.
*/

import java.awt.*;
import java.awt.geom.*;

public class Background implements DrawingObject{
    private Color bgColor;
    private Color centerColor;

    /**
     * Initializes the color instances used for the Background
     */
    public Background(){
        bgColor = new Color(232,231,225);
        centerColor = new Color(240,60,60);
    }

    
    
    /** 
     * Draws the background of the Air Hockey Field
     * @param g2d - accepts a Graphics2D object that will be used to draw and fill shapes
     */
    @Override
    public void draw(Graphics2D g2d){
        g2d.setColor(bgColor);
        Rectangle2D.Double bg = new Rectangle2D.Double(0, 0, 800, 400);
        g2d.fill(bg);
        g2d.setColor(centerColor);
        Rectangle2D.Double line = new Rectangle2D.Double(396, 0, 8, 400);
        Ellipse2D.Double leftOuterCircle1 = new Ellipse2D.Double(111,56,59,59);
        Ellipse2D.Double leftOuterCircle2 = new Ellipse2D.Double(111,286,59,59);
        Ellipse2D.Double rightOuterCircle1 = new Ellipse2D.Double(630,56,59,59);
        Ellipse2D.Double rightOuterCircle2 = new Ellipse2D.Double(630,286,59,59);
        g2d.fill(line);
        g2d.fill(leftOuterCircle1);
        g2d.fill(leftOuterCircle2);
        g2d.fill(rightOuterCircle1);
        g2d.fill(rightOuterCircle2);
        Ellipse2D.Double center = new Ellipse2D.Double(340, 140, 121, 121);
        g2d.fill(center);
        g2d.setColor(bgColor);
        Ellipse2D.Double centerCover = new Ellipse2D.Double(350, 150, 100, 100);
        Ellipse2D.Double leftInnerCircle1 = new Ellipse2D.Double(118, 63, 45, 45);
        Ellipse2D.Double leftInnerCircle2 = new Ellipse2D.Double(118, 293, 45, 45);
        Ellipse2D.Double rightInnerCircle1 = new Ellipse2D.Double(637,63,45,45);
        Ellipse2D.Double rightInnerCircle2 = new Ellipse2D.Double(637,293,45,45);
        g2d.fill(centerCover);
        g2d.fill(leftInnerCircle1);
        g2d.fill(leftInnerCircle2);
        g2d.fill(rightInnerCircle1);
        g2d.fill(rightInnerCircle2);
        Ellipse2D.Double leftGoal = new Ellipse2D.Double(-43,148,93,106);
        Ellipse2D.Double rightGoal = new Ellipse2D.Double(751,148,93,106);
        g2d.setColor(centerColor);
        g2d.fill(leftGoal);
        g2d.fill(rightGoal);
        Rectangle2D.Double leftLine = new Rectangle2D.Double(0, 148, 4, 106);
        Rectangle2D.Double rightLine = new Rectangle2D.Double(796, 148, 4, 106);
        g2d.setColor(Color.BLACK);
        g2d.fill(leftLine);
        g2d.fill(rightLine);
        Ellipse2D.Double leftDot1 = new Ellipse2D.Double(136,81,8,8);
        Ellipse2D.Double leftDot2 = new Ellipse2D.Double(136,311,8,8);
        Ellipse2D.Double rightDot1 = new Ellipse2D.Double(655,81,8,8);
        Ellipse2D.Double rightDot2 = new Ellipse2D.Double(655,311,8,8);
        g2d.setColor(centerColor);
        g2d.fill(leftDot1);
        g2d.fill(leftDot2);
        g2d.fill(rightDot1);
        g2d.fill(rightDot2);

    }
    
}
    
