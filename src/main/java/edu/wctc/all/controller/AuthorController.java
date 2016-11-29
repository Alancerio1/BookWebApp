/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.controller;

//import com.mysql.fabric.xmlrpc.base.Data;
import edu.wctc.all.model.Author;
import edu.wctc.all.service.AuthorService;
//import edu.wctc.all.model.AuthorDao;
//import edu.wctc.all.model.AuthorDaoStrategy;
//import edu.wctc.all.model.AuthorService;
//import edu.wctc.all.model.DbStrategy;
//import edu.wctc.all.model.MySqlDbStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author alancerio18
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

    private final String RESULTS_PAGE = "/ResultsPage.jsp";
    private final String ADD_PAGE = "/Add.jsp";
    private final String UPDATE_PAGE = "/Update.jsp";
//    private DbStrategy db;

    private String dbJndiName;
    
    
    private AuthorService service;

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        String destination = RESULTS_PAGE;
//        String destination = LIST_PAGE;
//        String action = request.getParameter(ACTION_PARAM);
        try {

            String button = request.getParameter("submit");
            if (button == null) {
                button = "";
            }
            String authorName = null;

            switch (button) {
                case "Delete":
                    String[] ids = request.getParameterValues("authorId");
                    if (ids != null) {
                        for (String id : ids) {
                            Author author = service.findById(id.toString());
                            service.remove(author);
                        }
                        refreshList(request, service);
                    }
                    break;
                case "Update":
                    String[] id = request.getParameterValues("authorId");
                    Integer a = Integer.parseInt(id[0]);
                    Author author = service.findByIdAndFetchBooksEagerly(a.toString());
                    // Author author = service.getAuthorById(a.toString());
                    request.setAttribute("item", author);
                    destination = UPDATE_PAGE;

                    break;

                case "Updated":
                    String authorId = request.getParameter("id");
                    authorName = request.getParameter("Added");
                    
       
                    Author updateAuthor = service.findByIdAndFetchBooksEagerly(authorId.toString());
                    updateAuthor.setAuthorName(authorName);
                    
                    service.edit(updateAuthor);
                    refreshList(request, service);
                    destination = RESULTS_PAGE;
                    break;
                case "Add":
                    destination = ADD_PAGE;
                    break;

                case "Create":
                    authorName = request.getParameter("Add");
                    Author createAuthor = new Author();
                    createAuthor.setAuthorName(authorName);
                    createAuthor.setDateAdded(new Date());
                    service.edit(createAuthor);

                    //  service.createRecord(authorName);
                    refreshList(request, service);
                    destination = RESULTS_PAGE;

                    break;
                default:
                    // must be asking to see list
                    refreshList(request, service);
                    destination = RESULTS_PAGE;

            }
//            else if (updateBtn != null && updateBtn.equals("Update")) {
//                String[] ids = request.getParameterValues("authorId");
//
//            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher view
                = request.getRequestDispatcher(destination);

        view.forward(request, response);
    }

    private void refreshList(HttpServletRequest request, AuthorService service) throws Exception {
        List<Author> authors = service.findAll();
        request.setAttribute("authors", authors);
    }

    @Override
    public void init() throws ServletException {
         ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        service = (AuthorService) ctx.getBean("authorService");
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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(AuthorController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
