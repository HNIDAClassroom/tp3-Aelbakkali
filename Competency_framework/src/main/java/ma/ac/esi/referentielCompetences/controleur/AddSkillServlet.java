package ma.ac.esi.referentielCompetences.controleur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ac.esi.referentielCompetences.model.Skill;
import ma.ac.esi.referentielCompetences.model.SkillDAO;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class SkillServlet
 */
public class AddSkillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSkillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String domain = request.getParameter("domain");
		String level = request.getParameter("level");

		Skill skill = new Skill(name, description, domain, level);

	
		SkillDAO skillDAO = new SkillDAO();
		boolean success = skillDAO.addSkill(skill);
			if(success) {
				request.setAttribute("message","la competence a ete ajoute avec succes.");
				
			}
			else {
				request.setAttribute("erreur,","erreur lors de l'ajout du competence");
			}
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/SkillCrud.jsp");
		//dispatcher.forward(request,response);
		 // Récupérer la liste des compétences mise à jour après l'ajout de la nouvelle compétence
	    List<Skill> skills = skillDAO.getAllSkills();
	    request.setAttribute("skills", skills);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/SkillCrud.jsp");
	    dispatcher.forward(request,response);

		//response.sendRedirect("addSkill.html");
	}

}
