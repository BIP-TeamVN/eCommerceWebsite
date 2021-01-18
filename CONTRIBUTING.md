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
