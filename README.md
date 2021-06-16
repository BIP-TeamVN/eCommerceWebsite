# eCommerce Website

![java](https://img.shields.io/badge/java-%23ED8B00.svg?&style=for-the-badge&logo=java&logoColor=white)
![html5](https://img.shields.io/badge/html5%20-%23E34F26.svg?&style=for-the-badge&logo=html5&logoColor=white)
![css3](https://img.shields.io/badge/css3%20-%231572B6.svg?&style=for-the-badge&logo=css3&logoColor=white)
![javascript](https://img.shields.io/badge/javascript%20-%23323330.svg?&style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![bootstrap](https://img.shields.io/badge/bootstrap%20-%23563D7C.svg?&style=for-the-badge&logo=bootstrap&logoColor=white)
![jquery](https://img.shields.io/badge/jquery%20-%230769AD.svg?&style=for-the-badge&logo=jquery&logoColor=white)
![apache](https://img.shields.io/badge/apache%20-%23D42029.svg?&style=for-the-badge&logo=apache&logoColor=white)
![mysql](https://img.shields.io/badge/mysql-%2300f.svg?&style=for-the-badge&logo=mysql&logoColor=white)

<br>

![GitHub contributors](https://img.shields.io/github/contributors/HKNP-Team/eCommerceWebsite)
![GitHub issues](https://img.shields.io/github/issues/HKNP-Team/eCommerceWebsite?color=red)
![GitHub top language](https://img.shields.io/github/languages/top/HKNP-Team/eCommerceWebsite?color=cyan)
![GitHub repo size](https://img.shields.io/github/repo-size/HKNP-Team/eCommerceWebsite)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/HKNP-Team/eCommerceWebsite)
![Github total lines](https://sloc.xyz/github/HKNP-Team/eCommerceWebsite)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/HKNP-Team/eCommerceWebsite?color=g)
![GitHub last commit](https://img.shields.io/github/last-commit/HKNP-Team/eCommerceWebsite?color=yellow)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/HKNP-Team/eCommerceWebsite)

<br>
<br>

## Overview

- Technical: **JSP** (**J**akarta **S**erver **P**ages) and **Servlet**

- Database:
    - Relational database management system : **MySQL 5.7**
    - Object-relational mapping : **Hibernate 5.4.10.Final**
    - Administrative units of Vietnam in database get
      from [GENERAL STATISTICS OFFICE OF VIETNAM](https://www.gso.gov.vn/en/homepage/)

- Front-end frameworks :
    - [**Bootstrap 4.5.3**](https://getbootstrap.com/docs/4.5/getting-started/introduction/) - Open source front end
      framework
    - [**jQuery 3.5.1**](https://jquery.com/) - Fast, small, and feature-rich JavaScript library
    - [**AJAX**]() (**A**synchronous **J**avaScript **a**nd **X**ML) - send and retrieve data from a server
      asynchronously without interfering with the display and behaviour of the existing page.
    - [**Slim Select 2**](https://slimselectjs.com/) - Slim advanced select dropdown
    - [**Argon dashboard**](https://www.creative-tim.com/product/argon-dashboard/)
      by [Creative Tim](https://www.creative-tim.com/)

- Design pattern : **Model - View - Controller (MVC)**
- Integrated development environment (IDE) : **IntelliJ IDEA 2020**

<br>
<br>

## Project Structure

<pre>
<b>eCommerceWebsite</b>
├── db
│   ├── Database.sql                  (Script MYSQL create database)
│   ├── ScriptInsert.sql              (Script MYSQL insert sample data)
│   ├── <a href="./db/vietnam-zone.xls" target="_blank">vietnam-zone.xls</a>              (Excel file contain Administrative units of Vietnam)
├── src
│   ├── main
│   │   ├── <b>java</b>
│   │   │   ├── <b>com.hknp.controller</b>
│   │   │   │   └── api               (REST API)
│   │   │   │   └── filter            (Servlet filter)
│   │   │   │   └── common            (common controller for all user's page)
│   │   │   │   └── admin             (controller for admin page)
│   │   │   │   └── delivery          (controller for delivery page)
│   │   │   │   └── employee          (controller for employee page)
│   │   │   │   └── seller            (controller for seller page)
│   │   │   │   └── web               (controller for guest & customer page)
│   │   │   ├── <b>com.hknp.interfaces</b>
│   │   │   ├── <b>com.hknp.model</b>
│   │   │   │   └── dao               (data access object with singleton pattern)
│   │   │   │   └── enity             (Entity Bean class)
│   │   │   │   └── domain
│   │   │   ├── <b>com.hknp.utils</b>
│   │   ├── <b>webapp</b>
│   │   │   ├── <b>WEB-INF</b>
│   │   │   │   └─── <a href="./src/main/webapp/WEB-INF/web.xml" target="_blank">web.xml</a>
│   │   │   ├── <b>assets</b>
│   │   │   │   └── css               (argon css && custom css)
│   │   │   │   └── fonts             (nucleo font)
│   │   │   │   └── img               (images)
│   │   │   │   └── js                (custom javascript)
│   │   │   │   └── vendor            (front-end frameworks)
│   │   │   ├── <b>common</b>                (common components for all jsp page)
│   │   │   ├── <b>view</b>
│   │   │   │   └── admin             (contain admin pages)
│   │   │   │   └── delivery          (contain delivery pages)
│   │   │   │   └── employee          (contain employee pages)
│   │   │   │   └── seller            (contain seller pages)
│   │   │   │   └── web               (contain guest & customer pages)
│   │   │   ├── index.jsp             (redirect to /home)
└── <a href="./pom.xml" target="_blank">pom.xml</a>                           (Acronym for Project Object Model)
</pre>

<br>
<br>

## How to run

Please set environment variables for SMTP mail before run.

|Variables       |Require |Description                                                                               |Default         |
|----------------|--------|------------------------------------------------------------------------------------------|----------------|
|SMTP_HOST       |❌      |The SMTP server to connect to                                                             |smtp.gmail.com  |
|SMTP_PORT       |❌      |The SMTP server port to connect to, if the connect() method doesn't explicitly specify one|587             |
|SMTP_USERNAME   |✔      |Username for SMTP Authenticator                                                           |                |
|SMTP_PASSWORD   |✔      |Password for SMTP Authenticator                                                           |                |
|SMTP_ADDRESS    |✔      |Email address to use for SMTP MAIL command. This sets the envelope return address         |                |

If use Tomcat on window, set environment variables by add this line below to file setenv.bat
```
set "SMTP_USERNAME=<YOUR_SMTP_USERNAME>"
set "SMTP_PASSWORD=<YOUR_SMTP_PASSWORD>"
set "SMTP_ADDRESS=<YOUR_SMTP_PASSWORD>"
```
