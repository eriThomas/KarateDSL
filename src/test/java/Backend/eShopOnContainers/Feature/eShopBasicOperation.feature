Feature: eShopOnContainers Docker Application

  Background:
    * def itemHandlerPath = 'classpath:Backend/eShopOnContainers/Feature/itemHandler.feature'
    * def CreateItem = read(itemHandlerPath + '@CREATE')
    * def ReadItem = read(itemHandlerPath + '@READ')
    * def PutItem = read(itemHandlerPath + '@PUT')
    * def PatchItem = read(itemHandlerPath + '@PATCH')
    * def DeleteItem = read(itemHandlerPath + '@DELETE')

  Scenario: Get All Items
    * def params = { pageSize: 10, pageIndex: 0 }
    * def readResponse = callonce ReadItem { itemId: '', queryParams: '#(params)', respondeStatus: 200 }
    * match readResponse.response.pageIndex == 0
    * match readResponse.response.pageSize == 10
    * match readResponse.response.count == 14
    * def findData = karate.jsonPath(readResponse.response, "$.data[?(@.name=='.NET Black & White Mug')]")[0]
    * match findData.id == 2
    * match findData.name == '.NET Black & White Mug'
    * match findData.description == '.NET Black & White Mug'
    * match findData.price == 8.5
    * match findData.catalogTypeId == 1
    * match findData.catalogType == null
    * match findData.availableStock == 89