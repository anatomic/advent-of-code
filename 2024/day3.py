import re
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()
ins = re.compile(r"mul\((\d{1,3}),(\d{1,3})\)")


def parse_and_sum(text):
    return sum([int(l) * int(r) for l, r in ins.findall(text)])


valid = ""
v = True

for i in range(len(input)):
    if input[i:].startswith("do()"):
        v = True
    if input[i:].startswith("don't()"):
        v = False
    if v:
        valid += input[i]

print("part1", parse_and_sum(input))
print("part2", parse_and_sum(valid))
