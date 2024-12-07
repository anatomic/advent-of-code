from collections import deque
from pathlib import Path
from re import I

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

G = [[c for c in line] for line in input.splitlines()]
X = len(G[0])
Y = len(G)
print(X, Y)

guard = (0, 0)
visited = set()
direction = (0, -1)  # up
# turns go 90 deg to the right

for y, row in enumerate(input.splitlines()):
    for x, c in enumerate(row):
        if c == "^":
            guard = (x, y)
            visited.add(guard)
            break


def print_grid(grid):
    for row in grid:
        for column in row:
            print(column, end=" ")
        print()
    print()


def turn(direction):
    x, y = direction
    match (x, y):
        case (0, -1):
            return (1, 0)
        case (0, 1):
            return (-1, 0)
        case (1, 0):
            return (0, 1)
        case (-1, 0):
            return (0, -1)
    return direction  # shouldn't happen


while True:
    x1 = guard[0] + direction[0]
    y1 = guard[1] + direction[1]

    if x1 < 0 or x1 >= len(G[0]) or y1 < 0 or y1 >= len(G):
        patrol = False
        break

    if G[y1][x1] == "#":
        direction = turn(direction)
    else:
        guard = (x1, y1)
        visited.add((guard, direction))
        G[y1][x1] = "X"


print(len(visited))
