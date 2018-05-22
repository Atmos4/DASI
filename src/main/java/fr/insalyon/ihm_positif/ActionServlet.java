/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.ihm_positif;

import Serializer.Printer;
import Serializer.PrinterDetailsMedium;
import Serializer.PrinterHistorique;
import Serializer.PrinterMedium;
import Serializer.PrinterRecupererDemandeVoyance;
import Serializer.PrinterVoyancesBarChartMedium;
import Serializer.PrinterVoyancesPieChart;
import action.Action;
import action.ActionAfficherDetailsMedium;
import action.ActionConnexionEmploye;
import action.ActionRecupererDataBarChartEmploye;
import action.ActionRecupererDataBarChartMedium;
import action.ActionRecupererDataPieChart;
import action.ActionRecupererDemandeVoyance;
import action.ActionRecupererHistoriqueClient;
import action.ActionRecupererMediums;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Client;
import models.Employe;

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
                Action act = new ActionConnexionEmploye();
                if(act.execute(request))
                {
                    response.setStatus(200);
                    try (PrintWriter out = response.getWriter()) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
                        JsonObject jsonEmp = new JsonObject();
                        jsonEmp.addProperty("status", "success");
                        
                        JsonObject container = new JsonObject();
                        container.add("personne", jsonEmp);
                        
                        out.println(gson.toJson(container));
                   }
                }
                else
                {
                     //403 : erreur on refuse de traiter la requete
                     response.setStatus(403);
                }
                
                break;
            }
            case  "connexionClient" :
            {
                String mail = request.getParameter("mail");
                Client client = services.connexionClient(mail);
                if(client!=null)
                {
                   response.setStatus(200);
                   HttpSession session = request.getSession(true);
                   session.setAttribute("Client", client);
                   try (PrintWriter out = response.getWriter()) {
                       Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
                        JsonObject jsonClient = new JsonObject();
                        jsonClient.addProperty("id", client.getIdC());
                        jsonClient.addProperty("motDePasse", client.getMail());
                        
                        JsonObject container = new JsonObject();
                        container.add("personne", jsonClient);
                        
                        out.println(gson.toJson(container));
                   }
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
                Employe employe = services.connexionEmploye(mail);
                if(employe!=null)
                {
                   response.setStatus(200);
                   HttpSession session = request.getSession(true);
                   session.setAttribute("employe", employe);
                   try (PrintWriter out = response.getWriter()) {
                       Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
                        JsonObject jsonEmp = new JsonObject();
                        jsonEmp.addProperty("mail", mail);
                        
                        JsonObject container = new JsonObject();
                        container.add("personne", jsonEmp);
                        
                        out.println(gson.toJson(container));
                   }
                }
                else
                {
                     //403 : erreur on refuse de traiter la requete
                     response.setStatus(403);
                }
                
                break;
            }
            
            case "recupererProfilClient" :
            {
                HttpSession session = request.getSession(false);
                Client clt = (Client) session.getAttribute("Client");
                String civil, prenom,nom;
                civil = clt.getCivilite();
                prenom=clt.getPrenom();
                nom=clt.getNom();
                try (PrintWriter out = response.getWriter()) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();

                    JsonObject jsonClient = new JsonObject();
                    jsonClient.addProperty("civil", civil);
                    jsonClient.addProperty("nom", nom);
                    jsonClient.addProperty("prenom", prenom);

                    JsonObject container = new JsonObject();
                    container.add("profil", jsonClient);

                    out.println(gson.toJson(container));
                }
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
