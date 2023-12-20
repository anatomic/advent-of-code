import json
import re
import copy
from math import prod
from collections import deque
from dataclasses import dataclass
from pathlib import Path
from typing import Callable

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
px{a<2006:qkq,m>2090:A,rfg}
pv{a>1716:R,A}
lnx{m>1548:A,A}
rfg{s<537:gd,x>2440:R,A}
qs{s>3448:A,lnx}
qkq{x<1416:A,crn}
crn{x>2662:A,R}
in{s<1351:px,qqz}
qqz{s>2770:qs,m<1801:hdj,R}
gd{a>3333:R,R}
hdj{m>838:A,pv}

{x=787,m=2655,a=1222,s=2876}
{x=1679,m=44,a=2067,s=496}
{x=2036,m=264,a=79,s=2244}
{x=2461,m=1339,a=466,s=291}
{x=2127,m=1623,a=2188,s=1013}
""".strip()

[instructions, parts] = input.split("\n\n")

I = {}


def parse_instructions(instruction: str):
    # px{a<2006:qkq,m>2090:A,rfg}
    a = re.match(r"([a-z]+)\{(.+)\}", instruction)
    name = a.group(1)
    ops = a.group(2).split(",")
    default = ops[-1]
    ops = [val.split(":") for val in ops[:-1]]
    return (name, ops, default)

for i in instructions.splitlines():
    (name, ops, default) = parse_instructions(i)
    I[name] = (ops, default)

p1 = 0
for part in parts.splitlines():
    match = re.match(r"\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)\}", part)
    x = int(match.group(1))
    m = int(match.group(2))
    a = int(match.group(3))
    s = int(match.group(4))

    key = "in"
    i = 0
    ops = I[key][0]
    default = I[key][1]
    while True:
        ops = I[key][0]
        default = I[key][1]
        if i < len(ops):
            [op, target] = ops[i]
            if eval(op):
                key = target
                i = 0
            else:
                i += 1
        else:
            i = 0
            key = default

        if key == "R":
            break
        elif key == "A":
            p1 += x + m + a + s
            break
        else:
            ops = I[key][0]
            default = I[key][1]

seed = {'x': [1, 4001], 'm': [1, 4001], 'a': [1, 4001], 's': [1, 4001]}
checks = deque([("in", seed)])
p2 = 0
while checks:
    (key, part_range) = checks.popleft()

    if key == 'A':
        p2 += prod([p[1] - p[0] for p in part_range.values()])
        continue
    elif key == 'R':
        continue

    ops = I[key][0]
    default = I[key][1]

    for op in ops:
        [exp, target] = op
        d = int(re.search(r"(\d+)", exp).group(1))
        c = exp[0]
        v = part_range[c]
        to_check = copy.copy(part_range)
        if '<' in exp:
            to_check[c] = [v[0], d]
            part_range[c] = [d, v[1]]
        elif '>' in exp:
            to_check[c] = [d+1, v[1]]
            part_range[c] = [v[0], d+1]

        checks.append((target, to_check))

    checks.append((default, part_range))

print(p1)
print(p2)
