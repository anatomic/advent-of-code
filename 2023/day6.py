import os
import math
from pathlib import Path
from functools import reduce

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
Time:      7  15   30
Distance:  9  40  200
""".strip()

lines = input.splitlines()
times = [int(x) for x in lines[0].split(":")[1].strip().split()]
distances = [int(x) for x in lines[1].split(":")[1].strip().split()]
pairs = list(zip(times, distances))

def calculate_winners(race: (int, int)):
    t, d = race
    return len([s * (t - s) for s in range(1, t + 1) if s * (t - s) > d])

p1 = list(map(calculate_winners, pairs))
print(math.prod(p1))

time = int(lines[0].split(":")[1].replace(" ", ""))
distance = int(lines[1].split(":")[1].replace(" ", ""))
print(calculate_winners((time, distance)))
