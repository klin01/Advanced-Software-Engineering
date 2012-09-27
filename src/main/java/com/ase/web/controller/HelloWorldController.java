package com.ase.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ase.domain.User;
import com.ase.service.UserService;

import java.io.IOException;
import java.util.List;

public class HelloWorldController implements Controller {

	@Autowired
	private UserService userService;
	
	protected final Log logger = LogFactory.getLog(getClass());

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Return View");
		
		//Test persistence
        userService.save( new User( "bob", "smith", "bob@bob.com", "12345" ) );
        List<User> users = userService.findAll();
        logger.info(users.size() + " users in system");

		return new ModelAndView("helloworld.jsp");
	}
}
