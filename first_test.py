import requests
import json
import math
import pandas as pd


api_key = open('api_keys.txt').readline()

address = input("Vous voulez allez ou\n")
google_maps_api_url = 'https://maps.googleapis.com/maps/api/geocode/json?address={}&key={}'

google_api_request = requests.get(google_maps_api_url.format(address, api_key))
print(google_maps_api_url.format(address, api_key))
geocode = json.loads(google_api_request.content)
latitude = float(geocode['results'][0]['geometry']['location']['lat'])
longitude = float(geocode['results'][0]['geometry']['location']['lng'])


socrata_location_query_url = 'https://opendata.paris.fr/explore/dataset/stationnement-voie-publique-emplacements/api/?disjunctive.regpri&disjunctive.regpar&disjunctive.typsta&disjunctive.typ&disjunctive.arrond&dataChart=eyJxdWVyaWVzIjpbeyJjb25maWciOnsiZGF0YXNldCI6InN0YXRpb25uZW1lbnQtdm9pZS1wdWJsaXF1ZS1lbXBsYWNlbWVudHMiLCJvcHRpb25zIjp7ImRpc2p1bmN0aXZlLnJlZ3ByaSI6dHJ1ZSwiZGlzanVuY3RpdmUucmVncGFyIjp0cnVlLCJkaXNqdW5jdGl2ZS50eXBzdGEiOnRydWUsImRpc2p1bmN0aXZlLnR5cCI6dHJ1ZSwiZGlzanVuY3RpdmUuYXJyb25kIjp0cnVlfX0sImNoYXJ0cyI6W3sidHlwZSI6ImNvbHVtbiIsImZ1bmMiOiJBVkciLCJ5QXhpcyI6Im9iamVjdGlkIiwic2NpZW50aWZpY0Rpc3BsYXkiOnRydWUsImNvbG9yIjoiIzI2Mzg5MiJ9XSwieEF4aXMiOiJ6b25yZXMiLCJtYXhwb2ludHMiOjUwLCJzb3J0IjoiIn1dLCJ0aW1lc2NhbGUiOiIiLCJkaXNwbGF5TGVnZW5kIjp0cnVlfQ%3D%3D?$where=within_circle(location_2, {}, {}, 600)'
socrata_api_request = requests.get(socrata_location_query_url.format(latitude, longitude))
parking_violations = json.loads(socrata_api_request.content)


violation_latitudes = [float(entry['latitude']) for entry in parking_violations]
violation_longitudes = [float(entry['longitude']) for entry in parking_violations]


test_points = {key : [] for key in ('x', 'y', 'count', 'distance')}

radius = (10 ** -3)

for multiplier in range(1, 6):
    for angle in range(0, 64):
        test_x = longitude + ((multiplier * radius) * math.cos(angle * (math.pi / 32)))
        test_y = latitude + ((multiplier * radius) * math.sin(angle * (math.pi / 32)))
        count = 0
        for long, lat in zip(violation_longitudes, violation_latitudes):
            for distance in (.015 ,.014 ,.013, .012 ,.011 ,.01, .009, .008, .007, .006, .005):
                if abs(test_x - long) < distance and abs(test_y - lat) < distance:
                    count += 1
        test_points['x'].append(test_x)
        test_points['y'].append(test_y)
        test_points['count'].append(count)
        test_points['distance'].append(multiplier)

pd_points = pd.DataFrame(test_points)

pd_points['parking_matrix'] = (pd_points['count'] ** 2) * (pd_points['distance'])

print(pd_points.sort_values('count', ascending=True))

print(pd_points[pd_points['count'] < pd_points['count'].mean()])