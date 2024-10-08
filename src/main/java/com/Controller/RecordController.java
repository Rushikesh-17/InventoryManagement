package com.Controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.Model.Item;
import com.Model.Record;
import com.Services.ItemService;
import com.Services.ItemServiceImpl;
import com.Services.RecordService;
import com.Services.RecordServiceImpl;

/**
 * Servlet implementation class RecordController
 */
@WebServlet("/records/*")
@MultipartConfig
public class RecordController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RecordService recordService = new RecordServiceImpl();
	private ItemService itemService = new ItemServiceImpl();

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		recordService.addRecord(new Record("Admin", new Item("Paper", "Bundle", "Stationary"), 10, "added"));
		recordService.addRecord(new Record("Admin", new Item("Laptop", "Nos", "Assest"), 15, "added"));
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				insertRecord(request, response);
				break;
			case "/fileUpload":	
				showUploadForm(request,response);
			default:
				listRecord(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String action = request.getPathInfo();
		if (action == null) {
			action = "/";
		}

		try {
			switch (action) {
			case "/bulkInsert":
				bulkInsertRecords(request, response);
			default:
				listRecord(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	private void showUploadForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/bulkUpload.jsp");
		dispatcher.forward(request, response);
		
	}

	private void bulkInsertRecords(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Content Type: " + request.getContentType());


		Part filePart = request.getPart("file");
		if (filePart == null) {
//			response.getWriter().println("Please select a file to upload");
			return;
		}

		try {
			File tempFile = File.createTempFile("temp",".xlsx");
			filePart.write(tempFile.getAbsolutePath());

			recordService.importRecords(tempFile);

			tempFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
//			response.getWriter().println("Failed to process the file: " + e.getMessage());
		}
		response.sendRedirect("list");
	}

	private void listRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
//		List<Record> listRecord = recordService.getAllRecords();
//		recordService.getRecordCount();
//		request.setAttribute("listRecord", listRecord);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/record-list.jsp");
//		dispatcher.forward(request, response);
		int pageNumber = 1;
        int pageSize = 10;

        if (request.getParameter("page") != null) {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        List<Record> records = recordService.getPaginatedRecords(pageNumber);
        long totalRecords = recordService.getRecordCount();
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        request.setAttribute("listRecord", records);
        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/WEB-INF/JSP/record-list.jsp").forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Item> listRecord = itemService.getAllItems();
		request.setAttribute("listItems", listRecord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/record-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String user = request.getParameter("user");
		String itemName = request.getParameter("itemName");
		int usedQuantity = Integer.parseInt(request.getParameter("usedQuantity"));
		String action = request.getParameter("action");
			
		Record newRecord = new Record(user, new Item(itemName), usedQuantity, action);
		
		boolean isAdded = recordService.addRecord(newRecord);

		if (isAdded) {
			response.sendRedirect("list");
		} else {
			request.setAttribute("errorMessage", "Entered quantity exceeds available quantity.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/quantityError.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
