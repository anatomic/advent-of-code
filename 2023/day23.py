from pathlib import Path
from heapq import heappop, heappush
from time import sleep
import sys

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()


test = """
#.#####################
#.......#########...###
#######.#########.#.###
###.....#.>.>.###.#.###
###v#####.#v#.###.#.###
###.>...#.#.#.....#...#
###v###.#.#.#########.#
###...#.#.#.......#...#
#####.#.#.#######.#.###
#.....#.#.#.......#...#
#.#####.#.#.#########v#
#.#...#...#...###...>.#
#.#.#v#######v###.###v#
#...#.>.#...>.>.#.###.#
#####v#.#.###v#.#.###.#
#.....#...#...#.#.#...#
#.#########.###.#.#.###
#...###...#...#...#.###
###.###.#.###v#####v###
#...#...#.#.>.>.#.>.###
#.###.###.#.###.#.#v###
#.....###...###...#...#
#####################.#
""".strip()

G = dict([((x, y), c) for y, row in enumerate(test.splitlines()) for x, c in enumerate(row)])
R = max([y for _, y in G.keys()])
C = max([x for x, _ in G.keys()])
end = [(x, y) for (x, y), c in G.items() if c == '.' and y == R][0]
print(end)

def pp(grid):
    for y in range(R + 1):
        r = ""
        for x in range(C + 1):
            c = grid[(x, y)]
            if c == '#':
                r += "## "
            elif c == '>' or c == '<' or c == 'v':
                r += " " + c + " "
            elif (x, y) in visited:
                r += str(abs(visited[(x, y)])).zfill(2) + " "
            else:
                r += " " + c + " "
        print(r)

checks = {
    '>': [(1, 0)],
    '<': [(-1, 0)],
    'v': [(0, 1)],
    '#': [],
    '.': [(0, 1), (0, -1), (1, 0), (-1, 0)],
}

checks = {
    '>': [(0, 1), (0, -1), (1, 0), (-1, 0)],
    '<': [(0, 1), (0, -1), (1, 0), (-1, 0)],
    'v': [(0, 1), (0, -1), (1, 0), (-1, 0)],
    '#': [],
    '.': [(0, 1), (0, -1), (1, 0), (-1, 0)],
}

# start and end are second spot on first row and penultimate spot on the last row
Q = [(0, (1, 0), None)]
nq = []
visited = dict()
while Q:
    steps, pos, src = heappop(Q)
    c = G[pos]

    if c == '#':
        continue

    if pos in visited and visited[pos] <= steps:
        continue

    for x_, y_ in checks[c]:
        next = (pos[0] + x_, pos[1] + y_)
        if next not in G or next == src:
            continue

        heappush(nq, (steps-1, next, pos))

    visited[pos] = steps
    if len(Q) == 0:
        Q = nq

print(abs(visited[end]))
