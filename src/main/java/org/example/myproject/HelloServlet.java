package org.example.myproject;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Welcome to my project!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h2>" + message + "</h2>");

        ArticleScraper scraper = new ArticleScraper();
        String article = scraper.getArticleTexts().get(1);  // Updated method name

        out.println("<h1>" + "this is the article:" + "</h1>");
        out.println("<p>" + article + "</p>");

        

        out.println("</body></html>");
    }

    public void destroy() {
    }
}