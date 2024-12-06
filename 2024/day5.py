from pathlib import Path
from collections import defaultdict, deque

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
47|53
97|13
97|61
97|47
75|29
61|13
75|53
29|13
97|29
53|29
61|53
97|53
61|29
47|13
75|47
97|75
47|61
75|61
47|29
75|13
53|13

75,47,61,53,29
97,61,53,29,13
75,29,13
75,97,47,61,53
61,13,29
97,13,75,29,47
""".strip()

rules, updates = input.split("\n\n")

R = defaultdict(set)
R2 = defaultdict(set)
for rule in rules.splitlines():
    l, r = rule.split("|")
    l, r = int(l), int(r)
    R[l].add(r)
    R2[r].add(l)

L = [[int(c) for c in line.split(",")] for line in updates.splitlines()]
p1 = 0
for line in L:
    seen = set()
    valid = True
    for page in line:
        n = R[page] if page in R else None
        if n and not n.isdisjoint(seen):
            valid = False

        seen.add(page)

    if valid:
        median = line[len(line) // 2]
        p1 += median
    else:
        # sort and calculate median
        to_sort = set(line)
     
print(p1)
