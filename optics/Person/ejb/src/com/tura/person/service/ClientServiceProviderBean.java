package com.tura.person.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.UserTransaction;
import javax.validation.Validator;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.granite.messaging.service.annotations.RemoteDestination;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.infinispan.util.logging.LogFactory;
import org.jboss.weld.injection.producer.Injector;

import com.tura.common.Functions;
import com.tura.common.domain.DataAuthor;
import com.tura.common.domain.Person;
import com.tura.common.domain.Person_;
import com.tura.common.domain.Role;
import com.tura.common.domain.Role_;
import com.tura.common.domain.SessionActivity;
import com.tura.common.dto.ConstraintViolationDTO;
import com.tura.common.dto.LoginDTO;
import com.tura.common.service.AbstractParameter;
import com.tura.common.service.ListParameter;
import com.tura.common.service.RecordContentionException;
import com.tura.common.service.ServiceException;
import com.tura.common.service.context.PushSessionContext;
import com.tura.common.service.interceptor.NullParameterCheck;
import com.tura.common.service.login.LoginService;
import com.tura.common.service.persistence.PersistenceHelper;
import com.tura.common.service.search.SearchService;



/**
 * This client service provider facades the main product business service and provides extra functionality which is
 * specifically needed for the client view.
 * 
 * @author pbuchheit
 */
@Stateless(name = "ClientServiceProvider")
@Local(ClientServiceProvider.class)
@PushSessionContext
@SuppressWarnings("unchecked")
@Interceptors({ NullParameterCheck.class })
@RemoteDestination(id = "clientServiceProvider", channel = "graniteamf", factory = "tideEjbFactory", securityRoles = {
		"administrator", "tura", "user_maintenance" })
@TransactionManagement(TransactionManagementType.BEAN)
public class ClientServiceProviderBean implements com.tura.common.service.context.SessionContext, ClientServiceProvider
{
	private static org.infinispan.util.logging.Log log = LogFactory.getLog(ClientServiceProviderBean.class);
	@PersistenceContext(name = "entityManager")
	protected EntityManager entityManager;
	@Resource
	protected UserTransaction tx;
	@EJB(name = "personService")
	protected PersonService personService;
	@EJB(name = "roleService")
	protected RoleService roleService;
	@EJB(name = "loginService")
	protected LoginService loginService;
	@EJB(name = "searchService")
	protected SearchService searchService;
  
  @Override
	public LoginDTO getLoggedInUser()
	{
		LoginDTO loginDTO = loginService.getLoggedInUser();
		return loginDTO;
	}

	@Override
	public LoginDTO login(String username, String password) throws Exception
	{
		LoginDTO loginDTO = loginService.login(username, password);
		return loginDTO;
	}

	@Override
	public Boolean emailAccountInfo(String email)
	{
		return loginService.emailAccountInfo(email);
	}
}
