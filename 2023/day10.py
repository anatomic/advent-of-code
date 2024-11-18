from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
G = f.read().splitlines()
cols = len(G)
rows = len(G[0])

start = [(x, y) for y, row in enumerate(G) for x, c in enumerate(row) if c == 'S'][0]
loop = False

# visited = []
# while not loop:
#     visited.append(start)
#     start = [(start[0]+dx, start[1]+dy) for dx, dy in [(-1, 0), (1, 0), (0, -1), (0, 1)] if G[start[1]+dy][start[0]+dx] != '#'][0]
#     if start in visited:
#         loop = True

# Find 'S' in the grid and then work around there to find the length of the loop
# | is a vertical pipe connecting north and south. (0, +/-1)
# - is a horizontal pipe connecting east and west. (+/-1, 0)
# L is a 90-degree bend connecting north and east. (+1, +1)
# J is a 90-degree bend connecting north and west. (-1, +1)
# 7 is a 90-degree bend connecting south and west. (-1, -1)
# F is a 90-degree bend connecting south and east. (+1, -1)
# . is ground; there is no pipe in this tile.
# S is the starting position of the animal; there is a pipe on this tile, but your sketch doesn't show what shape the pipe has.

# discard . and each LJ7F tile should have exactly two neighours which are pipes

# *---A---*
# *--D@B--*
# *---C---*

# if @ == | then A can be either | or 7 or F, C can be | or J or F, and D & B cannot be reached
# if @ == - then D can be either - or J or 7, B can be either - or L or F, and A & C cannot be reached

def check(pos: (int, int)):
    x, y = pos
    val = G[y][x]
    A = G[y-1][x] if y - 1 >= 0 else None
    B = G[y][x+1] if x + 1 < rows else None
    C = G[y+1][x] if y + 1 < cols else None
    D = G[y][x-1] if x - 1 >= 0 else None


    return [x for x in [A, B, C, D] if x is not None]

print(check(start))
