from copy import copy
from heapq import heappop, heappush, heappushpop
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read().splitlines()

# expand the grid then do pathfinding for each pair of galaxies

test = """
...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....
""".strip()

# input = test.splitlines()

galaxies = [
    (x, y) for y, line in enumerate(input) for x, c in enumerate(line) if c == "#"
]
cols = set(range(len(input[0]))) - set([x[0] for x in galaxies])
rows = set(range(len(input))) - set(x[1] for x in galaxies)

def update(galaxy, rows, cols, factor=2):
    return (
        galaxy[0] + len([i for i in cols if galaxy[0] > i]) * (factor - 1),
        galaxy[1] + len([i for i in rows if galaxy[1] > i]) * (factor - 1),
    )

galaxies = [update(galaxy, rows, cols, 1000000) for galaxy in galaxies]
dists = [
    abs(b[1] - a[1]) + abs(b[0] - a[0])
    for i, a in enumerate(galaxies)
    for b in galaxies[i:]
]

print(sum(dists))
