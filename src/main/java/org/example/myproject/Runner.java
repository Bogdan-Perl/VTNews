package org.example.myproject;

public class Runner {

    public static void main(String[] args) {
        ArticleScraper scraper = new ArticleScraper();
        String article = scraper.getArticleTexts().get(2);  // Updated method name

        System.out.println("This is artcle: " + article.replaceAll("\n", "(new line)"));

    }
}






//        try {
//            Thread.sleep(3000); // Waiting for a bit to ensure the page is loaded
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        String fakeArticle = "Kris Wernstedt, professor of urban affairs and planning in the College of Liberal Arts and Human Sciences at Virginia Tech, has been conferred the title of professor emeritus by the Virginia Tech Board of Visitors." +
//                "The emeritus title may be conferred on retired faculty members who are specially recommended to the board by Virginia Tech President Tim Sands in recognition of exemplary service to the university. Nominated individuals who are approved by the board receive a copy of the resolution and a certificate of appreciation." +
//                "A member of the Virginia Tech community for more than 17 years, Wernstedt made significant contributions to the interdisciplinary science of risk and decision making by managers in the public realm, private sector, and civil society. He helped lay the intellectual groundwork for advocacy by non-governmental organizations and states for federal reforms to better address land contamination." +
//                "Wernstedt served as principal or co-principal investigator on federal grants to research flooding, emergency management, environmental cleanup, behavioral responses to COVID, digital technology use, and climate change. He influenced the outcomes of federal programs by reviews of more than 100 proposals awarding more than $50 million, influencing the course of scientific progress and affecting communities throughout the nation." +
//                "Wernstedt promoted global missions of Virginia Tech and Virginia through appointment as a Fulbright Scholar and through visiting professorships in major East African universities, promoting research, teaching, student exchanges, and institutional collaboration of Virginians across international borders." +
//                "At Virginia Tech, Wernstedt chaired the Urban Affairs and Planning Program through reaccreditation and master’s degree curriculum reform. In addition, he directed 26 and served on an additional 60 other Ph.D. or master’s degree committees across eight different programs. He taught a wide variety of courses ranging across the full urban planning curriculum." +
//                "Wernstedt received his bachelor's degree from Western Michigan University, a master’s degree from the University of Wisconsin, and a master’s degree and Ph.D. from Cornell University." +
//                "Related stories" +
//                "Researchers examine impact of digital information on COVID-19 decisions and behavior" +
//                "Fulbright scholar headed to Tanzania to teach and research solid waste practices";

