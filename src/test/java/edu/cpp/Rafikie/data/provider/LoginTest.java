package edu.cpp.Rafikie.data.provider;
import edu.cpp.Rafikie.data.Login;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jack
 */
public class LoginTest {
    
    private UserManager userManager = new FSUserManager();
    private List<String> list = new ArrayList<>();
    
    /*
    @Test
    public void testLogin() {
        String email = "pham@cpp.edu";
        String password = "123";
        boolean test = false;
        boolean result = true;
        Login user = new Login(email, password);
        
        list = userManager.isUserExist(user.getEmail());
        if (list.contains(user.getPassword()))
            result = true;
        assertEquals(test, result);
    }
*/
    
}
