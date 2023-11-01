package progi.ccc.backend.rest;

/*.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/

//import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;


public class StudentUserDetailsService  /*implements UserDetailsService*/ {
  /*  @Value("${progi.admin.password}")
    private String adminPasswordHash;

    @Autowired
    private ProfileService profileService;
*/
    /*@Override
    public UserDetails loadUserByUsername(String username) {
        return new User(username, adminPasswordHash, authorities(username));
    }*/

  /*  private List<GrantedAuthority> authorities(String username) {
        if ("admin".equals(username))
            return commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        Profile profile = profileService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("No user '" + username + "'")
        );
        if (student.isLead())
            return commaSeparatedStringToAuthorityList("ROLE_LEAD, ROLE_STUDENT");
        else
            return commaSeparatedStringToAuthorityList("ROLE_STUDENT");
    }
    */
}

