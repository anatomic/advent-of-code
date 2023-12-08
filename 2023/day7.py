import math
import os
from collections import defaultdict
from enum import Enum
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()

test = """
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483
""".strip()

# Hand              Number of different cards
# Five of a kind    1 (5)
# Four of a kind    2 (4 + 1)
# Full house        2 (3 + 2)
# Three of a kind   3 (3 + 1 + 1)
# Two pair          3 (2 + 2 + 1)
# One pair          4 (2 + 1 + 1 + 1)
# High card         5 (1 + 1 + 1 + 1 + 1)

# parse the cards, sort them, determine winings for each hand and return the sum
hands = [(line.split()[0], int(line.split()[1])) for line in input.splitlines()]

def update_hand(hand: str, part2: bool = False):
    hand = hand.replace("T", chr(ord("9") + 1))
    hand = hand.replace("J", chr(ord("1") - 1) if part2 else chr(ord("9") + 2))
    hand = hand.replace("Q", chr(ord("9") + 3))
    hand = hand.replace("K", chr(ord("9") + 4))
    return hand.replace("A", chr(ord("9") + 5))

def score(hand: str, part2: bool = False):
    cards = defaultdict(int)
    jokers = 0
    for card in hand:
        if part2 and card == "J":
            jokers += 1
        else:
            cards[card] += 1

    cards = sorted(cards.values())

    if part2:
        if len(cards) == 0:
            cards = [5]
        else:
            cards = cards[:-1] + [cards[-1] + jokers]

    hand = update_hand(hand, part2)
    match cards:
        case [5]:
            return (7, hand)
        case [1, 4]:
            return (6, hand)
        case [2, 3]:
            return (5, hand)
        case [1, 1, 3]:
            return (4, hand)
        case [1, 2, 2]:
            return (3, hand)
        case [1, 1, 1, 2]:
            return (2, hand)
        case [1, 1, 1, 1, 1]:
            return (1, hand)

for part2 in [False, True]:
    ranked = sorted(hands, key=lambda h: score(h[0], part2))
    print(sum([(i + 1) * h[1] for i, h in enumerate(ranked)]))
