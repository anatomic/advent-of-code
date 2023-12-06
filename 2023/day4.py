from collections import defaultdict
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
""".strip()

p1 = 0
p2 = defaultdict(int)
for i, line in enumerate(input.splitlines()):
    p2[i] += 1
    card, numbers = line.split(": ")
    a, b = numbers.split(" | ")
    winners = len(set(a.split()) & set(b.split()))
    if winners > 0:
        p1 += 2 ** (winners - 1)
    for j in range(i + 1, i + 1 + winners):
        p2[j] += p2[i]

print("p1", p1)
print("p2", sum(p2.values()))
