from pathlib import Path
import math
from collections import defaultdict

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

lines = input.splitlines()

p1 = 0
ids = []
for line in lines:
    row = range(0, 128)
    col = range(0, 8)
    for r in line[:7]:
        if r == 'F':
            row = row[0:math.floor(len(row)/2)]
        elif r == 'B':
            row = row[math.ceil(len(row)/2):]

    for c in line[7:]:
        if c == 'L':
            col = col[0:math.floor(len(col)/2)]
        elif c == 'R':
            col = col[math.ceil(len(col)/2):]

    p1 = max(p1, row.start * 8 + col.start)
    ids.append(row.start * 8 + col.start)

print(p1)

for i in range(min(ids), max(ids)+1):
    if not i in ids:
        print(i)
