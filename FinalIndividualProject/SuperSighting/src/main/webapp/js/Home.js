/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    //functions for SuperPower

    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SuperPower',
        success: function (itemArray) {
            $.each(itemArray, function (index, item) {
                var id = item.id;
                var name = item.name;
                var price = item.price.toFixed(2);
                var quantity = item.quantity;

                var box = '<a onclick="displayIdMessage(' + id + ')"><div class="boxVMItem col-md-4 row row-eq-height" id="boxVMItem"><p>';
                box += '<div class="boxId" id="boxId">' + id + '</div>';
                box += '<div class="boxName" id="boxName">' + name + '</div>';
                box += '<div class="boxPrice" id="boxPrice">' + '$' + price + '</div>';
                box += '<div class="boxQuantity" id="boxQuantity">' + 'Quantity: ' + quantity + '</div>';
                box += '</p></div></a>';
                VMItems.append(box);

            });
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
    //functions for Location
    //
    //functions for Organization
    //
    //functions for Sighting
    //
    //functions for SuperPerson

});



