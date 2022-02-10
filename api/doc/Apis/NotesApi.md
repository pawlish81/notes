# NotesApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addNote**](NotesApi.md#addNote) | **POST** /notes | Add a new note
[**deleteNote**](NotesApi.md#deleteNote) | **DELETE** /notes/{id} | Delete note
[**getNotes**](NotesApi.md#getNotes) | **GET** /notes | Return notes with search criteria
[**updateNote**](NotesApi.md#updateNote) | **PUT** /notes | Update an existing note


<a name="addNote"></a>
# **addNote**
> Note addNote(title, text, tag)

Add a new note

    Creates a new note in the store. Duplicates are allowed

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **title** | **String**| Note title | [default to null]
 **text** | **String**| Note text | [default to null]
 **tag** | **String**| note tag | [optional] [default to null] [enum: BUSINESS, PERSONAL, IMPORTANT]

### Return type

[**Note**](../\Models/Note.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="deleteNote"></a>
# **deleteNote**
> deleteNote(id)

Delete note

    Delete a single note based on the id.

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**| ID of note to delete | [default to null]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined

<a name="getNotes"></a>
# **getNotes**
> NoteList getNotes(page, size, id, title, tag)

Return notes with search criteria

    Returns all notes from the syst

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **page** | **Integer**| page | [default to 0]
 **size** | **Integer**| size | [default to 100]
 **id** | **String**| note id | [optional] [default to null]
 **title** | **String**| note title | [optional] [default to null]
 **tag** | **String**| note tag | [optional] [default to null] [enum: BUSINESS, PERSONAL, IMPORTANT]

### Return type

[**NoteList**](../\Models/NoteList.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="updateNote"></a>
# **updateNote**
> Note updateNote(updatedNote)

Update an existing note

    Update an existing note by Id

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **updatedNote** | [**UpdatedNote**](../\Models/UpdatedNote.md)|  |

### Return type

[**Note**](../\Models/Note.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

