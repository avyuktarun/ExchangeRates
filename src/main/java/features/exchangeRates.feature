Feature: Validate foreign exchange rates for positive and negative scenarios 

Scenario Outline: Verify rates for latest and past dated foreign exchange rates 

	Given rates for foreign exchange rates 
	When user calls Get http request with <Uri> 
	Then user gets success with status code 200 
	And <base> in response body is <baseValue> 
	
	
	@Regression 
	Examples: Positive scenarios 
	|Uri 			|base  |baseValue|
	|/api/latest	|base  |EUR      |
	|/api/2020-05-08|base  |EUR	     |
	|/api/2010-01-14|base  |EUR      |      
	
	
	Scenario Outline: Verify the response from invalid url for 
	
	Given  rates for foreign exchange rates 
	When  user calls invalid Get http request with <Uri> 
	Then  user gets error message with status code 400 
	
	@NegativeTest 
	Examples: Negative scenarios 
	|Uri 			|
	|/api/      	|
	|/api/2020-02-30|
	|/api/20100114  |         