package GeneralSeleniumFunctionalityTests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testdata.DProviders;

import java.util.HashMap;

@Listeners(core.Listeners.class)
public class DataProvider {

    @Test(dataProvider = "usernameAndPasswordTest", dataProviderClass = DProviders.class)
    public void testIt(String username, String password){
        System.out.println(username);
        System.out.println(password);
    }

    @Test(dataProvider = "hashMapDataProviderTest" , dataProviderClass = DProviders.class)
    public void testHashMapDP(HashMap<String,String> input){
        System.out.println(input.get("email"));
        System.out.println(input.get("password"));
    }

    @Test(dataProvider = "hashMapFromJSON", dataProviderClass = DProviders.class)
    public void testHashMapFromJSON(HashMap<String,String> data){
        System.out.println(data.get("email"));
        System.out.println(data.get("password"));
        System.out.println(data.get("username"));
    }
}
