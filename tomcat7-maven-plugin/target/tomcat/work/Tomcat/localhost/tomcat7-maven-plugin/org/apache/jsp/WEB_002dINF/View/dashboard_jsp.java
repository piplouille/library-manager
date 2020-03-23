/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2020-03-23 08:59:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.View;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class dashboard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"UTF-8\">\n");
      out.write("  <title>Library Management</title>\n");
      out.write("  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>\n");
      out.write("  <link href=\"https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css\">\n");
      out.write("  <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n");
      out.write("  <link href=\"assets/css/custom.css\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "menu.jsp", out, false);
      out.write("\n");
      out.write("  <main>\n");
      out.write("    <section class=\"content\">\n");
      out.write("      <div class=\"page-announce valign-wrapper\">\n");
      out.write("        <a href=\"#\" data-activates=\"slide-out\" class=\"button-collapse valign hide-on-large-only\"><i class=\"material-icons\">menu</i></a>\n");
      out.write("        <h1 class=\"page-announce-text valign\">Tableau de bord</h1>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"row\">\n");
      out.write("        <div class=\"col l4 s6\">\n");
      out.write("          <div class=\"small-box bg-aqua\">\n");
      out.write("            <div class=\"inner\">\n");
      out.write("              <h3>12</h3><!-- TODO : afficher le nombre de membres ï¿½ la place de 12 -->\n");
      out.write("              <p>Membres</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"icon\">\n");
      out.write("            <ion-icon name=\"people\"></ion-icon>\n");
      out.write("            </div>\n");
      out.write("            <a href=\"membre_list\" class=\"small-box-footer\" class=\"animsition-link\">Liste des membres <i class=\"fa fa-arrow-circle-right\"></i></a>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col l4 s6\">\n");
      out.write("          <div class=\"small-box bg-green\">\n");
      out.write("            <div class=\"inner\">\n");
      out.write("              <h3>27</h3><!-- TODO : afficher le nombre de livres ï¿½ la place de 27 -->\n");
      out.write("              <p>Livres</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"icon\">\n");
      out.write("              <ion-icon name=\"book\"></ion-icon>\n");
      out.write("            </div>\n");
      out.write("            <a href=\"livre_list\" class=\"small-box-footer\" class=\"animsition-link\">Liste des livres <i class=\"fa fa-arrow-circle-right\"></i></a>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col l4 s6\">\n");
      out.write("          <div class=\"small-box bg-yellow\">\n");
      out.write("            <div class=\"inner\">\n");
      out.write("              <h3>1515</h3><!-- TODO : afficher le nombre d'emprunts ï¿½ la place de 1515 -->\n");
      out.write("              <p>Emprunts</p>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"icon\">\n");
      out.write("              <ion-icon name=\"bookmarks\"></ion-icon>\n");
      out.write("            </div>\n");
      out.write("            <a href=\"emprunt_list\" class=\"small-box-footer\" class=\"animsition-link\">Liste des emprunts <i class=\"fa fa-arrow-circle-right\"></i></a>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"container\">\n");
      out.write("\t        <div class=\"col s12\">\n");
      out.write("\t          <h5>Emprunts en cours</h5>\n");
      out.write("\t          <table class=\"striped\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Livre</th>\n");
      out.write("                        <th>Membre emprunteur</th>\n");
      out.write("                        <th>Date d'emprunt</th>\n");
      out.write("                        <th>Retour</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody id=\"results\">\n");
      out.write("                \n");
      out.write("                    <tr>\n");
      out.write("                        <td>Titre du livre, <em>de Nom de l'auteur</em></td>\n");
      out.write("                        <td>Prï¿½nom et nom du membre emprunteur</td>\n");
      out.write("                        <td>Date de l'emprunt</td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"emprunt_return?id=idDeLEmprunt\"><ion-icon class=\"table-item\" name=\"log-in\"></a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("                    \n");
      out.write("                  <!-- TODO : parcourir la liste des emprunts en cours et les afficher selon la structure d'exemple ci-dessus -->\n");
      out.write("                </tbody>\n");
      out.write("            </table>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </section>\n");
      out.write("  </main>\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write(" -->\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
