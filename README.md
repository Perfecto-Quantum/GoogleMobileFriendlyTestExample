# GoogleMobileFriendlyTest
The objective of this example is to illustrate how one can easily add Google Mobile Friendly test to your testing.
Notes:
 - You will need a Google API key
 - You need to make a small change to the POM
 - Steps are added to support Gherkin, you can browser to a page or automatically validate the page you're on
 - Google's API has been a little flaky, you can add retry
 - Google's API renders the page again in Google to do the analysis



# Some backgroud about Google Mobile friendly test:
https://testmysite.thinkwithgoogle.com/
https://developers.google.com/webmaster-tools/search-console-api/reference/rest/v1/urlTestingTools.mobileFriendlyTest/run#teststatus