package org.example.myproject;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "Welcome to my project!";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Hello Servlet</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f9; }");
        out.println(".container { max-width: 800px; margin: 50px auto; padding: 20px; background-color: #ffffff; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
        out.println("h1, h2 { color: #333; }");
        out.println("p { line-height: 1.6; }");
        out.println(".article { margin-top: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h2>" + message + "</h2>");

        ArticleScraper scraper = new ArticleScraper();
        String article;

        try {
            article = scraper.getArticleTexts().get(1); // Ensure there is an article at index 1
        } catch (IndexOutOfBoundsException e) {
            article = "No article found at index 1.";
        }

        out.println("<div class='article'>");
        out.println("<h1>Article</h1>");
        out.println("<p>" + article + "</p>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        // Clean up resources if necessary
    }
}
