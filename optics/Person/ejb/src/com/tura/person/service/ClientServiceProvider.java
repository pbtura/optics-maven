package com.tura.person.service;

import java.util.List;

import javax.ws.rs.Path;

import com.tura.common.domain.Person;
import com.tura.common.domain.Role;
import com.tura.common.dto.LoginDTO;
import com.tura.common.service.ListParameter;
import com.tura.common.service.activity.SessionActivityService;
import com.tura.common.service.login.LoginService;
import com.tura.common.test.BoomTestService;
import com.tura.person.dto.PersonDTO;
import com.tura.person.dto.PersonList;
import com.tura.person.dto.RoleDTO;
import com.tura.person.dto.RoleList;
import com.tura.person.dto.SessionActivityList;

@Path("service")
public interface ClientServiceProvider extends LoginService, SessionActivityService, BoomTestService
{
	/**
	 * Provide a list of persons. The objects in this list are detached and contain just enough information necessary to
	 * build the persons list on the client.
	 * 
	 * @param parameters
	 * @return
	 */
	public PersonList listPeople(ListPeopleParameter parameters);

	/**
	 * Retrieve the person with the given id
	 * 
	 * @param id
	 * @return
	 */
	public Person getPerson(Long id);

	/**
	 * Provide a list of roles. The objects in this list are detached and contain just enough information necessary to
	 * build the role list on the client.
	 * 
	 * @param parameters
	 * @return
	 */
	public RoleList listRoles(ListParameter parameters);

	/**
	 * Queries for a list of persons <b>not</b> in the specified role.
	 * 
	 * @param parameter
	 *            TODO
	 * @return
	 */
	public List<PersonDTO> listPotentialMembers(ListPotentialMembersParameter parameter);

	/**
	 * Queries for a list of potential child roles (ie roles not currently child roles) for the specified role id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleDTO> listPotentialChildRoles(Long roleId);

	/**
	 * Get the role with the given id
	 * 
	 * @param id
	 * @return
	 */
	public Role getRole(Long id);

	/**
	 * Add a new person to the database and return the new Person object.
	 * 
	 * @param personInfo
	 * @return
	 */
	public Person addNewPerson(NewPersonInfoParameter personInfo);

	/**
	 * Add a new person to the database and return the new Person object.
	 * 
	 * @param personInfo
	 * @return
	 */
	public Person addNewPerson(PersonInfoParameter personInfo);

	/**
	 * Update an existing person
	 * 
	 * @param personInfo
	 * @return
	 */
	public Person updatePersonInfo(UpdatePersonInfoParameter personInfo);

	/**
	 * Disable the people whose ids are in this list.
	 * 
	 * @param personIds
	 */
	public void disablePersons(List<Long> personIds);

	/**
	 * Add selected roles to the specified person
	 * 
	 * @param parameter
	 * @return
	 */
	public Person addRoles(PersonsRolesParameter parameter);

	/**
	 * Remove selected roles from the specified person
	 * 
	 * @param parameter
	 * @return
	 */
	public Person removeRoles(PersonsRolesParameter parameter);

	/**
	 * Get a list of all the roles the person can be added to
	 * 
	 * @param parameter
	 * @return
	 */
	public List<RoleDTO> getAvailableRoles(Long personId);

	/**
	 * Disable a single user account
	 * 
	 * @param personId
	 * @return
	 */
	public Person disablePerson(Long personId);

	/**
	 * @param personId
	 * @return
	 */
	public List<String> getRoleNamesForPerson(Long personId);

	public SessionActivityList getSessionActivity(SessionActivityListParameter parameter);

	/**
	 * @param personId
	 * @return
	 */
	public Boolean isEnabled(Long personId);

	public LoginDTO createLoginDTO(Person p);

	/**
	 * Create a new role with the given name. Should throw a ConstraintException if the role name is not unique
	 * 
	 * @param roleInfo
	 *            TODO
	 * @return
	 */
	public Role addNewRole(RoleInfoParameter roleInfo);

	/**
	 * Update the role info. Should throw a ConstraintException if the role name is not unique
	 * 
	 * @param name
	 * @param description
	 * @return
	 */
	public Role updateRoleInfo(UpdateRoleInfoParameter roleInfo);

	/**
	 * Add selected child roles to the given parent role
	 * 
	 * @param childRoles
	 * @return
	 */
	public Role addChildRoles(ChildRolesParameter childRoles);

	/**
	 * Remove the selected roles for the given parent role
	 * 
	 * @param childRoles
	 * @return
	 */
	public Role removeChildRoles(ChildRolesParameter childRoles);

	/**
	 * Add selected people as members of the specified role
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Role addMembers(RoleMembersParameter parameterObject);

	/**
	 * Remove the given role from the specified people
	 * 
	 * @param parameterObject
	 * @return
	 */
	public Role removeMembers(RoleMembersParameter parameterObject);

	public void updateIndexes(Boolean async);

	public Person updatePersonInfo(UpdatePersonContactInfoParameter parameter);
}
