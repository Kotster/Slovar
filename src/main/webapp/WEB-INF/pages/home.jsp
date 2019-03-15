<%--
  Created by IntelliJ IDEA.
  User: mderzhavin
  Date: 28.01.2019
  Time: 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
    <body>
    <script type="text/javascript">
        function search() {

            var key = document.getElementById("inp").value;
            $.ajax({
                url: 'http://localhost:8081/rest/postsearch',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data:({
                    key:key
                }),
                success: function (data) {

                    $("#id").text(data.id);
                    $("#key").text(data.key);
                    $("#value").text(data.value);
                    $("#name").text(data.name);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR: " + textStatus + " - " + errorThrown);
                }
            });
        }
        function listnameDict() {
            $.ajax({
                url: 'http://localhost:8081/rest/getlistdict',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                success: function (data) {
                     var option="<option></option>";
                    for (var i=0;i<data.length;i++){
                        option+="<option id="+data[i]+">"+data[i]+"</option>";
                        console.log(option);
                    }
                    $("#dict").append(option);
                    $("#dictadd").append(option);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR: " + textStatus + " - " + errorThrown);
                }
            });
        }
        function listDict() {
            $("#dictionary").empty();
            var e=document.getElementById("dict");
            $.ajax({
                url: 'http://localhost:8081/rest/getlistrecords',
                type: 'GET',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data:({
                    string:e[e.selectedIndex].value
                }),
                success: function (data) {
                     var option="<option></option>";
                    for (var i=0;i<data.length;i++){
                        option+="<option id="+data[i].id+">"+"key:"+data[i].key+" value:"+data[i].value+"</option>";
                        console.log(option);
                    }
                    $("#dictionary").append(option);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR: " + textStatus + " - " + errorThrown);
                }
            });
        }
        function update() {
            var data={
                "key":document.getElementById("keyinput").value,
                "value":document.getElementById("valueinput").value
            };
            $.ajax({
                url: 'http://localhost:8081/rest/postupdate',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data:JSON.stringify(data),
                success: function (data) {
                    alert(data);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR: " + textStatus + " - " + errorThrown);
                }
            });
        }
        function recordadd() {
            var e=document.getElementById("dictadd");
            var data={
                "key":document.getElementById("keyadd").value,
                "value":document.getElementById("valueadd").value,
                "name":e[e.selectedIndex].value
            };
            $.ajax({
                url: 'http://localhost:8081/rest/postadd',
                type: 'PUT',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data:JSON.stringify(data),
                success: function (data) {
                    alert(data);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("ERROR: " + textStatus + " - " + errorThrown);
                }
            });
        }
        $(document).ready ( function(){
            listnameDict();
        });
    </script>

    <div>
        <h1>Select dictionary</h1>
        <select id="dict" onchange="listDict();return false;">
        </select>
        <h1>Dictionary</h1>
        <select id="dictionary">
        </select>
    </div>

    <div>
        <h1>Search</h1>
        <label for="inp">key:</label><input type="text" id="inp"/>
        <label for="inp">id:</label><h3 id="id"></h3>
        <label for="inp">key:</label><h3 id="key"></h3>
        <label for="inp">value:</label><h3 id="value"></h3>
        <label for="inp">name:</label><h3 id="name"></h3>
        <button type="button" onclick="search();return false;">Search</button>
    </div>
    <div>
        <h1>Update</h1>
        <label for="keyinput">key:</label><input id="keyinput"/>
        <label for="valueinput">value:</label><input id="valueinput"/>
        <button type="button" onclick="update();return false;">Update</button>
    </div>
    <div>
        <h1>Save</h1>
        <label>
            key:<input id="keyadd"/>
        </label>
        <label>
            value:<input id="valueadd"/>
        </label>
        <label>
            dictionary:
            <select id="dictadd">
            </select>
        </label>
        <button type="button" onclick="recordadd();return false;">check</button>
    </div>
    </body>
</html>