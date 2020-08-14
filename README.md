# z-training-spring
This repo is the backend part of the BE-Training project.
To test out the server 
1. <b>Download</b> or <b>clone</b> the repo.

2.Run following Codes in mysql to create desired database
    <b>CREATE DATABASE ztrainingdb ;
    CREATE USER 'dummyuser@localhost' IDENTIFIED BY 'dummy_password';
    GRANT ALL ON ztrainingdb.* TO 'dummyuser@localhost';</b>
   The spring-boot will create desired tables in this repo.
   
3. Run spring boot using maven - <b>mvn spring-boot:run</b>

For the frontend part please visit : <b> https://github.com/ayush29/z-training-vue </b>


