from pathlib import Path
from collections import defaultdict

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.

#...##..#
#....#..#
..##..###
#####.##.
#####.##.
..##..###
#....#..#
""".strip()

patterns = [pattern for pattern in test.split("\n\n")]
X = 0
Y = 0
for pattern in patterns:
    # 1. split into rows and columns
    rows = [row for row in pattern.splitlines()]

    cols = defaultdict(str)
    for i in range(len(rows[0])):
        for row in rows:
            cols[i] += row[i]


    print(rows)
    print(list(cols.values()))

    X = None
    Y = None
    for i, col in enumerate(cols[:-1], 1):
        if col == cols[i]:
            X = i

    for i, row in enumerate(rows[:-1], 1):
        if row == rows[i]:
            Y = i

    print(X, Y)
