#!/usr/bin/env python3
import argparse
import os
import subprocess
import sys

session = os.environ["AOC_SESSION"]

cli = argparse.ArgumentParser(description="Fetch the input for a specific day")
cli.add_argument("--year", type=int, default=2023, help="Year to fetch")
cli.add_argument("--day", type=int, default=1, help="Day to fetch")
cli.add_argument(
    "--init", help="Create the empty script for the day", action="store_true"
)

args = cli.parse_args()

useragent = "https://github.com/anatomic/advent-of-code by ian@ian-thomas.net"
cmd = f'curl https://adventofcode.com/{args.year}/day/{args.day}/input --cookie "session={session}" -A {useragent}'
output = subprocess.check_output(cmd, shell=True)
output = output.decode("utf-8")

os.makedirs(f"{args.year}/input", exist_ok=True)
open(f"{args.year}/input/day{args.day}.txt", "w").write(output.strip())

if args.init:
    open(f"{args.year}/day{args.day}.py", "w").write(
        """
from pathlib import Path

current_path = Path(__file__).resolve()
year = current_path.parts[-2]
day = current_path.stem

f = open(f"{year}/input/{day}.txt", "r")
input = f.read()
""".strip()
    )

print("\n".join(output.split("\n")[:10]), file=sys.stderr)
