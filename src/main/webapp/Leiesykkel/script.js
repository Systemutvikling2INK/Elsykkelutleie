/**
 * Created by Knut on 15.09.2016.
 */

$(document).ready(function() {

    var selectedLocation = $("#locationSelecter").prop('selectedIndex') + 1;

    $("#locationSelecter").on('change', function() {
        $("#tabell").DataTable();
        selectedLocation = $("#locationSelecter").prop('selectedIndex') + 1;
        //$("#tabell").fnReloadAjax('rest/parking/' + selectedLocation + '/bikes/');
        $("#tabell").DataTable().ajax.url( 'rest/parking/' + selectedLocation + '/bikes/' ).load();
    });

    $("#tabell").DataTable( {
       ajax: {
           url: 'rest/parking/' + selectedLocation + '/bikes/',
           dataSrc: ''
       },
       columns: [
           {data: 'id'},
           {data: 'batteryPercentage'},
           {data: 'inUse'}
       ]
    });


    $("#addParking").click(function() {
       $.ajax({
           url: 'rest/parking/1/bikes/1',
           type: 'POST',
           data: JSON.stringify({
               id: $("#parkingId").val(),
               batteryPercentage: $("#name").val()
           }),
           contentType: 'application/json; charset=utf-8',
           dataType: 'json',
           success: function(result) {
               $('#tabell').DataTable().ajax.reload();
           }
       });
    });

    $("#reserverSykkel").click(function() {
       $.ajax({
           url: 'rest/parking/' + selectedLocation + '/bikes/' + $("#bikeId").val(),
           type: 'PUT',
           contentType: 'application/json; charset=utf-8',
           dataType: 'json',
           success: function(result) {
               $('#tabell').DataTable().ajax.reload();
           }
       });
    });
});