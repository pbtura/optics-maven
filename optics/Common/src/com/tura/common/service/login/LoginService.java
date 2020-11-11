package com.tura.common.service.login;

import com.tura.common.dto.LoginDTO;

/**
 * Interface which defines common methods used to login or retrieve login user information.
 * 
 * @author gdunkle
 * 
 */
public interface LoginService
{
	/**
	 * Retrieved the currently logged in user from the current http session if one exists
	 * 
	 * @return
	 */
	public LoginDTO getLoggedInUser();

	/**
	 * gets the login information for the given username and password. This method doesn't do actual jee authentication
	 * that is handled by the container.
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public LoginDTO login(String username, String password) throws Exception;

	/**
	 * Lookup all user accounts by the email address and send the account(s) info to the email.
	 * 
	 * @param email
	 * @return Whether the account info was emailed successfully or not
	 */
	public Boolean emailAccountInfo(String email);
}
