/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.ihm_positif;

import Serializer.Printer;
import Serializer.PrinterDemarrerVoyance;
import Serializer.PrinterDetailsMedium;
import Serializer.PrinterHistorique;
import Serializer.PrinterMedium;
import Serializer.PrinterPrediction;
import Serializer.PrinterProfilClient;
import Serializer.PrinterRecupererDemandeVoyance;
import Serializer.PrinterStatusRequest;
import Serializer.PrinterVoyancesBarChartMedium;
import Serializer.PrinterVoyancesPieChart;
import action.Action;
import action.ActionAfficherDetailsMedium;
import action.ActionConnexionClient;
import action.ActionConnexionEmploye;
import action.ActionDemarrerVoyance;
import action.ActionInscription;
import action.ActionRecupererDataBarChartEmploye;
import action.ActionRecupererDataBarChartMedium;
import action.ActionRecupererDataPieChart;
import action.ActionRecupererDemandeVoyance;
import action.ActionRecupererHistoriqueClient;
import action.ActionRecupererMediums;
import action.ActionRecupererPrediction;
import action.ActionRecupererProfilClient;
import dao.JpaUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        switch (todo)
        {
            case "inscription" : 
            {
                Action act = new ActionInscription();
                act.execute(request);
                Printer prt = new PrinterStatusRequest();
                prt.execute(response.getWriter(), request);
                
                break;
            }
            case  "connexionClient" :
            {
                Action act = new ActionConnexionClient();
                act.execute(request);
                Printer prt = new PrinterStatusRequest();
                prt.execute(response.getWriter(), request);
                
                break;
            }
            case "connexionEmploye" :
            {
                Action act = new ActionConnexionEmploye();
                act.execute(request);
                Printer prt = new PrinterStatusRequest();
                prt.execute(response.getWriter(), request);
                
                break;
            }
            
            case "recupererProfilClient" :
            {
                Action act = new ActionRecupererProfilClient();
                act.execute(request);
                Printer prt = new PrinterProfilClient();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererHistoriqueClient" :
            {
                Action act = new ActionRecupererHistoriqueClient();
                act.execute(request);
                Printer prt = new PrinterHistorique();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererMediums":
            {
                Action act = new ActionRecupererMediums();
                act.execute(request);
                Printer prt = new PrinterMedium();
                prt.execute(response.getWriter(), request);
                break;
            }
            case "afficherDetailsMedium":
            {
                Action act = new ActionAfficherDetailsMedium();
                act.execute(request);
                Printer prt = new PrinterDetailsMedium();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererDataPieChart":
            {
                Action act = new ActionRecupererDataPieChart();
                act.execute(request);
                Printer prt = new PrinterVoyancesPieChart();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererDataBarChartMedium":
            {
                Action act = new ActionRecupererDataBarChartMedium();
                act.execute(request);
                Printer prt = new PrinterVoyancesBarChartMedium();
                prt.execute(response.getWriter(), request);
                break;
            }
            case "recupererDataBarChartEmploye":
            {
                Action act = new ActionRecupererDataBarChartEmploye();
                act.execute(request);
                Printer prt = new PrinterVoyancesPieChart();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererDemandeVoyanceEmploye":
            {
                Action act = new ActionRecupererDemandeVoyance();
                act.execute(request);
                Printer prt = new PrinterRecupererDemandeVoyance();
                prt.execute(response.getWriter(), request);
                break;
            }
            case "demarrerVoyance":
            {
                Action act = new ActionDemarrerVoyance();
                act.execute(request);
                Printer prt = new PrinterDemarrerVoyance();
                prt.execute(response.getWriter(), request);
                break;
            }
            
            case "recupererPrediction":
            {
                Action act = new ActionRecupererPrediction();
                act.execute(request);
                Printer prt = new PrinterPrediction();
                prt.execute(response.getWriter(), request);
                break;
            }
            default :
            {
                
                
            }
                
        }
        
        
        
        
//        try (PrintWriter out = response.getWriter()) {
//            
//            
//            
//            
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ActionServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }
    
    @Override
    public void init(){
        try {
            super.init();
        } catch (ServletException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.init();
    }
    
    @Override
    public void destroy(){
        super.destroy();
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
