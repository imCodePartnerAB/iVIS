/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.imcode.controllers.html;

import com.imcode.entities.Person;
import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.exceptions.factories.ErrorBuilder;
import com.imcode.exceptions.wrappers.GeneralError;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.PersonService;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.services.RoleService;
import com.imcode.services.UserService;
import com.imcode.utils.MailSenderUtil;
import com.imcode.utils.StaticUtls;
import com.imcode.validators.GeneralValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.oauth.examples.sparklr.oauth.SparklrUserApprovalHandler;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

@Controller
public class AdminController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConsumerTokenServices tokenServices;

	@Autowired
    private TokenStore tokenStore;

    @Autowired
    private IvisClientDetailsService clientDetailsService;

	@Value("${Hibernate.dialect}")
	private String test;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PersonService personService;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${mail.smtp.from.address}")
	private String fromAddress;

	@Value("${mail.smtp.from.username}")
	private String fromUsername;

	@Autowired
	public OnceTimeAccessTokenService onceTimeAccessTokenService;

	@Value("${server.name}")
	private String serverName;

	@RequestMapping(value = "/oauth/users/{user}/tokens/{token}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> revokeToken(@PathVariable String user, @PathVariable String token, Principal principal)
			throws Exception {
		checkResourceOwner(user, principal);
		if (tokenServices.revokeToken(token)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping("/oauth/clients/{client}/tokens")
	@ResponseBody
	public Collection<OAuth2AccessToken> listTokensForClient(@PathVariable String client) throws Exception {
		return tokenStore.findTokensByClientId(client);
	}

    @RequestMapping(value = "/oauth/tokens", method = {RequestMethod.GET})
    public String tokenList(Model model) {
        IvisClientDetailsService ClientDetailsService =  clientDetailsService;
        List<ClientDetails> clients = clientDetailsService.listClientDetails();

        List<OAuth2AccessToken> tokens = new LinkedList<>();

        for (ClientDetails clientDetails : clients) {
            Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clientDetails.getClientId());

            if (tokensByClientId != null) {
                tokens.addAll(tokensByClientId);
            }
        }

        model.addAttribute("tokens", tokens);

        return "tokens";
    }

    @RequestMapping(value = "/oauth/tokens/{tokenVlue}", method = {RequestMethod.DELETE})
    @ResponseBody
    public boolean delete(@PathVariable("tokenVlue") String tokenValue) {
        return tokenServices.revokeToken(tokenValue);
    }

	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "display", required = false) String display,
							  WebRequest webRequest,
							  ModelAndView model) {

		boolean isPopup = display != null && "popup".equals(display);

		if (isPopup) {
			model.setViewName("security/login_popup");
		} else {
			model.setViewName("security/login");
		}

		return model;
	}

	@RequestMapping("/registration")
	public ModelAndView registration(WebRequest webRequest, ModelAndView model) {
		User user = new User();
		model.addObject(user);
		model.setViewName("security/registration");
		return model;
	}

	@RequestMapping(value = "/registration/do",
			params = {"firstName", "lastName", "email", "contactPhone"},
			method = RequestMethod.POST
	)
	public ModelAndView registrationDo(@ModelAttribute("user") User user,
												 @RequestParam("firstName") String firstName,
												 @RequestParam("lastName") String lastName,
												 @RequestParam("email") String email,
												 @RequestParam("contactPhone") String contactPhone,
									   			 BindingResult bindingResult,
												 WebRequest webRequest, ModelAndView model) throws MethodArgumentNotValidException {

		Person person = new Person();

		person.setFirstName(firstName);
		person.setLastName(lastName);

		Email emailPerson = new Email();
		emailPerson.setValue(email);
		person.setEmail(emailPerson);

		Phone phone = new Phone();
		phone.setValue(contactPhone);
		person.setPhone(phone);

		user.setPerson(person);

		Map<String, Map<GeneralValidator.Constraint, String>> constraints = new HashMap<>();

		GeneralValidator.buildField(constraints, "password",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4"),
				new SimpleEntry<>(GeneralValidator.Constraint.MATCH_WITH, "confirmPassword")
		);

		GeneralValidator.buildField(constraints, "person.firstName",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
		);

		GeneralValidator.buildField(constraints, "person.lastName",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
		);

		GeneralValidator.buildField(constraints, "person.emails",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.REGEX, GeneralValidator.EMAIL_PATTERN)
		);

		GeneralValidator.buildField(constraints, "person.phones",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.MIN, "8")
		);


		if (userService.findByUsername(user.getUsername()) != null) {
			bindingResult.reject(null, "username not unique");
		}

		if (userService.findByEmail(email) != null) {
			bindingResult.reject(null, "email not unique");
		}

		new GeneralValidator(constraints).invoke(user, bindingResult);

		StaticUtls.encodeUserPassword(user);

		OnceTimeAccessToken token = OnceTimeAccessToken.genToken(user, 60 * 24, OnceTimeAccessToken.TokenType.VERIFICATION);
		onceTimeAccessTokenService.save(token);

		String link = StaticUtls.genLinkConfirmationForOnceTimeAccessToken(token, serverName, "registration");

		String to = email;
		String subject = "Registration confirmation in iVIS";
		String text = "Thank you, " + user.getUsername() + " for registration in iVIS."
				+ " Please follow link to confirm registration: " + link;

		MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false, fromAddress, fromUsername);
		mailSenderUtil.createMessage(to, subject, text);
		mailSenderUtil.sendMessage();

		model.setViewName("redirect:/login");

		return model;
	}

	@RequestMapping(value = "/registration/confirm", params = {"access", "id"}, method = RequestMethod.GET)
	public ModelAndView registrationConfirm(@RequestParam("access") String access,
											@RequestParam("id") Long id,
											WebRequest webRequest,
											ModelAndView model) {

		OnceTimeAccessToken accessToken = onceTimeAccessTokenService.find(id);

		String message = StaticUtls.checkOnceTimeAccessToken(accessToken, access);
		if (message != null) {
			GeneralError generalError = ErrorBuilder.buildSecurityException(message);
			model.addObject(generalError);
			model.setViewName("errors/error");
			return model;
		}

		User user = (User) accessToken.getUser();

		user.setEnabled(true);

		Role roleUser = roleService.findFirstByName("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(roleUser);
		user.setRoles(roles);

		Person person = personService.save(user.getPerson());

		user.setPerson(person);

		userService.save(user);

		accessToken.setUsed(true);

		onceTimeAccessTokenService.save(accessToken);

		String to = user.getPerson().getEmails().get(CommunicationTypeEnum.HOME).getValue();
		String subject = "Registration complete success";
		String text = "Now you, " + user.getUsername() + " can use iVIS system."
				+ " You can log in " + serverName + "/login using your username and password.";

		MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false, fromAddress, fromUsername);
		mailSenderUtil.createMessage(to, subject, text);
		mailSenderUtil.sendMessage();

		model.setViewName("redirect:/login");

		return model;

	}

	@RequestMapping(value = "/registration/emailunique", method = RequestMethod.GET)
	public @ResponseBody Boolean registrationEmailCheck(@RequestParam("email") String email,
														WebRequest webRequest,
														ModelAndView model) {

		User userByEmail = userService.findByEmail(email);

		return userByEmail == null;

	}

	@RequestMapping(value = "/registration/usernameunique", method = RequestMethod.GET)
	public @ResponseBody Boolean registrationUsernameCheck(@RequestParam("username") String username,
														WebRequest webRequest,
														ModelAndView model) {

		User userByName = userService.findByUsername(username);

		return userByName == null;

	}

	@RequestMapping("/restore_password")
	public ModelAndView restorePassword(WebRequest webRequest, ModelAndView model) {
		model.setViewName("security/restore_password");
		return model;
	}

	@RequestMapping(value = "/restore_password/email", method = RequestMethod.POST)
	public ModelAndView restorePasswordEmail(@RequestParam("email") String email,
											 WebRequest webRequest,
											 ModelAndView model) {

		User userByEmail = userService.findByEmail(email);

		if (userByEmail == null) {
			GeneralError generalError = ErrorBuilder.buildValidationError(Arrays.asList("user with email "+ email + " does not exist"));
			model.addObject(generalError);
			model.setViewName("errors/error");
			return model;
		}

		OnceTimeAccessToken accessToken = OnceTimeAccessToken.genToken(
				userByEmail,
				60 * 24,
				OnceTimeAccessToken.TokenType.PASSWORD_RESET
		);
		onceTimeAccessTokenService.save(accessToken);

		String link = StaticUtls.genLinkConfirmationForOnceTimeAccessToken(accessToken, serverName, "restore_password");

		String to = email;
		String subject = "Restore password in iVIS";
		String text = "Hello, " + userByEmail.getUsername() + ". For restore password in iVIS"
				+ " please follow link  " + link;

		MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false, fromAddress, fromUsername);
		mailSenderUtil.createMessage(to, subject, text);
		mailSenderUtil.sendMessage();

		model.setViewName("redirect:/login");

		return model;
	}

	@RequestMapping(value = "/restore_password/confirm",
			params = {"access", "id"},
			method = RequestMethod.GET)
	public ModelAndView restorePasswordConfirm(@RequestParam("access") String access,
											   @RequestParam("id") Long id,
											   WebRequest webRequest,
											   ModelAndView model
											 	) {

		OnceTimeAccessToken accessToken = onceTimeAccessTokenService.find(id);

		String message = StaticUtls.checkOnceTimeAccessToken(accessToken, access);
		if (message != null) {
			GeneralError generalError = ErrorBuilder.buildSecurityException(message);
			model.addObject(generalError);
			model.setViewName("errors/error");
			return model;
		}

		accessToken.setUsed(true);

		onceTimeAccessTokenService.save(accessToken);

		User user = (User) accessToken.getUser();
		model.addObject("user", user);

		model.setViewName("security/restore_password");

		return model;
	}

	@RequestMapping(value = "/restore_password/do",
			params = {"password", "userId"},
			method = RequestMethod.POST)
	public ModelAndView restorePasswordDo(@RequestParam("password") String password,
										  @RequestParam("confirmPassword") String confirmPassword,
										  @RequestParam("userId") Long userId,
										  WebRequest webRequest,
										  ModelAndView model) throws MethodArgumentNotValidException {

		User user = userService.find(userId);
		if (user == null) {
			GeneralError generalError = ErrorBuilder.buildValidationError(Arrays.asList("user with id " + userId + " does not exist"));
			model.addObject(generalError);
			model.setViewName("errors/error");
			return model;
		}

		user.setPassword(password);
		user.setConfirmPassword(confirmPassword);

		Map<String, Map<GeneralValidator.Constraint, String>> constraints = new HashMap<>();

		GeneralValidator.buildField(constraints, "password",
				new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
				new SimpleEntry<>(GeneralValidator.Constraint.MIN, "8"),
				new SimpleEntry<>(GeneralValidator.Constraint.MATCH_WITH, "confirmPassword")
		);
		BindingResult bindingResult = new BeanPropertyBindingResult(user, "user");
		new GeneralValidator(constraints).invoke(user, bindingResult);

		StaticUtls.encodeUserPassword(user);
		userService.save(user);

		String to = user.getPerson().getEmails().get(CommunicationTypeEnum.HOME).getValue();
		String subject = "Restore password in iVIS";
		String text = "Hello, " + user.getUsername() + ". Your password has bean changed.";

		MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false, fromAddress, fromUsername);
		mailSenderUtil.createMessage(to, subject, text);
		mailSenderUtil.sendMessage();

		model.setViewName("redirect:/login");

		return model;

	}

	@RequestMapping(value = "/restore_password/emailunique", method = RequestMethod.GET)
	public @ResponseBody Boolean restorePasswordEmailCheck(@RequestParam("email") String email,
														WebRequest webRequest,
														ModelAndView model) {

		return registrationEmailCheck(email, webRequest, model);

	}

	@RequestMapping({"/", "/home", "index"})
	public String home() {
		return "default";
	}

	@RequestMapping("/errorhandler")
	public void errorHandler(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
		throw (Exception) request.getAttribute("javax.servlet.error.exception");
	}

	@RequestMapping(value = "/errorhandler", params = "body", method = RequestMethod.GET)
	public ModelAndView errorHandlerWithBody(HttpServletRequest request, ModelAndView modelAndView) throws Exception {
		GeneralError generalError = new GeneralError();
		generalError.setErrorCode(Integer.parseInt(request.getParameter("error_code")));
		generalError.setErrorMessage(request.getParameter("error_message"));
		generalError.setErrorDescription(Arrays.asList(request.getParameter("error_description").split(",")));
		modelAndView.addObject(generalError);
		modelAndView.setViewName("errors/error");
		return modelAndView;
	}

	private void checkResourceOwner(String user, Principal principal) {
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
				throw new AccessDeniedException(String.format("User '%s' cannot obtain tokens for user '%s'",
						principal.getName(), user));
			}
		}
	}

	/**
	 * @param tokenServices the consumerTokenServices to set
	 */
	public void setTokenServices(ConsumerTokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}
	
	/**
	 * @param tokenStore the tokenStore to set
	 */
	public void setTokenStore(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

    public void setClientDetailsService(IvisClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }
}
