package testdata;

import core.TestUtilities;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DProviders {

    @DataProvider(name="usernameAndPasswordTest")
    public static Object[][] dataProviderTest(){
        Object[][] data = new Object[3][2];
        data[0][0] = "username1";
        data[0][1] = "password1";

        data[1][0] = "username2";
        data[1][1] = "password3";

        data[2][0] = "username3";
        data[2][1] = "password3";

        return data;
    }

    @DataProvider(name="hashMapDataProviderTest")
    public static Object[][] dataProviderHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("email","username1");
        map.put("password","password1");

        HashMap<String, String> map2 = new HashMap<>();
        map2.put("email","username1");
        map2.put("password","password1");

        return new Object[][] {{map},{map2}};
    }

    @DataProvider(name="hashMapFromJSON")
    public static Object[][] dataProviderHashMapFromJSON() throws IOException {

        List<HashMap<String,String>> data =  TestUtilities.getJsonDataToMap();
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}
