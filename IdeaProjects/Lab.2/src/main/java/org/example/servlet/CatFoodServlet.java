package org.example.servlet;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.example.model.CatFood;

@WebServlet("/api/catfood")
public class CatFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");

        List<CatFood> catalog = new ArrayList<>();

        catalog.add(new CatFood("Royal Canin", "Курка та рис", 2.0, 850.50,
                "https://images.unsplash.com/photo-1548366086-7f1b76106622?w=400&h=300&fit=crop"));

        catalog.add(new CatFood("Purina Pro Plan", "Лосось", 1.5, 720.00,
                "https://images.unsplash.com/photo-1592194996308-7b43878e84a6?w=400&h=300&fit=crop"));

        catalog.add(new CatFood("Club 4 Paws", "М'ясне асорті", 5.0, 650.00,
                "https://images.unsplash.com/photo-1623387641168-d9803ddd3f35?w=400&h=300&fit=crop"));

        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(catalog));
    }
}