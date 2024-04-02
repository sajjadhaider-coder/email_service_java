/*
 * package com.email.cinqd.serviceImp;
 * 
 * import java.util.ArrayList;
 * 
 * import jakarta.persistence.EntityManager; import
 * jakarta.persistence.PersistenceContext; //import
 * javax.servlet.http.HttpSession; import jakarta.servlet.http.HttpSession;
 * 
 * //import org.apache.commons.lang.math.NumberUtils; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service;
 * 
 * import com.email.cinqd.response.User;
 * 
 * @Service public class JwtUserDetailsService implements UserDetailsService {
 * 
 * private User user;
 * 
 * @Autowired private PasswordEncoder bcryptEncoder;
 * 
 * @PersistenceContext private EntityManager entityManager;
 * 
 * @Value("${spring.security.user.name}") private String userId;
 * 
 * @Value("${spring.security.user.password}") private String password;
 * 
 * private int businessId;
 * 
 * @Override public UserDetails loadUserByUsername(String userId) throws
 * UsernameNotFoundException { //ApiUser user = null; try { //Session sess =
 * HibernateSessionFactory.getSession(); //DAOFactory df =
 * DAOFactory.getInstance(); //ApiUserDAO auDAO = df.createApiUserDAO();
 * 
 * //user = auDAO.getApiDomainByLogin(userId); user = new User();
 * user.setLogin(userId); user.setPassword(password);
 * 
 * } catch (Exception e) { throw new
 * UsernameNotFoundException("User not found with username: " + userId + ", "+
 * e.getMessage()); }
 * 
 * if (user == null) { throw new
 * UsernameNotFoundException("User not found with username: " + userId); }
 * 
 * return new
 * org.springframework.security.core.userdetails.User(user.getLogin(),
 * bcryptEncoder.encode(user.getPassword()), new ArrayList<>()); }
 * 
 * public UserDetails loadUserByUserIdPassword(String userId, String password,
 * String domainName,HttpSession session) throws UsernameNotFoundException { try
 * { //HibernateSessionFactory.rebuildSessionFactory();
 * //entityManager.refresh(arg0); //DAOFactory df = DAOFactory.getInstance();
 * //ApiUserDAO auDAO = df.createApiUserDAO();
 * 
 * user = this.getApiUserByLoginPasswordDomain(userId, password, domainName);
 * businessId = user.getBusinessId(); user.setPassword(password);
 * user.setLogin(userId);
 * 
 * } catch (Exception e) { throw new
 * UsernameNotFoundException("User not found with username: " + userId); }
 * 
 * if (user == null) { throw new
 * UsernameNotFoundException("User not found with username: " + userId); }
 * 
 * //session.setAttribute(ConstantList.PARAM_EVENT_ID, user.getBusinessId());
 * return new
 * org.springframework.security.core.userdetails.User(user.getLogin(),
 * bcryptEncoder.encode(user.getPassword()), new ArrayList<>()); }
 * 
 * private User getApiUserByLoginPasswordDomain(String userId, String password,
 * String domainName) { User result = new User(); //result = listDomain.get(0);
 * result.setBusinessId(Integer.valueOf(domainName)); result.setPassword(password);
 * result.setLogin(userId);
 * 
 * return result; }
 * 
 * public int getBusinessId () { return businessId; }
 * 
 * @Bean public PasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * }
 */