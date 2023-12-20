from pathlib import Path
from enum import Enum
from dataclasses import dataclass
from collections import defaultdict, deque
from math import lcm
import sys

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

class Pulse(Enum):
    LOW = 1
    HIGH = 2

    def flip(self):
        if self == Pulse.LOW:
            return Pulse.HIGH
        else:
            return Pulse.LOW


class ModuleType(Enum):
    BROADCASTER = 1
    CONJUNCTION = 2
    FLIP_FLOP = 3

# pulses can be low or high

I = defaultdict(dict) # inputs keyed by module name
O = defaultdict(list) # outputs keyed by module name
M = {}

for line in input.splitlines():
    a,b = line.split(' -> ')
    targets = [t for t in b.split(', ')]

    if a == "broadcaster":
        module_type = ModuleType.BROADCASTER
        name = "broadcaster"
    elif line.startswith('&'):
        module_type = ModuleType.CONJUNCTION
        name = a[1:]
    elif line.startswith('%'):
        module_type = ModuleType.FLIP_FLOP
        name = a[1:]

    O[name].extend(targets)
    M[name] = (module_type, Pulse.LOW)
    for t in targets:
        I[t][name] = Pulse.LOW

# send a low pulse to the broadcaster
low = 0
high = 0
count = 0
lows = {}
p2_checks = ['vr', 'nl', 'lr', 'gt']

while True:
    count += 1
    Q = deque()
    Q.append(("broadcaster", "button", Pulse.LOW))
    while Q:
        module, sender, pulse = Q.popleft()

        if module in p2_checks and pulse == Pulse.LOW:
            lows[module] = count
            p2_checks.remove(module)

            if len(lows) == 4:
                print('p2', lcm(*lows.values()))
                sys.exit(0)

        if pulse == Pulse.HIGH:
            high += 1
        else:
            low += 1

        if module not in M:
            # module is rx
            # brute force ain't going to work!
            # jq feeds into rx and needs all inputs to be HIGH
            # inputs are vr, nl, lr, gt,
            # lcm of all the values for them to get to LOW?
            continue

        module_type, value = M[module]
        p_ = pulse

        match module_type:
            case ModuleType.FLIP_FLOP:
                if pulse == Pulse.LOW:
                    p_ = value.flip()
                    M[module] = (module_type, p_)
                else:
                    continue
            case ModuleType.CONJUNCTION:
                I[module][sender] = pulse
                p_ = Pulse.LOW if all(i == Pulse.HIGH for i in I[module].values()) else Pulse.HIGH

        for t in O[module]:
            Q.append((t, module, p_))

    if count == 1000:
        print("p1", low * high)
