package com.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Model.Item;
import com.Services.ItemService;
import com.Services.ItemServiceImpl;

@WebServlet("/items/*")
public class ItemController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ItemService itemService = new ItemServiceImpl();
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	itemService.addItem(new Item("Paper","Bundle","Stationary"));
    	itemService.addItem(new Item("Laptop","Nos","Assest"));
    	super.init();
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "/";
        }

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertItem(request, response);
                    break;
                case "/delete":
                    deleteItem(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateItem(request, response);
                    break;
                default:
                    listItems(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listItems(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Item> listItem = itemService.getAllItems();
        request.setAttribute("listItem", listItem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/item-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/item-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Item existingItem = itemService.getItemById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/item-form.jsp");
        request.setAttribute("item", existingItem);
        dispatcher.forward(request, response);
    }

    private void insertItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
    	String measure = request.getParameter("measure");;
    	String category = request.getParameter("category");;
    	Item newItem = new Item(name,measure,category);

        itemService.addItem(newItem);
        response.sendRedirect("list");
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
    	int quantity = Integer.parseInt( request.getParameter("quantity"));
    	String measure = request.getParameter("measure");;
    	String category = request.getParameter("category");;

        Item item = new Item(id, name,quantity,measure,category);
        itemService.updateItem(item);
        System.out.println("Update Called");

        response.sendRedirect("list");

    }

    private void deleteItem(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        itemService.deleteItem(id);
        System.out.println("Delete Called");

        response.sendRedirect("list");
    }
}