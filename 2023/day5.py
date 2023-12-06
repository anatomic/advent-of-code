import sys
from pathlib import Path
from functools import cache

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4
""".strip()

M = input.split("\n\n")[1:]
S = [int(x) for x in input.splitlines()[0].split(": ")[1].split()]
S2 = []
t = 0
for i, s in enumerate(S):
    if i % 2 == 0:
        start = S[i]
        count = S[i + 1]
        S2.append((start, start + count + 1))

class Mappings:
    def __init__(self, lines: str):
        parts = lines.split("\n")
        self.m = []
        for p in parts[1:]:
            dest, src, count = map(int, p.split())
            self.m.append((src, dest, count))

    def get(self, value: int):
        for src, dest, count in self.m:
            if src <= value <= src + count:
                return value + (dest - src)
        return value

    def apply_range(self, src_range: list((int, int))) -> list((int, int)):
        output = []
        for src, dest, count in self.m:
            # There are three combinations, start, intersect, end
            # If a range is altered by a mapping, the intersect won't be altered again
            e = src + count
            t = []
            while src_range:
                (s1, e1) = src_range.pop()
                diff = dest - src
                if s1 < min(src, e1):
                    t.append((s1, min(src, e1)))
                if e < max(s1, e):
                    t.append((max(e, s1),  e1))
                if max(src, s1) < min(e, e1):
                    output.append((max(s1, src) + diff, min(e1, e) + diff))
            src_range = t
        return output + src_range


G = list(map(Mappings, M))

p1 = sys.maxsize
for seed in S:
    s_ = seed
    for m in G:
        s_ = m.get(s_)
    p1 = min(p1, s_)

p2 = []
for r in S2:
    r_ = [r]
    for m in G:
        r_ = m.apply_range(r_)
    p2.append(min(r_)[0])

print(p1)
print(min(p2))
