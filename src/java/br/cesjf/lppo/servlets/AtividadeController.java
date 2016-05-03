package br.cesjf.lppo.servlets;

import br.cesjf.lppo.Atividade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AtividadeController", urlPatterns = {"/atividade/listar.html", "/atividade/cadastrar.html"})
public class AtividadeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("listar.html")) {
            List<Atividade> lista = new ArrayList<>();
            try {
                AtividadeDAO dao = new AtividadeDAO();
                lista = dao.listaTodos();
            } catch (Exception ex) {
                Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
                lista = new ArrayList<Atividade>();
                request.setAttribute("erro", "Problema ao listar as atividades!");
            }
            request.setAttribute("atividades", lista);
            request.getRequestDispatcher("/WEB-INF/views/atividade/listar.jsp").forward(request, response);

        } else if (request.getRequestURI().contains("cadastrar.html")) {
            request.getRequestDispatcher("/WEB-INF/views/atividade/cadastrar.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().contains("cadastrar.html")) {
            Atividade novaAtiv = new Atividade();
            novaAtiv.setFuncionario(request.getParameter("funcionario"));
            novaAtiv.setDescricao(request.getParameter("descricao"));
            novaAtiv.setTipo(request.getParameter("tipo"));
            novaAtiv.setHoras(Integer.parseInt(request.getParameter("horas")));
            try {
                AtividadeDAO dao = new AtividadeDAO();

                dao.criar(novaAtiv);
            } catch (Exception ex) {
                Logger.getLogger(AtividadeController.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("listar.html?erro=Erro ao criar o Atividade!");
                return;

            }

            response.sendRedirect("listar.html");
        }
    }
}
