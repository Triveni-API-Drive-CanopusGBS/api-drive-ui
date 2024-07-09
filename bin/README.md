Admin REST API to create new users is listed in this project - 
- The following are the end points 
- POST request- http://localhost:8080/api/userprofiles/upload ---> to upload an excel sheet of users 
- POST request- http://localhost:8080/api/userprofiles ---> to upload a single user from postman or React front end 
Body for the post request - 

{
    "empId": 11050,
    "empName": "test2",
    "emailId": "test@gmail.com",
    "contactNumber": "123456789",
    "groupId": 14,
	"image": "JVBERi0xLjQKJeLjz9MKNyAwIG9iago8PAovVHlwZSAvRm9udAovU3VidHlwZ",
    "modifiedById": 5,
    "department":"Marketing_Sales",
    "designation": "Engineer",
    "activeStatus": true,
    "roleNames": ["BAN","FEB","MAR"],
    "regionNames": ["Manager","reviewer","engineer","approver"]
}


Users are stored in a database called ttl_ito_drive, list of users are read from an excel file. 
- 
