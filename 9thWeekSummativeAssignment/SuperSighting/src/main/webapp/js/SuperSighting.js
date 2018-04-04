$(document).ready(function () {
    $('#errorMessages').empty();
    $('#homePage').show();
    $('#superPowerPage').hide();
    $('#organizationsPage').hide();
    $('#locationsPage').hide();
    $('#sightingsPage').hide();
    $('#superPeoplePage').hide();
    loadLastTenSightings();
});

//Home Button
$('#homeButton').on('click', function () {
    $('#errorMessages').empty();
    $('#homePage').show();
    $('#superPowerPage').hide();
    $('#organizationsPage').hide();
    $('#locationsPage').hide();
    $('#sightingsPage').hide();
    $('#superPeoplePage').hide();
    loadLastTenSightings();
});

function loadLastTenSightings() {
    $('#errorMessages').empty();
    $('#lastTenSightings').empty();


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/LastTenSightings',
        success: function (listTenSightings) {
            $.each(listTenSightings, function (index, sighting) {
                var id = sighting.sightingID;
                var date = sighting.sightingDateTime;
                var loca = sighting.location;
                var lat = sighting.location.latitude;
                var long = sighting.location.longitude;
                var result = '<a onclick="locateSighting(' + lat + ',' + long + ',' + id + ')">' + sighting.sightingDateTime + ' ' + loca.locationName + '</a><br/>'


                $('#lastTenSightings').append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }
    });
}

function locateSighting(lat, long, id) {
    $('#errorMessages').empty();
    $('#locationCoordinates').prop('src', "https://www.google.com/maps/embed/v1/place?key=AIzaSyDK75JSHh0r0wrACe8vI7rqi_LnlZoE8_g&q=" + lat.toFixed(6) + ',' + long.toFixed(6));
    $('#superPeopleList').empty();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPeopleBySighting/' + id,
        success: function (superPeople) {
            $.each(superPeople, function (index, superPerson) {
                var name = superPerson.superName;
                var type = superPerson.typeOfSuperPerson;
                var power = superPerson.superPower.superPowerName;
                var result = '<br/><p><b> ' + name + '</b><br/> Type: ' + type + '<br/> Power: ' + power + '</p> <hr/>'

                $('#superPeopleList').append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }
    });
}

//SuperPowerButton
$('#superPowersButton').on('click', function () {
    $('#errorMessages').empty();
    $('#homePage').hide();
    $('#superPowerPage').show();
    $('#organizationsPage').hide();
    $('#locationsPage').hide();
    $('#sightingsPage').hide();
    $('#superPeoplePage').hide();
    reloadSuperPowers();

});
function reloadSuperPowers() {
    $('#superPowerDropDown').empty();
    var superPowersList = $('#superPowerDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPowers',
        success: function (superPowerArray) {
            $.each(superPowerArray, function (index, superPower) {
                var id = superPower.superPowerID;
                var superPowerName = superPower.superPowerName;
                var result = '<option id="superPowerID" value="' + id + '">' + superPowerName + '</option>'
                superPowersList.append(result);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

$('#createSuperPowerButton').on('click', function () {
    $('#errorMessages').empty();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SuperSighting/SuperPower',
        data: JSON.stringify({
            superPowerName: $('#addSuperPowerName').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function (superPower) {
            $('#errorMessages').empty();
            alert("SuperPower " + superPower.superPowerName + " created!");
            $('#addSuperPowerName').val('');
            reloadSuperPowers();
        },
        error: function (error) {
            $('#errorMessages').append(error.responseJSON.message);
            $('#addSuperPowerName').val('');
        }

    });


});

$('#deleteSuperPowerButton').on('click', function () {
    $('#errorMessages').empty();
    var superPowerID = $('#superPowerDropDown option:selected').val();
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/SuperSighting/SuperPower/' + superPowerID,
        success: function () {
            reloadSuperPowers();
        },
        error: function (error) {
            $('#errorMessages').append(error.responseJSON.message);
        }
    });
});




//OrganizationsButton
$('#organizationsButton').on('click', function () {

    $('#errorMessages').empty();
    $('#homePage').hide();
    $('#superPowerPage').hide();
    $('#organizationsPage').show();
    $('#locationsPage').hide();
    $('#sightingsPage').hide();
    $('#superPeoplePage').hide();

    reloadOrganizations();
});

function reloadOrganizations() {
    $('#organizationDropDown').empty();
    var organizationsList = $('#organizationDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Organizations',
        success: function (organizationArray) {
            $.each(organizationArray, function (index, organization) {
                var id = organization.organizationID;
                var orgType = organization.typeOfOrganization;
                var orgName = organization.organizationName;
                var orgDescription = organization.organizationDescription;
                var orgAddress = organization.organizationAddress;
                var orgContact = organization.organizationContact;
                var result = '<option id="organizationID" value="' + id + '">' + orgName + '</option>'
                organizationsList.append(result);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

$('#deleteOrganizationButton').on('click', function () {
    var orgID = $('#organizationDropDown option:selected').val();
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/SuperSighting/Organization/' + orgID,
        success: function () {
            $('#editOrganizationType').val('');
            $('#editOrganizationName').val('');
            $('#editOrganizationDescription').val('');
            $('#editOrganizationAddress').val('');
            $('#editOrganizationContact').val('');
            reloadOrganizations();      
        }
    });
});

$('#organizationDropDown').on('change', function () {
    $('#errorMessages').empty();
    var orgSelected = $('#organizationDropDown option:selected').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Organization/' + orgSelected,
        success: function (organization) {
            var id = organization.organizationID;
            var orgType = organization.typeOfOrganization;
            var orgName = organization.organizationName;
            var orgDescription = organization.organizationDescription;
            var orgAddress = organization.organizationAddress;
            var orgContact = organization.organizationContact;

            $('#editOrganizationType').val(orgType),
                $('#editOrganizationName').val(orgName),
                $('#editOrganizationDescription').val(orgDescription),
                $('#editOrganizationAddress').val(orgAddress),
                $('#editOrganizationContact').val(orgContact)

            updateOrganization(organization);

        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
});

function updateOrganization(organization) {

    $('#errorMessages').empty();
    $('#editOrganizationButton').off('click');
    $('#editOrganizationButton').on('click', function () {

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/SuperSighting/Organization/' + organization.organizationID,
            data: JSON.stringify({
                organizationID: organization.organizationID,
                typeOfOrganization: $('#editOrganizationType').val(),
                organizationName: $('#editOrganizationName').val(),
                organizationDescription: $('#editOrganizationDescription').val(),
                organizationAddress: $('#editOrganizationAddress').val(),
                organizationContact: $('#editOrganizationContact').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (organization) {
                alert('Organization ' + organization.organizationName + ' successfully Changed!');
                $('#editOrganizationType').val(''),
                    $('#editOrganizationName').val(''),
                    $('#editOrganizationDescription').val(''),
                    $('#editOrganizationAddress').val(''),
                    $('#editOrganizationContact').val('')
                reloadOrganizations();
                $('#errorMessages').empty();
            },
            error: function (errors) {
                $('#errorMessages').append(errors.responseJSON.message);
                //  $('#errorMessages')
                //     .append($('<li>')
                //         .attr({ class: 'list-group-item list-group-item-danger' })
                //       .text('Error Calling Web Service.  Please Try Again Later.'));
            }

        });
    });
}



$('#createOrganizationButton').on('click', function () {
    $('#errorMessages').empty();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SuperSighting/Organization',
        data: JSON.stringify({
            typeOfOrganization: $('#addOrganizationType').val(),
            organizationName: $('#addOrganizationName').val(),
            organizationDescription: $('#addOrganizationDescription').val(),
            organizationAddress: $('#addOrganizationAddress').val(),
            organizationContact: $('#addOrganizationContact').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function (organization) {
            $('#errorMessages').empty();
            var orgName = organization.organizationName;
            alert("Organization " + orgName + " created!");
            $('#addOrganizationType').val(''),
                $('#addOrganizationName').val(''),
                $('#addOrganizationDescription').val(''),
                $('#addOrganizationAddress').val(''),
                $('#addOrganizationContact').val('')
            reloadOrganizations();
        },
        error: function (errors) {
            $('#errorMessages').append(errors.responseJSON.message);
            $('#addOrganizationType').val(''),
                $('#addOrganizationName').val(''),
                $('#addOrganizationDescription').val(''),
                $('#addOrganizationAddress').val(''),
                $('#addOrganizationContact').val('')
        }

    });

});


//LocationsButton
$('#locationsButton').on('click', function () {
    $('#errorMessages').empty();
    $('#homePage').hide();
    $('#superPowerPage').hide();
    $('#organizationsPage').hide();
    $('#locationsPage').show();
    $('#sightingsPage').hide();
    $('#superPeoplePage').hide();

    reloadLocations();
});

function reloadLocations() {
    $('#locationDropDown').empty();
    var locationsList = $('#locationDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Locations',
        success: function (locationArray) {
            $.each(locationArray, function (index, location) {
                var id = location.locationID;
                var locationName = location.locationName;
                var locationDescription = location.locationDescription;
                var locationLatitude = location.latitude;
                var locationLongitude = location.longitude;
                var result = '<option id="locationID" value="' + id + '">' + locationName + '</option>'
                locationsList.append(result);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

$('#deleteLocationButton').on('click', function () {
    var locationID = $('#locationDropDown option:selected').val();
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/SuperSighting/Location/' + locationID,
        success: function () {
            $('#editLocationName').val('');
            $('#editLocationDescription').val('');
            $('#editLocationLatitude').val('');
            $('#editLocationLongitude').val('');
            reloadLocations();
        },
        error: function (errors) {
            $('#errorMessages').empty();
            $('#errorMessages').append(errors.responseJSON.message);
        }
    });
});

$('#locationDropDown').on('change', function () {
    $('#errorMessages').empty();
    var locationSelected = $('#locationDropDown option:selected').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Location/' + locationSelected,
        success: function (location) {
            var id = location.locationID;
            var locationName = location.locationName;
            var locationDescription = location.locationDescription;
            var locationLatitude = location.latitude.toFixed(6);
            var locationLongitude = location.longitude.toFixed(6);

            $('#editLocationName').val(locationName);
            $('#editLocationDescription').val(locationDescription);
            $('#editLocationLatitude').val(locationLatitude);
            $('#editLocationLongitude').val(locationLongitude);

            updateLocation(location);

        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
});

function updateLocation(location) {

    $('#errorMessages').empty();
    $('#editLocationButton').off('click');

    $('#editLocationButton').on('click', function () {

        $.ajax({
            type: 'PUT',
            url: 'http://localhost:8080/SuperSighting/Location/' + location.locationID,
            data: JSON.stringify({
                locationID: location.locationID,
                locationName: $('#editLocationName').val(),
                locationDescription: $('#editLocationDescription').val(),
                latitude: $('#editLocationLatitude').val(),
                longitude: $('#editLocationLongitude').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (location) {
                $('#errorMessages').empty();
                alert("Location " + location.locationName + " successfully Changed!");
                $('#editLocationName').val('');
                $('#editLocationDescription').val('');
                $('#editLocationLatitude').val('');
                $('#editLocationLongitude').val('');
                reloadLocations();
            },
            error: function (errors) {
                $('#errorMessages').empty();
                $('#errorMessages').append(errors.responseJSON.message);
                //  $('#errorMessages')
                //     .append($('<li>')
                //         .attr({ class: 'list-group-item list-group-item-danger' })
                //       .text('Error Calling Web Service.  Please Try Again Later.'));
            }

        });
    });
}

$('#createLocationButton').on('click', function () {
    $('#errorMessages').empty();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/SuperSighting/Location',
        data: JSON.stringify({
            locationName: $('#addLocationName').val(),
            locationDescription: $('#addLocationDescription').val(),
            latitude: $('#addLocationLatitude').val(),
            longitude: $('#addLocationLongitude').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json',
        success: function (location) {
            $('#errorMessages').empty();
            var locationName = location.locationName;
            alert("Location " + locationName + " created!");
            $('#addLocationName').val(''),
                $('#addLocationDescription').val(''),
                $('#addLocationLatitude').val(''),
                $('#addLocationLongitude').val('')
            reloadLocations();
        },
        error: function (errors) {
            alert(errors.responseJSON.message);
            $('#errorMessages').append(errors.responseJSON.message);
            $('#addLocationName').val(''),
                $('#addLocationDescription').val(''),
                $('#addLocationLatitude').val(''),
                $('#addLocationLongitude').val('')
        }

    });

});

//SightingsButton
$('#sightingsButton').on('click', function () {
    $('#errorMessages').empty();
    $('#homePage').hide();
    $('#superPowerPage').hide();
    $('#organizationsPage').hide();
    $('#locationsPage').hide();
    $('#sightingsPage').show();
    $('#superPeoplePage').hide();

    reloadSightings();
    reloadSightingLocations();
    reloadSightingSuperPeople();
});

function reloadSightings() {
    $('#sightingDropDown').empty();
    var sightingsList = $('#sightingDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Sightings',
        success: function (sightingArray) {
            $.each(sightingArray, function (index, sighting) {
                var id = sighting.sightingID;
                var sightingDateTime = sighting.sightingDateTime;
                var sightingLocation = sighting.location;
                var result = '<option id="locationID" value="' + id + '">' + sightingDateTime + ' : ' + sightingLocation.locationName + '</option>'
                sightingsList.append(result);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

$('#deleteSightingButton').on('click', function () {
    var sightingID = $('#sightingDropDown option:selected').val();
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/SuperSighting/Sighting/' + sightingID,
        success: function () {
            $('#editSightingDateTime').val('');
            reloadSightings();
            reloadSightingLocations();
            reloadSightingSuperPeople();
        }
    });
});

function reloadSightingLocations() {
    $('#sightingLocationUpdateDropDown').empty();
    $('#sightingLocationCreateDropDown').empty();
    var sightingLocationsUpdateList = $('#sightingLocationUpdateDropDown');
    var sightingLocationsCreateList = $('#sightingLocationCreateDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Locations',
        success: function (locationArray) {
            $.each(locationArray, function (index, location) {
                var id = location.locationID;
                var locationName = location.locationName;
                var locationDescription = location.locationDescription;
                var locationLatitude = location.latitude;
                var locationLongitude = location.longitude;
                var result = '<option id="locationID" value="' + id + '">' + locationName + '</option>'
                sightingLocationsUpdateList.append(result);
                sightingLocationsCreateList.append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

function reloadSightingSuperPeople() {
    $('#sightingUpdateSuperPeopleDropDown').empty();
    $('#sightingCreateSuperPeopleDropDown').empty();
    var sightingSuperPeopleUpdateList = $('#sightingUpdateSuperPeopleDropDown');
    var sightingSuperPeopleCreateList = $('#sightingCreateSuperPeopleDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPeople',
        success: function (superPersonArray) {
            $.each(superPersonArray, function (index, superPerson) {
                var id = superPerson.superPersonID;
                var superPersonName = superPerson.superName;
                var result = '<option id="superPersonID" value="' + id + '">' + superPersonName + '</option>';
                sightingSuperPeopleUpdateList.append(result);
                sightingSuperPeopleCreateList.append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}



$('#createSightingButton').on('click', function () {

    var ids = $('#sightingCreateSuperPeopleDropDown').val();
    var id = $('#sightingLocationCreateDropDown').val();
    var newDateTime = $('#addSightingDateTime').val() + ":00";
    newDateTime = newDateTime.replace("T", " ");

    $('#errorMessages').empty();

    
    if (ids.length > 0) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/SuperSighting/Sighting/' + ids.toString() + '/' + id,
            data: JSON.stringify({
                sightingDateTime: newDateTime
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (sighting) {
                $('#errorMessages').empty();
                var sightingDate = sighting.sightingDateTime;
                alert("Sighting at " + sightingDate + " created!");
                $('#addSightingDateTime').val('');
                reloadSightingLocations();
                reloadSightingSuperPeople();
            },
            error: function (errors) {
                $('#errorMessages').append(errors.responseJSON.message);
                $('#addSightingDateTime').val('');
                reloadSightingLocations();
                reloadSightingSuperPeople();
            }

        });
    } else {
        alert("Please pick a SuperPerson/SuperPeople");
    }

});

$('#sightingDropDown').on('change', function () {
    $('#sightingUpdateSuperPeopleDropDown option').prop('selected', false);
    $('#errorMessages').empty();
    var sightingSelected = $('#sightingDropDown option:selected').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Sighting/' + sightingSelected,
        success: function (sighting) {
            var id = sighting.sightingID;
            var sightingDateTime = sighting.sightingDateTime;
            var locationID = sighting.location.locationID;
            var dateTime = sightingDateTime.replace(" ", "T");
            dateTime = dateTime.substr(0, 16);
            $('#editSightingDateTime').val(dateTime);
            $("#sightingLocationUpdateDropDown").val(locationID);
            updateSighting(sighting);
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }
    });

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPeopleBySighting/' + sightingSelected,
        success: function (listSuperPeople) {
            $.each(listSuperPeople, function (index, superPerson) {
                var id = superPerson.superPersonID;
                $('#sightingUpdateSuperPeopleDropDown option[value=' + id + ']').prop('selected', true);
            })
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }
    })

});

function updateSighting(sighting) {

    $('#errorMessages').empty();
    $('#editSightingButton').off('click');

    $('#editSightingButton').on('click', function () {
        var ids = $('#sightingUpdateSuperPeopleDropDown').val();
        var id = $('#sightingLocationUpdateDropDown').val();
        var newDateTime = $('#editSightingDateTime').val() + ":00";
        newDateTime = newDateTime.replace("T", " ");

        if (ids.length > 0) {
            $.ajax({
                type: 'PUT',
                url: 'http://localhost:8080/SuperSighting/Sighting/' + sighting.sightingID + "/" + ids.toString() + "/" + id,
                data: JSON.stringify({
                    sightingID: sighting.sightingID,
                    sightingDateTime: newDateTime,
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function (sighting) {
                    $('#errorMessages').empty();
                    alert("Sighting " + sighting.sightingDateTime + " successfully Changed!");
                    $('#editSightingDateTime').val('');
                    reloadSightings();
                    reloadSightingSuperPeople();
                    reloadSightingLocations();
                },
                error: function (errors) {
                    $('#errorMessages').empty();
                    $('#errorMessages').append(errors.responseJSON.message);

                }

            });
        } else {
            alert("Please pick a SuperPerson/SuperPeople");
        }
    });
}


$('#searchSightingsByDateButton').on('click', function () {
    $('#errorMessages').empty();
    $('#sightingsByDateDropDown').empty();
    var date = $('#dateForSightings').val();
    var sightingsListDate = $('#sightingsByDateDropDown');
    date = date.replace(/[^0-9.]/g, "");

    if (date.length == 8) {
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/SuperSighting/SightingsDate/' + date,
            success: function (sightingsList) {
                if (sightingsList.length == 0) {
                    alert("No Sightings for Inputted Date");
                } else {
                    $('#dateForSightings').val('');
                    $.each(sightingsList, function (index, sighting) {
                        var id = sighting.sightingID;
                        var sightingDateTime = sighting.sightingDateTime;
                        var locaName = sighting.location;

                        var result = '<option id="sightingID" value="' + id + '">' + sightingDateTime + ' : ' + locaName.locationName + '</option>';
                        sightingsListDate.append(result);
                    });
                }
            },
            error: function () {
                $('#errorMessages')
                    .append($('<li>')
                        .attr({ class: 'list-group-item list-group-item-danger' })
                        .text('Error Calling Web Service.  Please Try Again Later.'));
            }
        });
    } else {
        alert("Please provide a date in the format of yyyyMMdd");
        $('#dateForSightings').val('');
    }

})



//SuperPeopleButton
$('#superPeopleButton').on('click', function () {
    $('#errorMessages').empty();
    $('#homePage').hide();
    $('#superPowerPage').hide();
    $('#organizationsPage').hide();
    $('#locationsPage').hide();
    $('#sightingsPage').hide();
    $('#superPeoplePage').show();

    reloadSuperPeople();
    reloadSuperPeopleSuperPower();
    reloadSuperPeopleSightings();
    reloadSuperPeopleOrganizations();
});

function reloadSuperPeople() {
    $('#superPersonDropDown').empty();
    var superPersonList = $('#superPersonDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPeople',
        success: function (superPeopleArray) {
            $.each(superPeopleArray, function (index, superPerson) {
                var id = superPerson.superPersonID;
                var name = superPerson.superName;
                var description = superPerson.description;
                var type = superPerson.typeOfSuperPerson;

                var result = '<option id="locationID" value="' + id + '">' + name + '</option>'
                superPersonList.append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

$('#deleteSuperPersonButton').on('click', function () {
    var superPersonID = $('#superPersonDropDown option:selected').val();
    $.ajax({
        type: 'DELETE',
        url: 'http://localhost:8080/SuperSighting/SuperPerson/' + superPersonID,
        success: function () {
            $('#editSuperPersonName').val('');
            $('#editDescription').val('');
            $('#editTypeOfSuperPerson').val('');
            reloadSightings();
            reloadSuperPeopleSuperPower();
            reloadSuperPeopleSightings();
            reloadSuperPeopleOrganizations();
        }
    });
});

function reloadSuperPeopleSuperPower() {
    $('#superPersonSuperPowerUpdateDropDown').empty();
    $('#superPersonSuperPowerCreateDropDown').empty();
    var superPeopleSuperPowerUpdateList = $('#superPersonSuperPowerUpdateDropDown');
    var superPeopleSuperPowerCreateList = $('#superPersonSuperPowerCreateDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPowers',
        success: function (superPowerArray) {
            $.each(superPowerArray, function (index, superPower) {
                var id = superPower.superPowerID;
                var superPowerName = superPower.superPowerName;
                var result = '<option id="superPowerID" value="' + id + '">' + superPowerName + '</option>'
                superPeopleSuperPowerUpdateList.append(result);
                superPeopleSuperPowerCreateList.append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

function reloadSuperPeopleSightings() {
    $('#superPersonSightingsUpdateDropDown').empty();
    $('#superPersonSightingsCreateDropDown').empty();
    var superPersonSightingsUpdateList = $('#superPersonSightingsUpdateDropDown');
    var superPersonSightingsCreateList = $('#superPersonSightingsCreateDropDown');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Sightings',
        success: function (sightingArray) {
            $.each(sightingArray, function (index, sighting) {
                var id = sighting.sightingID;
                var sightingDateTime = sighting.sightingDateTime;
                var sightingLocation = sighting.location;
                var result = '<option id="locationID" value="' + id + '">' + sightingDateTime + ' : ' + sightingLocation.locationName + '</option>'
                superPersonSightingsUpdateList.append(result);
                superPersonSightingsCreateList.append(result);

            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}

function reloadSuperPeopleOrganizations() {
    $('#superPersonOrganizationsUpdateDropDown').empty();
    $('#superPersonOrganizationsCreateDropDown').empty();
    var superPersonOrganizationsUpdateList = $('#superPersonOrganizationsUpdateDropDown');
    var superPersonOrganizationsCreateList = $('#superPersonOrganizationsCreateDropDown');
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/Organizations',
        success: function (organizationArray) {
            $.each(organizationArray, function (index, organization) {
                var id = organization.organizationID;
                var orgType = organization.typeOfOrganization;
                var orgName = organization.organizationName;
                var orgDescription = organization.organizationDescription;
                var orgAddress = organization.organizationAddress;
                var orgContact = organization.organizationContact;
                var result = '<option id="organizationID" value="' + id + '">' + orgName + '</option>'
                superPersonOrganizationsUpdateList.append(result);
                superPersonOrganizationsCreateList.append(result);
            });
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }

    });
}


$('#createSuperPersonButton').on('click', function () {

    var orgIds = $('#superPersonOrganizationsCreateDropDown').val();
    var sightIds = $('#superPersonSightingsCreateDropDown').val();
    var id = $('#superPersonSuperPowerCreateDropDown').val();
    $('#errorMessages').empty();
    if (orgIds.length > 0 && sightIds.length>0) {
        
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/SuperSighting/SuperPerson/' + sightIds.toString() + '/' + orgIds.toString() + "/" + id,
            data: JSON.stringify({
                superName: $('#addSuperPersonName').val(),
                description: $('#addDescription').val(),
                typeOfSuperPerson: $('#addTypeOfSuperPerson').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (superPerson) {
                $('#errorMessages').empty();
                alert("SuperPerson " + superPerson.superName + " created!");
                $('#addSuperPersonName').val(),
                    $('#addDescription').val(),
                    $('#addTypeOfSuperPerson').val()

                reloadSuperPeople();
                reloadSuperPeopleSuperPower();
                reloadSuperPeopleSightings();
                reloadSuperPeopleOrganizations();

            },
            error: function (errors) {
                $('#errorMessages').append(errors.responseJSON.message);
                $('#addSightingDateTime').val('');
                reloadSuperPeople();
                reloadSuperPeopleSuperPower();
                reloadSuperPeopleSightings();
                reloadSuperPeopleOrganizations();
            }

        });
    } else {
        alert("Please select Sighting(s) and/or Organization(s) for your SuperPerson");
    }

});


//SuperPerson
$('#superPersonDropDown').on('change', function () {
    $('#superPersonOrganizationsUpdateDropDown option').prop('selected', false);
    $('#superPersonSightingsUpdateDropDown option').prop('selected', false);
    $('#errorMessages').empty();
    var superPersonSelected = $('#superPersonDropDown option:selected').val();
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/SuperSighting/SuperPerson/' + superPersonSelected,
        success: function (superPerson) {
            var id = superPerson.superPersonID;
            var name = superPerson.superName;
            var description = superPerson.description;
            var type = superPerson.typeOfSuperPerson;
            var power = superPerson.superPower;
            var sightings = superPerson.sightings;
            var orgs = superPerson.organizations;

            $('#editSuperPersonName').val(name);
            $('#editDescription').val(description);
            $('#editTypeOfSuperPerson').val(type);

            $('#superPersonSuperPowerUpdateDropDown').val(power.superPowerID);
            $.each(sightings, function (index, sighting) {
                var id = sighting.sightingID;
                $('#superPersonSightingsUpdateDropDown option[value=' + id + ']').prop('selected', true);
            })
            $.each(orgs, function (index, org) {
                var id = org.organizationID;
                $('#superPersonOrganizationsUpdateDropDown option[value=' + id + ']').prop('selected', true);
            })
            updateSuperPerson(superPerson);
        },
        error: function () {
            $('#errorMessages')
                .append($('<li>')
                    .attr({ class: 'list-group-item list-group-item-danger' })
                    .text('Error Calling Web Service.  Please Try Again Later.'));
        }
    });

});

function updateSuperPerson(superPerson) {

    $('#errorMessages').empty();
    $('#editSuperPersonButton').off('click');

    $('#editSuperPersonButton').on('click', function () {

        var orgIds = $('#superPersonOrganizationsUpdateDropDown').val();
        var sightIds = $('#superPersonSightingsUpdateDropDown').val();
        var id = $('#superPersonSuperPowerUpdateDropDown').val();
        if (sightIds.length > 0 && orgIds.length > 0) {
            $.ajax({
                type: 'PUT',
                url: 'http://localhost:8080/SuperSighting/SuperPerson/' + superPerson.superPersonID + "/" + sightIds.toString() + '/' + orgIds.toString() + "/" + id,
                data: JSON.stringify({
                    superPersonID: superPerson.superPersonID,
                    superName: $('#editSuperPersonName').val(),
                    description: $('#editDescription').val(),
                    typeOfSuperPerson: $('#editTypeOfSuperPerson').val()
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'dataType': 'json',
                success: function (superPerson) {
                    $('#errorMessages').empty();
                    alert("SuperPerson " + superPerson.superName + " successfully Changed!");
                    $('#editSuperPersonName').val('');
                    $('#editDescription').val('');
                    $('#editTypeOfSuperPerson').val('');

                    reloadSuperPeople();
                    reloadSuperPeopleSuperPower();
                    reloadSuperPeopleSightings();
                    reloadSuperPeopleOrganizations();
                },
                error: function (errors) {
                    $('#errorMessages').empty();
                    $('#errorMessages').append(errors.responseJSON.message);
                    //  $('#errorMessages')
                    //     .append($('<li>')
                    //         .attr({ class: 'list-group-item list-group-item-danger' })
                    //       .text('Error Calling Web Service.  Please Try Again Later.'));
                }

            });
        } else {
            alert("Please select Sighting(s) and/or Organization(s) for your SuperPerson");
        }
    });
}
