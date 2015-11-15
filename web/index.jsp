
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="style/styles.css">
    <title>Web-Project with XML</title>
  </head>
  <body>

      <div class="enter">
          <div class="enter-screen">
              <div class="app-title">
                  <h1>Choose XML and Parser</h1>
              </div>

              <div class="enter-form">
                  <div class="control-group">
                      <form action="controller" enctype="multipart/form-data" method="post">
                          <input type="hidden" name="command" value="parse_command">
                          <input type="hidden" name="recordsPerPage" value="5"/>
                          <input type="file" name="file" class="login-field" id="login-name" accept=".xml">
                          <div class="radio">
                              <input class="rad" type="radio" name="parser" value="dom"/> DOM parser<br/>
                              <input class="rad" type="radio" name="parser" value="sax"/> SAX parser<br/>
                              <input class="rad" type="radio" name="parser" value="stax"/> StAX parser
                          </div>
                          <input type="submit" class="btn btn-primary btn-large btn-block" value="create table">
                      </form>
                  </div>

              </div>
          </div>
      </div>

  </body>
</html>
