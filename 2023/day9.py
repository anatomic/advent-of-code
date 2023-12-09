from pathlib import Path
from functools import reduce

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45
""".strip()

lines = [[list(int(x) for x in line.split())] for line in input.splitlines()]
p1 = 0
p2 = 0
for line in lines:
    while any(x != 0 for x in line[-1]):
        line.append([(b-a) for a, b in zip(line[-1][:-1], line[-1][1:])])
    p1 += sum(x[-1] for x in line)
    p2 += reduce(lambda acc, val: val[0] - acc, line[::-1], 0)

print(p1, p2)
