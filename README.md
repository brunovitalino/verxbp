# VerxBp

## Description

A REST API for customer registration.

## Services

- Customers;  
- Addresses;  
- Cities;  
- States;  
- Areas.  

## Sites

[Application](https://verxbp.herokuapp.com)  
[Documentation](https://verxbp.herokuapp.com/swagger-ui.html)  

### API Credentials  

To get your token, you should access the url "[/auth](https://verxbp.herokuapp.com/auth)" through POST method with content bellow:  

> {  
>     email: admin@verx.com.br  
>     password: verx123  
> }  

## Instructions to run locally

Using Postgres database, you should create two databases: verxdb and verxdb_test
> user: postgres  
> password: 123  

When you run the application for the first time, you should config database accessing the url "[/configdb](https://verxbp.herokuapp.com/configdb)". Then you can enjoy all the available features.  
