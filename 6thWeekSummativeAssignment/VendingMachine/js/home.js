$(document).ready(function () {
	loadVMItems();

	$('#add-dollar-button').click(function (){
		addMoney("1.00");
	});
	

	$('#add-quarter-button').click( function () {
		addMoney("0.25");
	});

	$('#add-dime-button').click( function () {
		addMoney("0.10");
	});

	$('#add-nickel-button').click( function () {
		addMoney("0.05");
	});

	$('#purchase-button').click( function(){
		var moneyDeposited = $('#deposit-money-form').val().replace('$', '');
		var itemNumber = $('#edit-item-form').val();
		purchaseItem(moneyDeposited, itemNumber);
	})

	$('#change-return-button').click( function() {
		loadVMItems();
		var quarter = parseFloat("0.25");
		var dime = parseFloat("0.10");
		var nickel = parseFloat("0.05");
		var penny = parseFloat("0.01");
		var totalChangeReturned = "";

		var moneyDeposited=$('#deposit-money-form').val().replace('$', "");
		var changeRemaining=parseFloat(moneyDeposited);
		var quartersToReturn = Math.floor(changeRemaining/quarter);
		changeRemaining = (changeRemaining % quarter).toFixed(2);

		var dimesToReturn = Math.floor(changeRemaining /dime);
		changeRemaining = (changeRemaining % dime).toFixed(2);

		var nickelsToReturn = Math.floor(changeRemaining / nickel);
		changeRemaining = (changeRemaining % nickel);

		var penniesToReturn = changeRemaining;

		if (quartersToReturn !=0) {
			totalChangeReturned += quartersToReturn.toString() + ' Quarter(s) \n ';
		}
		if (dimesToReturn !=0) {
			totalChangeReturned += dimesToReturn.toString() + ' Dime(s) \n ';
		}
		if (nickelsToReturn !=0) {
			totalChangeReturned += nickelsToReturn.toString() + ' Nickel(s) \n ';
		}
		if (penniesToReturn !=0) {
			totalChangeReturned += penniesToReturn.toString() + ' Pennies \n ';
		}

		if (quartersToReturn == 0 && dimesToReturn == 0 && nickelsToReturn == 0 && penniesToReturn == 0) {
			$('#edit-change-form').val('No Money Deposited');
		} else {
			$('#edit-change-form').val(totalChangeReturned);
		}

		$('#edit-item-form').val('');
		$('#edit-messages-form').val('');
		$('#deposit-money-form').val('0.00');

	})

});

function loadVMItems() {
 $('#VMItems').empty();
	var VMItems = $('#VMItems');

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/items',
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
					.attr({ class: 'list-group-item list-group-item-danger' })
					.text('Error Calling Web Service.  Please Try Again Later.'));
		}

	});
}

function displayIdMessage(id) {
	$('#edit-item-form').val(id);
}

function addMoney(money) {
	var runningTotal = parseFloat($('#deposit-money-form').val().replace('$', "")) + parseFloat(money);
	var total = '$' + runningTotal.toFixed(2).toString();
	$('#deposit-money-form').val(total);

}

function purchaseItem(moneyDeposited, itemNumber) {

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/money/' + moneyDeposited +'/item/' + itemNumber,
		success: function (change) {
			var quarters = change.quarters;
			var dimes = change.dimes;
			var nickels = change.nickels;
			var pennies = change.pennies;

			var changeTotal = "";
			if (quarters != 0) {
				changeTotal +=  quarters + ' Quarter(s) \n ';
			}
			if (dimes != 0) {
				changeTotal +=  dimes + ' Dime(s) \n ';
			}
			if (nickels != 0) {
				changeTotal += nickels + ' Nickel(s) \n ';
			}
			if (pennies != 0) {
				changeTotal += pennies + ' Pennies ';
			}
			if(quarters ==0 && dimes==0 && nickels ==0 && pennies==0) {
				$('#edit-change-form').val("No Change");
				$('#deposit-money-form').val('$0.00');
			} else {
				$('#edit-change-form').val(changeTotal);
				$('#deposit-money-form').val('$0.00');
			}
			$('#edit-messages-form').val('Thank You!!!');
			


		},
		error: function (error) {
			$('#edit-messages-form').val(error.responseJSON.message);
		}

	})

}
