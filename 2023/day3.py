import math
import os
import re
from collections import defaultdict

current_file = os.path.basename(__file__)
current_path = os.path.relpath(__file__)
year = current_path.split("/")[0]
day, ext = os.path.splitext(current_file)

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..
""".strip()

total = 0
gears = defaultdict(list)
lines = input.splitlines()
rows = len(lines)
cols = len(lines[0])

def is_symbol(char: str):
    return not char.isalpha() and char != "." and not char.isdigit()

def in_bounds(row: int, col: int):
    return 0 <= row < rows and 0 <= col < cols

def symbol_neighbour(row: int, col: int):
    for y, x in ((row + dy, col + dx) for dy in (-1, 0, 1) for dx in (-1, 0, 1)):
        if (y, x) != (row, col) and in_bounds(y, x) and is_symbol(lines[y][x]):
            return (lines[y][x], (y, x))
    return None

for row, line in enumerate(lines):
    for match in re.finditer(r"\d+", line):
        for col in range(match.start(), match.end()):
            neighbour = symbol_neighbour(row, col)
            if neighbour:
                total += int(match.group())
                if neighbour[0] == "*":
                    gears[neighbour[1]].append(int(match.group()))
                break

print(total)
print(sum([math.prod(parts) for parts in gears.values() if len(parts) > 1]))
