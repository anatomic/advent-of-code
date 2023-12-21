from pathlib import Path
from copy import copy

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#....
""".strip()

G = [[c for c in line] for line in input.splitlines()]

def rot(G):
    NG = [['?' for _ in row] for row in G]
    for y, row in enumerate(G):
        for x, item in enumerate(row):
            NG[x][len(G) - 1 - y] = G[y][x]

    return NG


def roll(G):
    C = {}
    for y, row in enumerate(G):
        for x, item in enumerate(row):
            if x not in C:
                C[x] = y

            m = C[x]
            if item == '.':
                C[x] = min(y, m)
            elif item == '#':
                C[x] = y + 1
            elif item == 'O':
                if y > m:
                    G[m][x] = 'O'
                    G[y][x] = '.'
                    C[x] = m + 1
                else:
                    C[x] = y + 1
    return G

def total(G):
    return sum((len(G) - y for y, row in enumerate(G) for x, item in enumerate(row) if item == 'O'))

def serialize(G):
    return "".join((c for line in G for c in line))

P = {}
cache = []
for t in range(10**9):
    for r in range(4):
        G = roll(G)
        if t == 0 and r == 0:
            print("p1", total(G))
        G = rot(G)
    g_ = serialize(G)
    if g_ in P:
        start = cache.index(g_)
        r = (10**9 - start) % (t - start) + start
        print("p2", P[cache[r - 1]])
        break

    P[g_] = total(G)
    cache.append(g_)
