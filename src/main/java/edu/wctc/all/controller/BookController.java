/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.all.controller;

import edu.wctc.all.ejb.AuthorFacade;
import edu.wctc.all.ejb.BookFacade;
import edu.wctc.all.model.Author;
import edu.wctc.all.model.Book;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alancerio18
 */
@WebServlet(name = "bookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    private final String RESULTS_PAGE = "/BookResultPage.jsp";
    private final String ADD_PAGE = "/BookAdd.jsp";
    private final String UPDATE_PAGE = "/BookUpdate.jsp";

    @Inject
    private BookFacade service;
    @Inject
    private AuthorFacade authService;

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

        String destination = RESULTS_PAGE;

        try {

            String button = request.getParameter("submit");
            if (button == null) {
                button = "";
            }
            String bookName = null;

            switch (button) {
                case "Delete":
                    String[] ids = request.getParameterValues("bookId");
                    if (ids != null) {
                        for (String id : ids) {
                            Book book = service.find(new Integer(id));
                            service.remove(book);
                        }
                        refreshList(request, service);
                        refreshAuthorList(request, authService);

                    }
                    break;
                case "Update":
                    String[] id = request.getParameterValues("bookId");
                    Integer a = Integer.parseInt(id[0]);
                    Book book = service.find(a);
                    request.setAttribute("item", book);
                    refreshList(request, service);

                    destination = UPDATE_PAGE;

                    break;

                case "Updated":
                    String bookId = request.getParameter("bookId");
                    bookName = request.getParameter("bookAdded");

                    Book updateBook = service.find(new Integer(bookId));
                    updateBook.setTitle(bookName);

                    service.edit(updateBook);
                    refreshList(request, service);
                    refreshAuthorList(request, authService);

                    destination = RESULTS_PAGE;
                    break;
                case "Add":
                    destination = ADD_PAGE;

                    List<Author> authors = authService.findAll();
                    request.setAttribute("authors", authors);

                    break;

                case "Create":
                    bookName = request.getParameter("bookAdd");
                    String bookIsbn = request.getParameter("bookIsbn");
                    String authorName = request.getParameter("bookAuthor");
                    
                    int isbn = Integer.parseInt(bookIsbn);
                    
                    Book createBook = new Book();
                    createBook.setTitle(bookName);
                    createBook.setIsbn(isbn + "");
                    
                    service.create(createBook);
                    refreshList(request, service);
                    refreshAuthorList(request, authService);

                    destination = RESULTS_PAGE;

                    break;
                default:
                    refreshList(request, service);
                    refreshAuthorList(request, authService);
                    destination = RESULTS_PAGE;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestDispatcher view
                = request.getRequestDispatcher(destination);

        view.forward(request, response);
    }

    private void refreshList(HttpServletRequest request, BookFacade service) throws Exception {
        List<Book> books = service.findAll();
        request.setAttribute("books", books);
    }

    private void refreshAuthorList(HttpServletRequest request, AuthorFacade authService) throws Exception {
        List<Author> authors = authService.findAll();
        request.setAttribute("authors", authors);

    }

    @Override
    public void init() throws ServletException {

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
