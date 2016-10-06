$(document).ready(function() {

    //Laste inn .parking-selection items:
    var parkingJSON = $.getJSON("/rest/parking/", function(data) {
       for(var i = 0; i < data.length; i++) {
           $(".parking-selection").append('<option value="' + data[i].name+ '">' + data[i].name + '</option>');
           //$(".parking-selection").selectpicker("refresh");

       }


        var selectedLocation = $(".parking-selection").prop('selectedIndex') + 1;
        //test: {"batteryPercentage":1.0,"id":2,"inUse":false} i postman
        var syklerJSON = $.getJSON('/rest/parking/' + selectedLocation + '/bikes', function (data) {
            endreSykler(data);
        });
    });


  //Laste inn bokser med sykler
  function endreSykler(data) {
      $(".sykler").html(""); //For å sette diven til å bli tom

      if(data.length === 0) {
          $("#res-text-info").fadeOut();
          $("#ingen-sykler").fadeIn();
      } else {
          if($("#res-text-info").css('display') == 'none') {
              $("#res-text-info").fadeIn();
              $("#ingen-sykler").fadeOut();
          }
      }

      var antallSykler = 0;
      if(data.length >= 9) {
          antallSykler = 9;
      } else {
          antallSykler = data.length;
      }

      for(i = 0; i < antallSykler; i++) {
          var bikeProgress = data[i].batteryPercentage * 100;

          var progressBarKlasse = "";
          if (data[i].batteryPercentage <= 0.2) {
              //progress-bar-danger
              progressBarKlasse = "progress-bar-danger";
          } else if(data[i].batteryPercentage > 0.2 && data[i].batteryPercentage < 0.8) {
              //progress-bar-warning
              progressBarKlasse = "progress-bar-warning";
          } else if(data[i].batteryPercentage >= 0.8) {
              //progress-bar-info
              progressBarKlasse = "progress-bar-success";
          }

          $(".sykler").append('<div class="rentbike" id="bike' + data[i].id + '">' +
              '<div>' +
              '<div class="idBoks">' +
              "<p><b>ID:</b> " + data[i].id + "</p>" +
              "</div>" +
              '<div class="progress progress-boks">' +
              '<div class="progress-bar batteryBar ' + progressBarKlasse + '" id="' + data[i].id + '" role="progressbar" aria-valuenow="' + bikeProgress + '"' +
              'aria-valuemin="0" aria-valuemax="100" style="width: ' + bikeProgress + '%">' +
              '</div>' +
              '<p class="progressbar-text">' + bikeProgress + '%</p>' +
              "</div>" +
              '<div class="halla">' +
              '<svg-icon class="testaroo"><src href="/Leiesykkel/si-glyph-bicycle-1.svg" /></svg-icon>' +
              "</div>" +
              '<div class="reservasjonButton" id="button' + data[i].id + '">' +
              '<p class="button-text">Reserver sykkel</p>' +
              '</div>' +
              '</div>' +
              '</div>');
      };

      $(".rentbike").click(function() {
          //alert($(this).attr("id").split("bike")[1]);
          selectedBike = $(this).attr("id").split("bike")[1];
          $(".heisann").fadeIn();
          $("#informasjon-res").html("Vil du reservere sykkel med id: " + selectedBike + "?");
          window.location = "#mørkbakgrunn";
      });
  };
  var selectedBike = -1;

    var selectedLocation = $(".parking-selection").prop('selectedIndex') + 1;

    if ($(".parking-selection").prop('selectedIndex') === -1) {
        selectedLocation = 1;
    }


    $(".parking-selection").on('change', function() {
        selectedLocation = $(".parking-selection").prop('selectedIndex') + 1;
        var syklerJSON = $.getJSON('/rest/parking/' + selectedLocation + '/bikes', function (data) {
            endreSykler(data);
        });

    });


    $("#buttonja").click(function() {
        window.location = "#";
        //alert("Din sykkelkode er: " + Math.ceil(Math.random() * 12345));
        //alert("du trykket på: " + selectedBike);
        $.ajax({
            type: "POST",
            url: "/rest/parking/" + selectedLocation + "/bikes/" + selectedBike +"/reserver",
            success: function(data) {
                alert("Du har reservert sykkel nummer " + selectedBike + ", og kode for å hente den er: " + data);
            },
            error: function () {
                alert("Noe feil skjedde");
            }
        });
    });

    $("#buttonnei").click(function() {
        window.location = "#";
    });
});
