'''

Python version: 3.7
Development IDE: PyCharm version 2019.3.3
Author: Elián González Hernández
Email: elian880530@gmail.com

History:
We ran a survey in Mallorca amongst 1st Generation Pokemon fans (Pokémon Red, Green and Blue).

We want to answer the following question:
Where do people who preferred to start with Bulbasaur, Charmander or Squirtle live?

'''

import mysql.connector
import pandas as pd
import json
from geojson import Feature, FeatureCollection, Point

#1-Query these results from the database
#Configuration acces to the data-base test
cnx = mysql.connector.connect(user='candidate',database='test',password='Fbps9Y7MhKQa4XPxjYo8',host='35.187.55.190')

#2-Only keep the results with a score higher or equal to 0.5
#Create the data-frame
df = pd.read_sql("SELECT test.data.id as data_id,test.coordinates.id as coordinates_id,test.data.score as score,test.data.pokemon as pokemon,test.coordinates.latitude as latitude,test.coordinates.longitude as longitude FROM test.data_at_coordinate INNER JOIN test.data ON test.data.id = test.data_at_coordinate.id_data INNER JOIN test.coordinates ON test.coordinates.id = test.data_at_coordinate.id_coordinate WHERE test.data.score >= 0.5;", cnx)

#Close conection
cnx.close()

#Testing result in data-frame
df.head(50)

#3-Clean-Up the data:
#Remove white spaces:
df.columns = df.columns.str.replace(' ', '')
print(df)

#Convert to lower case
df['pokemon'] = df['pokemon'].str.lower()

#GroupBy column pokemon and counting the different types
gby_pokemon = df.groupby("pokemon")["coordinates_id"].count()

#Exploring the original data
gby_pokemon[5:18]
gby_pokemon[18:35]
gby_pokemon[155:185]
gby_pokemon[180:201]
print(gby_pokemon)

#Applying regular expressions to transform the data
df = pd.DataFrame(df.replace(regex={r'^(bulb).*': 'bulbasaur', r'^(charma|charam|charamn|charmn).*': 'charmander', r'^(charme|charml).*': 'charmeleon', r'^(squi).*': 'squirtle'}))

#Exploring the transformed data
gby_pokemon_clean = df.groupby("pokemon")["coordinates_id"].count()
gby_pokemon_clean[5:28]
gby_pokemon_clean[150:181]
print(gby_pokemon_clean)

#4-Only keep the points we are interested in (those about Bulbasaur, Charmander and Squirtle)
# select rows containing 'bulbasaur' or 'charmander' or 'squirtle'
df_clean = df.loc[(df['pokemon'] == 'bulbasaur') | (df['pokemon'] == 'charmander') | (df['pokemon'] == 'squirtle')]
print(df_clean)

#Function to create geojson
def df_to_geojson(df_clean, properties, lat='latitude', lon='longitude'):
    geojson = {'type':'FeatureCollection', 'features':[]}
    for _, row in df_clean.iterrows():
        feature = {'type':'Feature','properties':{},'geometry':{'type':'Point','coordinates':[]}}
        feature['geometry']['coordinates'] = [row[lon],row[lat]]
        for prop in properties:
            feature['properties'][prop] = row[prop]
        geojson['features'].append(feature)
    return geojson

cols = ['pokemon', 'score', 'data_id', 'coordinates_id']

#Whole geojson object
geojson = df_to_geojson(df_clean, cols)
print(geojson)

#Save the geojson file
with open('./tyrceo_result_elian.geojson', 'w', encoding='utf-8') as f:
    json.dump(geojson, f, ensure_ascii=False)

#Checking that the data according to the id are kept with the same values
df_clean[0:50]



