<!DOCTYPE html>
<html>
<head>
  <link href="http://a248.e.akamai.net/assets.github.com/stylesheets/bundle_github.css"
        rel="stylesheet" type="text/css">
  <script src='require.js' type='text/javascript'></script>
  <script src='markdown.js' type='text/javascript'></script>
</head>
<body id="readme">
<div id="markdown" class="wikistyle"></div>
<script type="text/javascript">
  require({paths:{text:"lib/text"}}, ["text!README.md"], function(readme) {
    document.getElementById("markdown").innerHTML = markdown.toHTML(readme);
  });
</body>
</html>
