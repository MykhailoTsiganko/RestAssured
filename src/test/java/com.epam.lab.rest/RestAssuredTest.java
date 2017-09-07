package com.epam.lab.rest;

import com.jayway.restassured.RestAssured;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.lab.rest.dto.Book;
import org.junit.runner.RunWith;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

@RunWith(DataDrivenTestRunner.class)
public class RestAssuredTest {

    @BeforeClass
    public static void setUp() {
        String port = System.getProperty("server.port");
        if (port == null) {
            com.jayway.restassured.RestAssured.port = Integer.valueOf(8080);
        }
        else{
            com.jayway.restassured.RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/MykhailoTsyhankoLibraryService/libraryREST/";
        }
        com.jayway.restassured.RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://localhost";
        }
        com.jayway.restassured.RestAssured.baseURI = baseHost;

    }

    @Test
    @DataLoader(filePaths = {"data.csv"},loaderType = LoaderType.CSV)
    public void firsAssuredTest(@Param(name="Name")String name,@Param(name="Author")String author,@Param(name="Genre")String genre){
        System.out.println(name);

        Book book = given().when().get("book/Clarissa").then().statusCode(200).extract().as(Book.class);
        System.out.println(book);

    }
}
