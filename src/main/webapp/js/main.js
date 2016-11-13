var tempRestaurantList = [];

$.getJSON('restaurants', function(jsonResaurantList) {
    var table = $('<table>').appendTo($('#restaurantList'));
    $.each(jsonResaurantList, function(i, restaurant) {
    	tempRestaurantList.push(restaurant);
        var row = $('<tr>').appendTo(table);
        $('<td>').text(restaurant).appendTo(row);
    });
    console.log(tempRestaurantList);
});

var button = document.getElementById('getChoice');
button.addEventListener('tap', function(e) {
	rand = tempRestaurantList[Math.floor(Math.random() * tempRestaurantList.length)];
	$('#yourchoice').text(rand);
});