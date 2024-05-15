package com.ttl.ito.common.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @Author: 1067382@TCS
 * @Date : 12/12/2016 Servlet to invoke or do pre-requiset activitiy.
 * 
 */
public class StartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(StartUpServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StartUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		String fileAbsolutePath = StartUpServlet.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println(" ## + fileAbsolutePath + ## [" + fileAbsolutePath + "]");
		DOMConfigurator.configure(fileAbsolutePath + "/" + "log4j.xml");
		// Log in console in and log file
		logger.info("Log4j appender configuration is successful !! !! !! !! !! !! !!");
		ServletContext context = config.getServletContext();
		logger.info("APPLICATION CONTEXT CREATED " + context);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
