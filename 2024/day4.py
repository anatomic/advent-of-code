from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

G = [[c for c in line] for line in input.splitlines()]
X = len(G[0])
Y = len(G)

def count_xmas(x, y):
    count = 0
    for dx in range(-1, 2):
        for dy in range(-1, 2):
            if dx == 0 and dy == 0:
                continue
            w = ""
            for i in range(4):
                vx = x + dx * i
                vy = y + dy * i
                if vx < 0 or vx >= X or vy < 0 or vy >= Y:
                    continue
                w += G[vy][vx]
            
            if w == "XMAS":
                count += 1

    return count

def is_mas(x, y):
    if y - 1 < 0 or y + 1 >= Y:
        return False

    if x - 1 < 0 or x + 1 >= X:
        return False
    
    lr = G[y - 1][x - 1] + G[y][x] + G[y + 1][x+1]
    rl = G[y - 1][x + 1] + G[y][x] + G[y + 1][x-1]

    return (lr == "MAS" or lr == "SAM") and (rl == "MAS" or rl == "SAM")

p1, p2 = 0, 0
for y in range(Y):
    for x in range(X):
        if G[y][x] == "X":
            p1 += count_xmas(x, y)
        if G[y][x] == 'A':
            p2 += 1 if is_mas(x, y) else 0


print("part1", p1)
print("part2", p2)
