package jaasAuth;

import java.io.Serializable;
import java.security.Principal;
 

public class UserPrincipal implements Principal, Serializable {
 
    private String name;
    public UserPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}