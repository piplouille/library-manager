<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="com.excilys.librarymanager.model.Livre"%>
<%@page import="com.excilys.librarymanager.model.Membre"%>
<%@page import="java.util.List"%>
<%List<Livre> liste_livres = (List<Livre>) request.getAttribute("liste_livres_dispos");%>
<%List<Membre> liste_membres = (List<Membre>) request.getAttribute("liste_membres_pouvant_emprunter");%>

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
        <h1 class="page-announce-text valign">Emprunter un livre</h1>
      </div>
      <div class="row">
      <div class="container">
        <h5>Selectionnez le livre et le membre emprunteur</h5>
        <div class="row">
	      <form action="/tomcat7-maven-plugin/emprunt_add" method="post" class="col s12">
	        <div class="row">
	          <div class="input-field col s6">
	            <select id="idLivre" name="idLivre" class="browser-default">
	              <option value="" disabled selected>-- Livres --</option>
                <!-- DONE : parcourir la liste des livres disponibles et afficher autant d'options que nessaire, sur la base de l'exemple ci-dessous -->
                <c:forEach items="${liste_livres}" var="livre">
                  <option value=${livre.getId()}>"${livre.getTitre()}", de ${livre.getAuteur()}</option>
                </c:forEach>
	            </select>
	          </div>
	          <div class="input-field col s6">
	            <select id="idMembre" name="idMembre" class="browser-default">
	              <option value="" disabled selected>-- Membres --</option>
                <!-- DONE : parcourir la liste des membres pouvant emprunter et afficher autant d'options que necessaire, sur la base de l'exemple ci-dessous -->
                <c:forEach items="${liste_membres}" var="membre">
                  <option value=${membre.getKey()}>${membre.getPrenom()} ${membre.getNom()}</option>
                </c:forEach>
	            </select>
	          </div>
	        </div>
	        <div class="row center">
	          <button class="btn waves-effect waves-light" type="submit">Enregistrer l'emprunt</button>
	          <button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
	        </div>
	      </form>
	    </div>
      </div>
      </div>
    </section>
  </main>
  <jsp:include page='footer.jsp'></jsp:include>
</body>
</html>
