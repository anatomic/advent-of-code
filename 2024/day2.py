from itertools import pairwise
from pathlib import Path
from re import I
import sys
from typing import Tuple

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.readlines()

def is_safe(vals: list[Tuple[int, int]]) -> bool:
    ascending = all(x < y for x, y in vals)
    descending = all(x > y for x, y in vals)
    matching = sum(1 if abs(x - y) > 3 or abs(x-y) < 1 else 0 for x, y in vals) 
    return (ascending or descending) and matching < 1

def make_pairs(vals: list[int]) -> list[Tuple[int, int]]:
    return list(pairwise(vals))

part1, part2 = 0, 0
for line in input:
    vals = list(map(int, line.split()))
    if is_safe(make_pairs(vals)):
        part1 += 1
        part2 += 1
    else:
        for i in range(len(vals)):
            if is_safe(make_pairs(vals[:i] + vals[i+1:])):
                part2 += 1
                break

print(part1)
print(part2)
