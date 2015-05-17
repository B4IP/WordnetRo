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
public class HttpGetTest {
    
    public HttpGetTest() {
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
     * Test of download method, of class HttpGet.
     */
    @Test
    public void testDownload() throws Exception {
        System.out.println("download");
        String link = "http://jsoup.org/";
        String expResult = "<!doctype html>\n" +
"<html>\n" +
" <head> \n" +
"  <title>jsoup Java HTML Parser, with best of DOM, CSS, and jquery</title> \n" +
"  <meta name=\"keywords\" content=\"jsoup, java html parser, home\"> \n" +
"  <meta name=\"description\" content=\"Open source Java HTML parser, with DOM, CSS, and jquery-like methods for easy data extraction.\"> \n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n" +
"  <link type=\"text/css\" rel=\"stylesheet\" href=\"/rez/style.css\"> \n" +
"  <script>\n" +
"  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
"  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
"  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
"  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n" +
"\n" +
"  ga('create', 'UA-89734-10', 'auto');\n" +
"  ga('send', 'pageview');\n" +
"\n" +
"</script> \n" +
" </head> \n" +
" <body class=\"n1-home\"> \n" +
"  <div class=\"wrap\"> \n" +
"   <div class=\"header\"> \n" +
"    <div class=\"nav-sections\"> \n" +
"     <ul> \n" +
"      <li class=\"n1-home\"><h4><a href=\"/\">jsoup</a></h4></li> \n" +
"      <li class=\"n1-news\"><a href=\"/news/\">News</a></li> \n" +
"      <li class=\"n1-bugs\"><a href=\"/bugs\">Bugs</a></li> \n" +
"      <li class=\"n1-discussion\"><a href=\"/discussion\">Discussion</a></li> \n" +
"      <li class=\"n1-download\"><a href=\"/download\">Download</a></li> \n" +
"      <li class=\"n1-api\"><a href=\"/apidocs/\">API Reference</a></li> \n" +
"      <li class=\"n1-cookbook\"><a href=\"/cookbook/\">Cookbook</a></li> \n" +
"      <li class=\"n1-try\"><a href=\"http://try.jsoup.org/\">Try jsoup</a></li> \n" +
"     </ul> \n" +
"    </div> \n" +
"   </div> \n" +
"   <div class=\"breadcrumb\"> \n" +
"    <a href=\"/\">jsoup</a> \n" +
"    <span class=\"seperator\">&raquo;</span> jsoup: Java HTML Parser \n" +
"   </div> \n" +
"   <div class=\"content\"> \n" +
"    <div class=\"col1\"> \n" +
"     <h1>jsoup: Java HTML Parser</h1> \n" +
"     <p><code>jsoup</code> is a Java library for working with real-world HTML. It provides a very convenient API for extracting and manipulating data, using the best of DOM, CSS, and jquery-like methods.</p> \n" +
"     <p><a href=\"http://whatwg.org/html\" class=\"badge badge-html5\"></a></p> \n" +
"     <p><code>jsoup</code> implements the <a href=\"http://whatwg.org/html\">WHATWG HTML5</a> specification, and parses HTML to the same DOM as modern browsers do.</p> \n" +
"     <ul> \n" +
"      <li>scrape and <a href=\"/cookbook/input/parse-document-from-string\">parse</a> HTML from a URL, file, or string</li> \n" +
"      <li><a href=\"/cookbook/extracting-data/selector-syntax\">find</a> and extract data, using DOM traversal or CSS selectors</li> \n" +
"      <li><a href=\"/cookbook/modifying-data/set-html\">manipulate</a> the HTML elements, attributes, and text</li> \n" +
"      <li><a href=\"/cookbook/cleaning-html/whitelist-sanitizer\">clean</a> user-submitted content against a safe white-list, to prevent <abbr title=\"Cross Site Scripting\">XSS</abbr> attacks</li> \n" +
"      <li><a href=\"/apidocs/org/jsoup/select/Elements.html#html()\">output</a> tidy HTML</li> \n" +
"     </ul> \n" +
"     <p>jsoup is designed to deal with all varieties of HTML found in the wild; from pristine and validating, to invalid tag-soup; jsoup will create a sensible parse tree.</p> \n" +
"     <h2>Example</h2> \n" +
"     <p>Fetch the <a href=\"http://en.wikipedia.org/wiki/Main_Page\">Wikipedia</a> homepage, parse it to a DOM, and select the headlines from the <i>In&nbsp;the&nbsp;news</i> section into a list of <a href=\"/apidocs/index.html?org/jsoup/select/Elements.html\">Elements</a> (<a href=\"http://try.jsoup.org/~LGB7rk_atM2roavV0d-czMt3J_g\">online sample</a>):</p> \n" +
"     <pre><code class=\"prettyprint\">Document doc = Jsoup.connect(\"http://en.wikipedia.org/\").get();\n" +
"Elements newsHeadlines = doc.select(\"#mp-itn b a\");\n" +
"</code></pre> \n" +
"     <p><a href=\"http://www.opensource.org/\" class=\"badge badge-os\"></a></p> \n" +
"     <h2>Open source</h2> \n" +
"     <p>jsoup is an open source project distributed under the liberal <a href=\"license\">MIT license</a>. The source code is available at <a href=\"http://github.com/jhy/jsoup/\">GitHub</a>.</p> \n" +
"     <h2>Getting started</h2> \n" +
"     <ol> \n" +
"      <li><a href=\"download\"><b>Download</b></a> the jsoup jar (version <a href=\"/news/release-1.8.2\">1.8.2</a>)</li> \n" +
"      <li>Read the cookbook <a href=\"/cookbook/introduction/\">introduction</a></li> \n" +
"      <li>Enjoy!</li> \n" +
"     </ol> \n" +
"     <h2>Development and support</h2> \n" +
"     <p>If you have any questions on how to use jsoup, or have ideas for future development, please get in touch via the <a href=\"discussion\">mailing list</a>.</p> \n" +
"     <p>If you find any issues, please <a href=\"bugs\">file a bug</a> after checking for duplicates.</p> \n" +
"     <h2>Status</h2> \n" +
"     <p>jsoup is in general release.</p> \n" +
"    </div>\n" +
"    <!-- /col1 --> \n" +
"    <div class=\"col2\"> \n" +
"     <div class=\"toc box\"> \n" +
"      <h2><a href=\"/cookbook\"></a>Cookbook contents</h2> \n" +
"      <h3>Introduction</h3> \n" +
"      <ol start=\"1\"> \n" +
"       <li><a href=\"/cookbook/introduction/parsing-a-document\">Parsing and traversing a Document</a></li> \n" +
"      </ol> \n" +
"      <h3>Input</h3> \n" +
"      <ol start=\"2\"> \n" +
"       <li><a href=\"/cookbook/input/parse-document-from-string\">Parse a document from a String</a></li> \n" +
"       <li><a href=\"/cookbook/input/parse-body-fragment\">Parsing a body fragment</a></li> \n" +
"       <li><a href=\"/cookbook/input/load-document-from-url\">Load a Document from a URL</a></li> \n" +
"       <li><a href=\"/cookbook/input/load-document-from-file\">Load a Document from a File</a></li> \n" +
"      </ol> \n" +
"      <h3>Extracting data</h3> \n" +
"      <ol start=\"6\"> \n" +
"       <li><a href=\"/cookbook/extracting-data/dom-navigation\">Use DOM methods to navigate a document</a></li> \n" +
"       <li><a href=\"/cookbook/extracting-data/selector-syntax\">Use selector-syntax to find elements</a></li> \n" +
"       <li><a href=\"/cookbook/extracting-data/attributes-text-html\">Extract attributes, text, and HTML from elements</a></li> \n" +
"       <li><a href=\"/cookbook/extracting-data/working-with-urls\">Working with URLs</a></li> \n" +
"       <li><a href=\"/cookbook/extracting-data/example-list-links\">Example program: list links</a></li> \n" +
"      </ol> \n" +
"      <h3>Modifying data</h3> \n" +
"      <ol start=\"11\"> \n" +
"       <li><a href=\"/cookbook/modifying-data/set-attributes\">Set attribute values</a></li> \n" +
"       <li><a href=\"/cookbook/modifying-data/set-html\">Set the HTML of an element</a></li> \n" +
"       <li><a href=\"/cookbook/modifying-data/set-text\">Setting the text content of elements</a></li> \n" +
"      </ol> \n" +
"      <h3>Cleaning HTML</h3> \n" +
"      <ol start=\"14\"> \n" +
"       <li><a href=\"/cookbook/cleaning-html/whitelist-sanitizer\">Sanitize untrusted HTML (to prevent XSS)</a></li> \n" +
"      </ol> \n" +
"     </div> \n" +
"    </div>\n" +
"    <!-- /col2 --> \n" +
"   </div>\n" +
"   <!-- /content--> \n" +
"   <div class=\"footer\"> \n" +
"    <b>jsoup HTML parser</b> &copy; 2009 - 2015 \n" +
"    <a href=\"http://jhy.io/\" rel=\"author\"><b>Jonathan Hedley</b></a> \n" +
"   </div> \n" +
"  </div>\n" +
"  <!-- /wrap --> \n" +
"  <script src=\"/rez/prettify.js\"></script>\n" +
"  <script>prettyPrint();</script> \n" +
" </body>\n" +
"</html>";
        String result = HttpGet.download(link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
