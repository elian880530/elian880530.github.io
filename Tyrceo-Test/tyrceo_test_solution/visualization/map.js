
mapboxgl.accessToken = 'pk.eyJ1Ijoiam1jYXJyYXNjb3NhIiwiYSI6ImNrZGlvcmIzMzA3MW0zMG50dG90NWJ0aTEifQ.ZoobdVV5OqJUUVH-k2ZHDg';

var map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11', // stylesheet location
    center: [2.9581,39.6374], // mallorca longitude:2.9581 , latitude:39.6374
    zoom: 9 // starting zoom
});

map.on('load', function() {

    // Add an image to use as a custom marker
    map.loadImage(
        'https://docs.mapbox.com/mapbox-gl-js/assets/custom_marker.png',
        function(error, image) {
            if (error) throw error;
            map.addImage('custom-marker', image);

            // Add a GeoJSON source with 2 points
            map.addSource('points', {
                'type': 'geojson',
                'data': 'tyrceo_result_elian.geojson'
            });

            map.addLayer({
                'id': 'points',
                'type': 'circle',
                'source': 'points',

                // 'source-layer': 'sf2010',
                'paint': {

                    // make circles larger as the user zooms from z12 to z22
                    'circle-radius': {
                        'base': 1.75,
                        'stops': [
                            [12, 2],
                            [22, 180]
                        ]
                    },

                    // color circles by points, using a match expression
                    'circle-color': [
                        'match',
                        ['get', 'pokemon'],
                        'bulbasaur',
                        '#fbb03b',
                        'charmander',
                        '#223b53',
                        'squirtle',
                        '#e55e5e',
                        /* other */
                        '#ccc'
                    ]
                }
            });

        }
    );
});

