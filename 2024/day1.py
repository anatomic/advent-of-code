from collections import Counter
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.readlines()

a = list()
b = list()

for line in input:
    [l, r] = line.split()
    l, r = int(l), int(r)
    a.append(l)
    b.append(r)

a.sort()
b.sort()
part1 = sum([abs(b - a) for a, b in zip(a, b)])

print(f"part1: {part1}")

c = Counter(b)
part2 = sum([a * c[a] for a in a])
print(f"part2: {part2}")
