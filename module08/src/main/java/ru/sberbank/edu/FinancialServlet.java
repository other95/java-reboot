package ru.sberbank.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

/**
 * Hello world!
 *
 */

@WebServlet(value = "/finance/*", loadOnStartup = 1)
public class FinancialServlet extends HttpServlet
{
    private Double percentage;
    private Double sum;
    private Integer years;

    private final Double MIN_SUM = 50000.0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        writer.write("<!DOCTYPE html>");
        writer.write("<html lang=\"ru\" >");
        writer.write("<head>");
        writer.write("<meta charset=\"UTF-8\">");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>Deposit Profitability Calculator</h1>");
        writer.write("<form method=\"POST\" action=\"\">" );
        writer.write("Amount at the time of opening: <input name=\"sum\"><br><br>");
        writer.write("The interest rate: <input name=\"percentage\"><br><br>");
        writer.write("Number of years: <input name=\"years\"><br><br>");
        writer.write("<input type=\"submit\" value=\"Calculate\">");
        writer.write("</from>");
        writer.write("</body>");
        writer.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        Writer writer = resp.getWriter();
        try {
            parseParameters(req);

            writer.write("<html><body>Calculation result :"+ sum*percentage/100*years+ "</body></html>");
        }
        catch (TooSmallAmountException ex) {
            writer.write("<html><body>"+ex.getMessage()+"</body></html>");
        }
        catch (NumberFormatException ex) {
            writer.write("<html><body>"+ex.getMessage()+"</body></html>");
        }
        catch (Exception ex) {
            writer.write("<html><body>Unknown server error</body></html>");
        }
    }

    private void parseParameters( HttpServletRequest req ) throws NumberFormatException,Exception,TooSmallAmountException {
        try {
            percentage = Double.parseDouble(req.getParameter("percentage"));
            sum = Double.parseDouble(req.getParameter("sum"));
            years = Integer.parseInt(req.getParameter("years"));
        }
        catch (NumberFormatException ex) {
            throw  new NumberFormatException("Invalid data format. Adjust the values");
        }
        if ( percentage < 0 || sum < 0 || years <0 ) {
            throw new NumberFormatException("Invalid data format. Adjust the values");
        }
        if (sum < MIN_SUM) {
            throw new TooSmallAmountException("The minimum amount at the time of opening the deposit is "+MIN_SUM+" rubles");
        }

    }

}
