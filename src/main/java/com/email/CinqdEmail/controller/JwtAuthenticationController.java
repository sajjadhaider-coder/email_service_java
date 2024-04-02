/*
 * package com.email.cinqd.controller;
 * 
 * import java.util.Base64; import jakarta.servlet.http.HttpServletRequest;
 * import jakarta.servlet.http.HttpServletResponse; import
 * jakarta.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.BadCredentialsException; import
 * org.springframework.security.authentication.DisabledException; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.web.bind.annotation.CrossOrigin; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * //import com.email.cinqd.config.JwtTokenUtil; import
 * com.email.cinqd.response.JwtRequest; import
 * com.email.cinqd.response.JwtResponse; import
 * com.email.cinqd.response.ResponseTemplate; import
 * com.email.cinqd.serviceImp.JwtUserDetailsService;
 * 
 * 
 * 
 * @RestController
 * 
 * @CrossOrigin //@Api(value="Skild API", description="To get JSON Web Token",
 * tags = {"Common APIs"}) public class JwtAuthenticationController {
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * //@Autowired //private JwtTokenUtil jwtTokenUtil;
 * 
 * @Autowired private JwtUserDetailsService userDetailsService;
 * 
 * @Value("${spring.security.user.name}") private String username;
 * 
 * @Value("${spring.security.user.name}") private String password;
 * 
 * @RequestMapping(value = "/authenticate", method = RequestMethod.POST) public
 * ResponseEntity<?> createAuthenticationToken(//@RequestBody JwtRequest
 * authenticationRequest, //@RequestHeader (value = "Host", required=true)
 * String domainMapping,
 * 
 * @RequestParam String userId,
 * 
 * @RequestParam String password,
 * 
 * @RequestParam String eventId, HttpSession ses, HttpServletResponse response,
 * HttpServletRequest request) throws Exception {
 * 
 * JwtRequest authenticationRequest = new JwtRequest();
 * 
 * userId = new String(Base64.getDecoder().decode((userId))); password = new
 * String(Base64.getDecoder().decode((password)));
 * 
 * authenticationRequest.setUserId(userId);
 * authenticationRequest.setPassword(password);
 * 
 * //String domainMapping = request.getHeader("Referer"); //domainMapping =
 * StringUtils.isEmpty(domainMapping) ? "" : domainMapping; //domainMapping =
 * domainMapping.replace("https://", "").replace("http://", ""); //domainMapping
 * = domainMapping.substring(0, domainMapping.indexOf("/"));
 * //System.out.println("domainMapping:" + domainMapping);
 * 
 * response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //domainMapping =
 * StringUtils.isEmpty(domainMapping) ? "" : domainMapping; String token = "";
 * 
 * 
 * try { final UserDetails userDetails =
 * userDetailsService.loadUserByUserIdPassword(authenticationRequest.getUserId()
 * , authenticationRequest.getPassword(), domainMapping,ses);
 * 
 * final UserDetails userDetails =
 * userDetailsService.loadUserByUserIdPassword(authenticationRequest.getUserId()
 * , authenticationRequest.getPassword(), eventId ,ses);
 * 
 * authenticate(authenticationRequest.getUserId(),
 * authenticationRequest.getPassword()); token = null; //
 * jwtTokenUtil.generateToken(userDetails, userDetailsService.getEventId());
 * 
 * } catch (UsernameNotFoundException e) { return
 * ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body( new
 * ResponseTemplate(0, "Invalid credentials.")); } catch (Exception e) { return
 * ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body( new
 * ResponseTemplate(0, "Invalid credentials.")); }
 * 
 * response.setStatus(HttpServletResponse.SC_OK); return ResponseEntity.ok(new
 * JwtResponse(token));
 * 
 * }
 * 
 * @RequestMapping(value = "/register", method = RequestMethod.POST) public
 * ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
 * return ResponseEntity.ok(userDetailsService.save(user)); }
 * 
 * private void authenticate(String userId, String password) throws Exception {
 * try { authenticationManager.authenticate(new
 * UsernamePasswordAuthenticationToken(userId, password)); } catch
 * (DisabledException e) { throw new Exception("USER_DISABLED", e); } catch
 * (BadCredentialsException e) { throw new Exception("INVALID_CREDENTIALS", e);
 * } } }
 */