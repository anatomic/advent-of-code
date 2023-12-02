import os
from collections import defaultdict
from functools import reduce

current_file = os.path.basename(__file__)
day, ext = os.path.splitext(current_file)
f = open(f"./input/{day}.txt", "r")
input = f.read()

test = """Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"""

def parse_game(game: str):
    return int(game.split(" ")[1])

def parse_draws(draws: str):
    rounds = []
    rounds_ = draws.split("; ")
    for round_ in rounds_:
        rounds.append(
            {
                colour: int(count)
                for draw in round_.split(", ")
                for [count, colour] in [draw.split(" ")]
            }
        )
    return rounds

def is_valid(draws, red=0, green=0, blue=0):
    for draw in draws:
        for colour, count in draw.items():
            match colour:
                case "red" if count > red:
                    return False
                case "green" if count > green:
                    return False
                case "blue" if count > blue:
                    return False
    return True


def power(draws):
    result = defaultdict(int)
    for draw in draws:
        for colour, count in draw.items():
            result[colour] = max(count, result[colour])
    return reduce(lambda x, y: x * y, result.values(), 1)


sum = 0
sum_of_powers = 0
for line in input.splitlines():
    [game, draws] = line.split(": ")
    game = parse_game(game)
    draws = parse_draws(draws)
    sum_of_powers += power(draws)
    if is_valid(draws, 12, 13, 14):
        sum += game

print(sum)
print(sum_of_powers)
