/**
 * 
 */
package com.quantum.steps;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Validator;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

/**
 * @author amirr
 *
 */
@QAFTestStepProvider
public class GoogleMobileFriendlyTestStepsDefs {

	@Then("I check mobileFriendly URL \"(.*?)\"")
	public void iCheckMobileFriendlyURL(String url) {
		String googleAPIKey = ConfigurationManager.getBundle().getString("google_api_key");
		if (null != googleAPIKey)
			check(url, googleAPIKey);
	}

    @Then("^I check mobileFriendly current URL$")
    public void iCheckMobileFriendlyCurrentURL() throws Throwable {
        String googleAPIKey = ConfigurationManager.getBundle().getString("google_api_key");
        if (null != googleAPIKey) {
            WebDriver orgDriver = new WebDriverTestBase().getDriver();
            String url = orgDriver.getCurrentUrl();
            check(url, googleAPIKey);
        }
    }

	private void check(String testurl, String apiKey) {
		try {
			HttpTransport httpTransport = new NetHttpTransport();
			HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
			GenericUrl url = new GenericUrl(
					"https://searchconsole.googleapis.com/v1/urlTestingTools/mobileFriendlyTest:run"
							+ "?key="+apiKey);
			JSONObject json = new JSONObject();
			json.put("url", testurl);//"https://www.google.com/");
			String requestBody = json.toString();
			HttpRequest request = requestFactory.buildPostRequest(
					url, ByteArrayContent.fromString("application/json", requestBody));
			System.out.println("request was: " + requestBody);
			HttpResponse httpResponse = request.setReadTimeout(70000).execute();

			String content = httpResponse.parseAsString();
			System.out.println("  Google Mobile Friendly Test Result  ");
			System.out.println(content);
			Validator.verifyThat(content, Matchers.equalTo("MOBILE_FRIENDLY"));
		} catch (IOException | JSONException ex) {
			ex.printStackTrace();
		}

	}

}
