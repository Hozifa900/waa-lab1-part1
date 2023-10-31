package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // show calculator page

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1' required='required'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2' required='required'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("</body>");
        out.println("</html>");

        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // show result page
        PrintWriter out = response.getWriter();
        String number1 = request.getParameter("number1");
        String number2 = request.getParameter("number2");
        String op = request.getParameter("op");
        HttpSession session = request.getSession();

        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = '/calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1' required='required'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2' required='required'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("<h3>");

        double result = 0.0;
        if (op.equals("+")) {
            result = Integer.parseInt(number1) + Integer.parseInt(number2);
        }
        if (op.equals("-")) {
            result = Integer.parseInt(number1) - Integer.parseInt(number2);
        }
        if (op.equals("*")) {
            result = Integer.parseInt(number1) * Integer.parseInt(number2);
        }
        if (op.equals("/")) {
            result = Integer.parseInt(number1) / Integer.parseInt(number2);
        }
        if (result != 0.0) {
            out.println("The result of " + number1 + " " + op + " " + number2 + " = ");
            out.print(result);
            session.setAttribute("table",
                    session.getAttribute("table") == null
                            ? "<tr><td>" + number1 + "</td><td>" + op + "</td><td>"
                                    + number2 + "</td><td>" + result
                                    + "</td></tr>"
                            : session.getAttribute("table") + "<tr><td>" + number1 + "</td><td>" + op + "</td><td>"
                                    + number2 + "</td><td>" + result
                                    + "</td></tr>");
        }

        out.println("</h3>");
        out.println(session.getAttribute("table") == null ? ""
                : "<table border = '1'><hr><th>first</th><th>operation</th><th>second</th><th>result</th></tr><tbody>"
                        + session.getAttribute("table") + "</tbody></table>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

}
