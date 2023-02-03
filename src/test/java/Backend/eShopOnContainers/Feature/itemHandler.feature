####
#
# Handler File: All Operation For Item
#
####
Feature: Item Handler

Background:
    * url PRODUCT_URL
    * def itemId = itemId = typeof itemId != "undefined" ? itemId : {}
    * def queryParameters = queryParams = typeof queryParams != "undefined" ? queryParams : {}
    * def payload = requestPayload = typeof requestPayload != "undefined" ? requestPayload : {}
    * def headerParameters = headerParams = typeof headerParams != "undefined" ? headerParams : {}
    * def expectedResponseCode = respondeStatus = typeof respondeStatus != 200 ? respondeStatus : {}

  @CREATE
  Scenario: Create Item
    Given path
    And headers headerParameters
    And params queryParameters
    And request payload
    When method POST
    Then match responseStatus == expectedResponseCode

  @READ
  Scenario: Read Item
    Given path itemId
    And headers headerParameters
    And params queryParameters
    And request payload
    When method GET
    Then match responseStatus == expectedResponseCode

  @PUT
  Scenario: Put Item
    Given path itemId
    And headers headerParameters
    And params queryParameters
    And request payload
    When method PUT
    Then match responseStatus == expectedResponseCode

  @PATCH
  Scenario: Patch Item
    Given path itemId
    And headers headerParameters
    And params queryParameters
    And request payload
    When method PUT
    Then match responseStatus == expectedResponseCode

  @DELETE
  Scenario: Delete Item
    Given path itemId
    And headers headerParameters
    And params queryParameters
    And request payload
    When method PUT
    Then match responseStatus == expectedResponseCode