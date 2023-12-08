import math
import re
import sys
import itertools
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

lines = input.splitlines()

choices = {}
for line in lines[2:]:
    key, raw_ = line.split(' = ')
    left, right = re.match(r"\((.*), (.*)\)", raw_).groups()
    choices[key] = {"L": left, "R": right}

p1 = 0
pos = 'AAA'
for command in itertools.cycle(lines[0]):
    pos = choices[pos][command]
    p1+=1
    if pos == 'ZZZ':
        print(p1)
        break

p2 = 0
positions = [x for x in choices.keys() if x.endswith('A')]
results = []
for command in itertools.cycle(lines[0]):
    p2+=1
    positions = [choices[p][command] for p in positions]
    results.extend([p2 for p in positions if p.endswith('Z')])

    if len(results) == len(positions):
        print(math.lcm(*results))
        break
