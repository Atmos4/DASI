/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.ihm_positif;

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;

import services.Services;

/**
 *
 * @author gbailly
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String todo=request.getParameter("action");
        
        Services services = new Services();
        switch (todo)
        {
            case  "connexionClient" :
            {
                String mail = request.getParameter("mail");
                Client client = services.connexionClient(mail);
                if(client!=null)
                {
                   response.setStatus(200);
                   HttpSession session = request.getSession(true);
                   session.setAttribute("Client", client);
                }
                else
                {
                     //403 : erreur on refuse de traiter la requete
                     response.setStatus(403);
                }
                
                break;
            }
            case "connexionEmploye" :
            {
                String mail = request.getParameter("mail");
                if(services.connexionEmploye(mail))
                {
                   response.setStatus(200);
                   HttpSession session = request.getSession(true);
                   session.setAttribute("mailEmploye", mail);
                }
                else
                {
                     //403 : erreur on refuse de traiter la requete
                     response.setStatus(403);
                }
                
                break;
            }
            
            case "getDonneesClients" :
            {
                
                
                break;
            }
            
            default :
            {
                
                
            }
            
                
        }
        
        
        
        
        try (PrintWriter out = response.getWriter()) {
            
            
            
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    public void init(){
        JpaUtil.init();
    }
    
    @Override
    public void destroy(){
        JpaUtil.destroy();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
