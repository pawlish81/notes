# NotesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNote**](NotesApi.md#addNote) | **POST** /notes | Add a new note
[**deleteNote**](NotesApi.md#deleteNote) | **DELETE** /notes/{id} | Delete note
[**getNotes**](NotesApi.md#getNotes) | **GET** /notes | Return notes with search criteria
[**updateNote**](NotesApi.md#updateNote) | **PUT** /notes | Update an existing note



## addNote

> Note addNote(title, text, tag)

Add a new note

Creates a new note in the store. Duplicates are allowed

### Example

```java
// Import classes:
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.Configuration;
import com.pwl.client.models.*;
import com.pwl.client.v1.NotesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        NotesApi apiInstance = new NotesApi(defaultClient);
        String title = "title_example"; // String | Note title
        String text = "text_example"; // String | Note text
        String tag = "BUSINESS"; // String | note tag
        try {
            Note result = apiInstance.addNote(title, text, tag);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotesApi#addNote");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **title** | **String**| Note title |
 **text** | **String**| Note text |
 **tag** | **String**| note tag | [optional] [enum: BUSINESS, PERSONAL, IMPORTANT]

### Return type

[**Note**](Note.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Note created |  -  |
| **400** | Bad Request |  -  |
| **405** | Invalid input |  -  |


## deleteNote

> deleteNote(id)

Delete note

Delete a single note based on the id.

### Example

```java
// Import classes:
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.Configuration;
import com.pwl.client.models.*;
import com.pwl.client.v1.NotesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        NotesApi apiInstance = new NotesApi(defaultClient);
        String id = "id_example"; // String | ID of note to delete
        try {
            apiInstance.deleteNote(id);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotesApi#deleteNote");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of note to delete |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Deleted |  -  |
| **400** | Invalid ID supplied |  -  |


## getNotes

> NoteList getNotes(page, size, id, title, tag)

Return notes with search criteria

Returns all notes from the syst

### Example

```java
// Import classes:
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.Configuration;
import com.pwl.client.models.*;
import com.pwl.client.v1.NotesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        NotesApi apiInstance = new NotesApi(defaultClient);
        Integer page = 0; // Integer | page
        Integer size = 100; // Integer | size
        String id = "id_example"; // String | note id
        String title = "title_example"; // String | note title
        String tag = "BUSINESS"; // String | note tag
        try {
            NoteList result = apiInstance.getNotes(page, size, id, title, tag);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotesApi#getNotes");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**| page | [default to 0]
 **size** | **Integer**| size | [default to 100]
 **id** | **String**| note id | [optional]
 **title** | **String**| note title | [optional]
 **tag** | **String**| note tag | [optional] [enum: BUSINESS, PERSONAL, IMPORTANT]

### Return type

[**NoteList**](NoteList.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  -  |


## updateNote

> Note updateNote(updatedNote)

Update an existing note

Update an existing note by Id

### Example

```java
// Import classes:
import com.pwl.client.ApiClient;
import com.pwl.client.ApiException;
import com.pwl.client.Configuration;
import com.pwl.client.models.*;
import com.pwl.client.v1.NotesApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost");

        NotesApi apiInstance = new NotesApi(defaultClient);
        UpdatedNote updatedNote = new UpdatedNote(); // UpdatedNote | 
        try {
            Note result = apiInstance.updateNote(updatedNote);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling NotesApi#updateNote");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **updatedNote** | [**UpdatedNote**](UpdatedNote.md)|  |

### Return type

[**Note**](Note.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Successful operation |  -  |
| **400** | Invalid ID supplied |  -  |
| **404** | Note not found |  -  |
| **405** | Validation exception |  -  |

