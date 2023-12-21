from pathlib import Path
from collections import deque, defaultdict

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
...........
.....###.#.
.###.##..#.
..#.#...#..
....#.#....
.##..S####.
.##..#...#.
.......##..
.##.#.####.
.##..##.##.
...........
""".strip()

G = [[c for c in row] for row in input.splitlines()]
R = len(G)
C = len(G[0])
S = [(x, y) for y, row in enumerate(G) for x, item in enumerate(row) if item == 'S'][0]

def neighbours(p: tuple):
    x, y = p
    return [(x + 1, y), (x - 1, y), (x, y + 1), (x, y - 1)]

# part 1
Q = [S]
for i in range(64):
    q = set()
    for p in Q:
        for n in neighbours(p):
            x, y = n
            if x < 0 or x >= C or y < 0 or y >= R:
                continue
            if G[y][x] != '.' and G[y][x] != 'S':
                continue

            q.add((x,y))
    Q = list(q)
print(len(Q))
