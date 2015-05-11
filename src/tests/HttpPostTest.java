/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate.http;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class HttpPostTest {

    public HttpPostTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of download method, of class HttpPost.
     * 
     * !!!!!!! Cannot be tested with JUnit as the return text contains elements(datestamp etc) that cannot be anticipated
     */
    @Test
    public void testDownload() throws Exception {
        System.out.println("download");
        String link = "https://api.datamarket.azure.com/Bing/MicrosoftTranslator/v1/Translate?Text=%27hello%27&To=%27ro%27&From=%27en%27";
        String user = "berendeanicolae@gmail.com";
        String passwd = "IGCynGBUnY83apRHqQ2fut9OqUdw0x7oEl0SZNyxJR8";
        String expResult = "";
        String result = HttpPost.download(link, user, passwd);
        System.out.println(result);
        assertEquals(expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
