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
import com.imcode.entities.User;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.oauth2.IvisClientDetailsService;
import com.imcode.services.PersonService;
import com.imcode.services.UserService;
import com.imcode.services.VerificationTokenService;
import com.imcode.utils.MailSenderUtil;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.oauth.examples.sparklr.oauth.SparklrUserApprovalHandler;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
	private PersonService personService;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VerificationTokenService verificationTokenService;

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

		personService.save(person);
		userService.save(user);

		String to = email;
		String subject = "Registration confirmation in iVIS";
		String text = "Thank you, " + user.getUsername() + " for registration in iVIS."
				+ " Please follow link to confirm registration: " ;

		MailSenderUtil mailSenderUtil = new MailSenderUtil(mailSender, false, false);
		mailSenderUtil.createMessage(to, subject, text);
		mailSenderUtil.sendMessage();

		model.setViewName("redirect:/login");

		return model;
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
