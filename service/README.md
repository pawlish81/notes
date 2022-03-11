# Documentation for Rockets

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*AdminApi* | [**getRocketByAuditableData**](Apis/AdminApi.md#getrocketbyauditabledata) | **GET** /audit | Return rocket with audit search criteria
*RocketApi* | [**addRocket**](Apis/RocketApi.md#addrocket) | **POST** /rockets | Add a new rocket
*RocketApi* | [**deleteRocket**](Apis/RocketApi.md#deleterocket) | **DELETE** /rockets/{id} | Delete rocket
*RocketApi* | [**getRockets**](Apis/RocketApi.md#getrockets) | **GET** /rockets | Return rockets with search criteria
*RocketApi* | [**getRocketsByID**](Apis/RocketApi.md#getrocketsbyid) | **GET** /rockets/{id} | Return rockets with search criteria
*RocketApi* | [**updateRocket**](Apis/RocketApi.md#updaterocket) | **PUT** /rockets | Update an existing rocket


<a name="documentation-for-models"></a>
## Documentation for Models

 - [ApiError](./\Models/ApiError.md)
 - [AuditMetaData](./\Models/AuditMetaData.md)
 - [BaseId](./\Models/BaseId.md)
 - [BaseResponseWitAudit](./\Models/BaseResponseWitAudit.md)
 - [BaseResponseWitAuditAllOf](./\Models/BaseResponseWitAuditAllOf.md)
 - [GetRocketsFilter](./\Models/GetRocketsFilter.md)
 - [NewRocket](./\Models/NewRocket.md)
 - [Rocket](./\Models/Rocket.md)
 - [RocketAllOf](./\Models/RocketAllOf.md)
 - [RocketBody](./\Models/RocketBody.md)
 - [RocketList](./\Models/RocketList.md)
 - [RocketType](./\Models/RocketType.md)
 - [UpdateRocket](./\Models/UpdateRocket.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
