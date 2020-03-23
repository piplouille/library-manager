<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="com.excilys.librarymanager.model.Emprunt"%>
<%@page import="com.excilys.librarymanager.model.Livre"%>
<%@page import="com.excilys.librarymanager.model.Membre"%>
<%@page import="java.util.List"%>
<%List<Emprunt> liste = (List<Emprunt>) request.getAttribute("liste_emprunts");%>
<%int id_emprunt = (Integer) request.getAttribute("id_emprunt");%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Library Management</title>
  <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
  <jsp:include page='menu.jsp'></jsp:include>
  <main>
    <section class="content">
      <div class="page-announce valign-wrapper">
        <a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i class="material-icons">menu</i></a>
        <h1 class="page-announce-text valign">Retour d'un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Selectionnez le livre a retourner</h5>
        <div class="row">
	      <form action="/tomcat7-maven-plugin/emprunt_return" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s12">
	            <select id="id" name="id" class="browser-default">
	              <option value="" disabled selected>---</option>
                  <!-- DONE : parcourir la liste des emprunts non rendus et afficher autant d'options que necessaire, sur la base de l'exemple ci-dessous -->
                  <!-- DONE : si l'attribut id existe, l'option correspondante devra etre selectionnee par defaut (ajouter l'attribut selected dans la balise <option>) -->
                  <c:forEach items="${liste_emprunts}" var="emprunt">
                    <option value="${emprunt.getId()}" ${(emprunt.getId() == id_emprunt) ? " selected" : ""}>"${emprunt.getLivre().getTitre()}", emprunte par ${emprunt.getMembre().getPrenom()} ${emprunt.getMembre().getNom()}</option>
                  </c:forEach>
	            </select>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Retourner le livre</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
  <script>
    document.addEventListener('DOMContentLoaded', function() {
	  var elems = document.querySelectorAll('select');
	  var instances = M.FormSelect.init(elems, {});
	});
  </script>
</body>
</html>
