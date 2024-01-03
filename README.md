श्रीराम जयम् !!!


# blog-app-apis
Apis with basic auth in spring boot. We can create users, post, comments,etc.

This is a learning project for my own.
It's working perfectly



Firstly we have created the enitites.
Then, we have created the repositry interface by implmenting the JpaRepositry.
Then we moved towards the service layer, in which we created the interface and its implementation class.
Afterwards we started with the DTO layer where we have created the same object as the entity objects because we dont want to expose our entity layer, So we are taking DTO object from the client as an input and then we are converting the object into the original ones and then performing all the operation accordingly in the service class.
After that we shifted to the controller layer where we provided all the endpoints, So that client can access it.
Then we added the spring boot stater security dependency which enables the deafult security and to customise it we have created the configuration class, where we have created the different beans.
Inorder to secure the endpoints we have to create another class(CustomUserDetailService) which implements UserDetailService interface, the purpose of this class is to overrides the loadUserByUsername(), which leads us to chng our implementation of our User entity beacause loadUserByUsername() wants UserDetails in return, So in our User class also we implemented the UserDetails which leads us to overrides several methods, which results in we can able to return the User object as in return instead of UserDetails object.


APPLICATION.PROPERTIES 
we have created the two different profiles we run the application on two different servers.


Also, we have handle the exceptions as well with @RestControllerAdvice and @ExceptionHandler(ClassName.class) 
we have created our own exception class(ResourceNotFoundException) as well which extends RuntimeException class  





श्रीराम जयम् !!!
