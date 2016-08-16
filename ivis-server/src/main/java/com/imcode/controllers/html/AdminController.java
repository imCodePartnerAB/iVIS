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
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.PersonService;
import com.imcode.services.OnceTimeAccessTokenService;
import com.imcode.services.RoleService;
import com.imcode.services.UserService;
import com.imcode.utils.MailSenderUtil;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.oauth.examples.sparklr.oauth.SparklrUserApprovalHandler;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.security.Principal;
import java.util.*;

/**
 * Controller for resetting the token store for testing purposes.
 * 
 * @author Dave Syer
 */
@Controller
public class AdminController {
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

//	private SparklrUserApprovalHandler userApprovalHandler;
//
//	@RequestMapping("/oauth/cache_approvals")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void startCaching() throws Exception {
//		userApprovalHandler.setUseApprovalStore(true);
//	}
//
//	@RequestMapping("/oauth/uncache_approvals")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void stopCaching() throws Exception {
//		userApprovalHandler.setUseApprovalStore(false);
//	}

//	@RequestMapping("/oauth/users/{user}/tokens")
//	@ResponseBody
//	public Collection<OAuth2AccessToken> listTokensForUser(@PathVariable String user, Principal principal)
//			throws Exception {
//		checkResourceOwner(user, principal);
//		return tokenStore.findTokensByUserName(user);
//	}

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

//        for (int i = 0; i < clients.length; i++) {
//            Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clients[i]);
//
//            if (tokensByClientId != null) {
//                tokens.addAll(tokensByClientId);
//            }
//        }

        model.addAttribute("tokens", tokens);

        return "tokens";
    }

    @RequestMapping(value = "/oauth/tokens/{tokenVlue}", method = {RequestMethod.DELETE})
    @ResponseBody
    public boolean delete(@PathVariable("tokenVlue") String tokenValue) {
        return tokenServices.revokeToken(tokenValue);
    }

//    @RequestMapping(value = "/oauth/tokens", method = RequestMethod.DELETE)
//    @ResponseBody
//    public String deleteToken(@RequestParam("tokenVlue") String tokenValue) {
////        tokenServices.revokeToken(tokenValue);
//
//        return "redirect:/oauth/tokens";
//    }

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

	@RequestMapping("/registration/do")
	public ModelAndView registrationDo(@ModelAttribute("user") User user,
												 @RequestParam("firstName") String firstName,
												 @RequestParam("lastName") String lastName,
												 @RequestParam("email") String email,
												 @RequestParam("contactPhone") String contactPhone,
												 WebRequest webRequest, ModelAndView model) {

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

	@RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
	public ModelAndView registrationConfirm(@RequestParam("access") String access,
											@RequestParam("id") Long id,
											WebRequest webRequest,
											ModelAndView model) {

		OnceTimeAccessToken accessToken = onceTimeAccessTokenService.find(id);

		String message = StaticUtls.checkOnceTimeAccessToken(accessToken, access);
		if (message != null) {
			model.addObject(message);
			model.setViewName("redirect:/oauth_error");
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

	@RequestMapping(value = "/restore_password/confirm", method = RequestMethod.GET)
	public ModelAndView restorePasswordConfirm(@RequestParam("access") String access,
											   @RequestParam("id") Long id,
											   WebRequest webRequest,
											   ModelAndView model
											 	) {

		OnceTimeAccessToken accessToken = onceTimeAccessTokenService.find(id);

		String message = StaticUtls.checkOnceTimeAccessToken(accessToken, access);
		if (message != null) {
			model.addObject(message);
			model.setViewName("redirect:/oauth_error");
			return model;
		}

		accessToken.setUsed(true);

		onceTimeAccessTokenService.save(accessToken);

		User user = (User) accessToken.getUser();
		model.addObject("user", user);

		model.setViewName("security/restore_password");

		return model;
	}

	@RequestMapping(value = "/restore_password/do", method = RequestMethod.POST)
	public ModelAndView restorePasswordDo(@RequestParam("password") String password,
										  @RequestParam("userId") Long userId,
										  WebRequest webRequest,
										  ModelAndView model) {

		User user = userService.find(userId);
		user.setPassword(password);
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

	private void checkResourceOwner(String user, Principal principal) {
		if (principal instanceof OAuth2Authentication) {
			OAuth2Authentication authentication = (OAuth2Authentication) principal;
			if (!authentication.isClientOnly() && !user.equals(principal.getName())) {
				throw new AccessDeniedException(String.format("User '%s' cannot obtain tokens for user '%s'",
						principal.getName(), user));
			}
		}
	}

//	/**
//	 * @param userApprovalHandler the userApprovalHandler to set
//	 */
//	public void setUserApprovalHandler(SparklrUserApprovalHandler userApprovalHandler) {
//		this.userApprovalHandler = userApprovalHandler;
//	}

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
