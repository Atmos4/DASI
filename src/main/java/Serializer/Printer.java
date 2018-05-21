/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serializer;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Arnaud
 */
public abstract class Printer {

    
    public abstract void execute(PrintWriter out, HttpServletRequest request );
}
