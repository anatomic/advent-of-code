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
p2 = 0
pos = 'AAA'
positions = [x for x in choices.keys() if x.endswith('A')]

for command in itertools.cycle(lines[0]):
    if pos == 'ZZZ':
        print(p1)
        break

    pos = choices[pos][command]
    p1+=1

for command in itertools.cycle(lines[0]):
    valid = True
    N = []
    for p in positions:
        if choices[p][command].endswith('Z') == False:
            valid = False
        N.append(choices[p][command])

    if valid:
        print("p2", p2)
        sys.exit()
    else:
        p2 += 1
        if p2 % 1000000 == 0:
            print(N)
        positions = N
