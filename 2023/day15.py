from pathlib import Path
from collections import defaultdict
from functools import reduce, cache

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

vals = input.split(",")
# vals = """
# rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
# """.strip().split(",")

@cache
def hash(x):
    return reduce(lambda hash, val: (((hash + ord(val)) * 17) % 256), x, 0)

boxes = defaultdict(dict)
for val in vals:
    if "-" in val:
        id = val[:-1]
        if id in boxes[hash(id)]:
            del boxes[hash(id)][id]
    else:
        id = val[:-2]
        boxes[hash(id)][id] = int(val[-1])

p1 = sum([hash(val) for val in vals])
p2 = sum([(id + 1) * pos * lens for id, box in boxes.items() for pos, lens in enumerate(box.values(), 1)])

print(p1)
print(p2)
