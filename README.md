springboot2.2 springMVC  mybatis Binobook User Management System with 

Technicals：
Backend springboot2.2.2.RELEASE springmvc mybaits
FrontEnd Lay-ui jquery freemarker html 

Database mysql jdk1.8
IDE：idea development tool 

references: 
1. https://www.layui.com/
2. http://www.lezijie.com/strict/id/16.html
3. https://spring.io/projects/spring-security
4. https://www.soapui.org/learn/
5. https://www.bilibili.com/video/BV1Ki4y1x7YC


Backend Management：
Staff Users
    Login
    Logout
    Change password
    Role-Based-Access-Control
        Administrator: Have all the permissions to all Modules
        IT Manager: Can do IT Management and Swagger-UI page
        HR Manager: Can manage all admin users setting their role and grant permission roles
        Sales Man : Can manage all customer information and customer orders
        
Customer Users
    Login
    Logout
    Self-Registration
    Cancel his or her own account
    Update his or her own account personal information
    View his own order information
    Change password

localhost:8080/bnb/adminLogin    admin  123456
localhost:8080/bnb/mallUserlogin    admin  12