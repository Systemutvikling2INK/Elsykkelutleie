/**
 * Created by Ingunn on 27.09.2016.
 * Login and reg javascript
 */

$(document).ready(function() {

    function login(userName, password1) {
        var ok = false;
        var test = $.ajax
        ({
            type: "GET",
            url: "Login/Login.json",
            dataType: 'json',
            success: function (data) {
                for(var i = 0; i < data.length; i++) {

                    if(data[i].username.valueOf() === userName && data[i].password.valueOf() === password1) {
                        ok = true;
                        window.location = '/index2.html';
                    }
                }
                /*if(Boolean(ok) == false) {
                 alert("Feil brukernavn eller passord test");
                 }*/
            },
            error: function() {
                alert("Error");
            }
        });

    }

    function reg(username, password, password2) {
        var okbruk = false;
        var okpass = false;
        var ok = false;
        var sjekkbnogpass = $.ajax({
            type: "GET",
            url: "Login/Login.json",
            dataType: 'json',
            success: function (data) {

                if(password === password2) {
                    okpass = true;
                    for(var i = 0; i < data.length; i++) {
                        if(data[i].username.valueOf() != username) {
                            okbruk = true;
                            var lagre = $.ajax({
                                type: "POST",
                                url: "rest/User",
                                data: '{"userName": "' + username + '", "password" : "' + password + '"}',
                                contentType: 'application/json; charset=utf-8',
                                dataType: 'json',

                                success: function (data) {
                                    ok = true;
                                    alert("det gikk");
                                }
                            });
                        }
                    }
                }



            },
            error: function() {
                alert("Dette er feil");
            }
        });
    }

    $("#TESTTEST").click(function() {
        var username = $("#username").val();
        var password = $("#password").val();

        login(username, password);
    });

    $("#regknapp").click(function() {
        var username = $("#username1").val();
        var password = $("#password1").val();
        var password2 = $("#password2").val();

        reg(username, password, password2);

    });

});

