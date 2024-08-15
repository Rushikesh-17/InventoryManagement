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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
			response.getWriter().println("Please select a file to upload");
			return;
		}

		try {
			File tempFile = File.createTempFile("temp",".xlsx");
			filePart.write(tempFile.getAbsolutePath());

			recordService.importRecords(tempFile);

			tempFile.delete();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/record-list.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Failed to process the file: " + e.getMessage());
		}
		response.sendRedirect("list");


	}

	private void listRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Record> listRecord = recordService.getAllRecords();
		request.setAttribute("listRecord", listRecord);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/record-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/record-form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertRecord(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String user = request.getParameter("user");
		String itemName = request.getParameter("itemName");
		int usedQuantity = Integer.parseInt(request.getParameter("usedQuantity"));
		String action = request.getParameter("action");
		Record newRecord = new Record(user, new Item(itemName), usedQuantity, action);
		recordService.addRecord(newRecord);
		response.sendRedirect("list");
	}

}
