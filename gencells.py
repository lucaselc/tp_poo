import json
import random

with open('moon.json', 'rb') as f:
    dick = json.load(f)
    height = dick['height'] 
    width = dick['width']
    num = height * width
    dick['cells'] = [None]*num
    for y in range(height):
        for x in range(width):
            dick['cells'][x + y*width] = {
                'position': {
                    'x': x,
                    'y': y,
                },
                'concentrationH3': random.randint(5, 10)/10,
                'roughness': random.randint(0, 5)/10
            }

with open('moon.json', 'w') as f:
    json.dump(dick, f, indent=2)
