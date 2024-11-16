package TUIO;

/*
 TUIO Java GUI Demo
 Copyright (c) 2005-2014 Martin Kaltenbrunner <martin@tuio.org>
 
 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files
 (the "Software"), to deal in the Software without restriction,
 including without limitation the rights to use, copy, modify, merge,
 publish, distribute, sublicense, and/or sell copies of the Software,
 and to permit persons to whom the Software is furnished to do so,
 subject to the following conditions:
 
 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import TUIO.*;
import GUI.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TuioDemoObject extends TuioObject {

    private Shape square;
    private boolean isEdgeOpened = false;
    public TuioDemoObject(TuioObject tobj) {
        super(tobj);
        int size = TuioDemoComponent.object_size;
        square = new Rectangle2D.Float(-size / 2, -size / 2, size, size);

        AffineTransform transform = new AffineTransform();
        transform.translate(xpos, ypos);
        transform.rotate(angle, xpos, ypos);
        square = transform.createTransformedShape(square);
    }

    public void paint(Graphics2D g, int width, int height) {
        float Xpos = xpos * width;
        float Ypos = ypos * height;
        float scale = height / (float) TuioDemoComponent.table_size;

        AffineTransform trans = new AffineTransform();
        trans.translate(-xpos, -ypos);
        trans.translate(Xpos, Ypos);
        trans.scale(scale, scale);
        Shape s = trans.createTransformedShape(square);

        // Use the provided marker ID for Word (e.g., symbol_id == 2)
        switch (symbol_id) {
            
            case 10:
                // New ID for Word
                try {
                    // Use the provided shortcut path for Word
                    String command = "cmd.exe /c start \"\" \"C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Word.lnk\"";
                    Runtime.getRuntime().exec(command);
                } catch (Exception e) {
                    e.printStackTrace();
                }   break;
            case 1:
                try {
                // Use the provided shortcut path for Word
                //String command = "cmd.exe /c start \"\" \"C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\Word.lnk\"";
                String PPTPath = "\"C:\\Program Files\\Microsoft Office\\root\\Office16\\POWERPNT.EXE\"";
                Runtime.getRuntime().exec(PPTPath);
                
            } catch (IOException e) {
                e.printStackTrace();
            } break;
            case 3:
                Runtime rs = Runtime.getRuntime();
                try {
                    rs.exec("\"C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE\"");
                } catch (IOException ex) {
                    Logger.getLogger(TuioDemoObject.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            case 4:
                    try {
                // Opens the default file explorer in the current directory
                Desktop.getDesktop().open(new File(System.getProperty("user.home")));
            } catch (IOException e) {
                e.printStackTrace();
            }      break;
            
            case 5:
                if (!isEdgeOpened) {
                    try {
                        String edgePath = "\"C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe\"";
                        // Open Edge with Google URL
                        Runtime.getRuntime().exec(new String[]{edgePath, "http://Google.com"});
                        isEdgeOpened = true; // Set flag to true once Edge is opened
                        System.out.println("Edge opened with Google.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Edge is already opened.");
                } break;


                default:
                    break;
            }
    }

    public void update(TuioObject tobj) {
        float dx = tobj.getX() - xpos;
        float dy = tobj.getY() - ypos;
        float da = tobj.getAngle() - angle;

        if ((dx != 0) || (dy != 0)) {
            AffineTransform trans = AffineTransform.getTranslateInstance(dx, dy);
            square = trans.createTransformedShape(square);
        }

        if (da != 0) {
            AffineTransform trans = AffineTransform.getRotateInstance(da, tobj.getX(), tobj.getY());
            square = trans.createTransformedShape(square);
        }

        super.update(tobj);
    }

    

    
}
