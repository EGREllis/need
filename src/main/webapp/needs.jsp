<html>
    <head>
        <title>Enter your need</title>
        <script>
            var needs = [];

            function clearNeeds() {
                needs = [];
            }

            function updateNeeds() {
                needs[needs.length] = [document.getElementById('addend').value, document.getElementById('addend_bid').value];
                renderNeeds();
            }

            function removeNeed(index) {
                if (index == 0) {
                  needs.shift();
                } else if (index + 1 == needs.length) {
                  needs.pop();
                } else {
                  needs = needs.slice(0, index).concat(needs.slice(index+1, needs.length));
                }
                renderNeeds();
            }

            function renderNeeds() {
                var header = "<table border='2'><tr><th>Need</th><th>Bid</th><th>Add/Remove</th></tr>";
                var footer = "<tr><td><input type='text' id='addend' /></td><td><input type='text' id='addend_bid'/></td><td><input type='button' value='+' onclick='updateNeeds()'/></td></tr><tr><td colspan='2'><input type='submit' value='Commit' /></td><td><input type='button' value='Clear' onclick='clearNeeds()'</td></tr></table>";

                var body = ""
                for (var i = 0; i < needs.length; i++) {
                    body += "<tr><td>"+needs[i][0]+"</td><td>"+needs[i][1]+"</td><td><input type='button' value='-' onclick='removeNeed("+i+")' /></td></tr>"
                }

                var ele = document.getElementById("content");
                ele.innerHTML = header+body+footer;
            }
        </script>
    </head>
    <body onload="renderNeeds()">
        <h1>Please enter your needs:</h1>
        <form method="POST" action="need">
            <div id="content">
            </div>
        </form>
    </body>
</html>