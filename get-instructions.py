import os

import openai

from markdownify import markdownify as md
from playwright.sync_api import sync_playwright

session = os.environ["AOC_SESSION"]
openai.api_key = os.getenv("OPENAI_API_KEY")


def interpret_with_llm(content):
    """Uses an LLM to interpret the instructions."""
    prompt = f"""
You are an assistant helping to summarize and interpret programming challenges.
Here are the instructions for a puzzle in Markdown format:
{content}

1. Provide a concise summary of the puzzle's objective.
2. Extract key tasks or subtasks required to solve the puzzle.
3. Identify and format any example inputs and outputs clearly.
4. Suggest algorithms or techniques that could be used to solve the puzzle.

Return your response in Markdown format with the following sections:
- **Objective**
- **Key Tasks**
- **Examples**
- **Programming Guidance**
"""
    completion = openai.chat.completions.create(
        model="gpt-4o",
        messages=[
            {
                "role": "system",
                "content": "You are a helpful programming assistant who is an expert in python.",
            },
            {"role": "user", "content": prompt},
        ],
    )
    return completion.choices[0].message.content


def fetch_advent_markdown(day, year=2024):
    url = f"https://adventofcode.com/{year}/day/{day}"
    with sync_playwright() as p:
        browser = p.chromium.launch()
        page = browser.new_page()
        page.context.add_cookies(
            [
                {
                    "name": "session",
                    "value": session,
                    "domain": "adventofcode.com",
                    "path": "/",
                }
            ]
        )
        page.goto(url)
        # Wait for the instructions to load
        page.wait_for_selector("article")
        article_html = page.locator("article").inner_html()
        browser.close()
        return md(article_html)


def save_to_file(day, markdown_content, interpretation):
    """Saves the fetched and interpreted content to files."""
    # Save the full markdown content
    with open(f"day_{day}.md", "w") as file:
        file.write(markdown_content)

    # Save the interpreted summary
    with open(f"day_{day}_summary.md", "w") as file:
        file.write(interpretation)


day = 18
markdown = fetch_advent_markdown(day)
interpretation = interpret_with_llm(markdown)
save_to_file(day, markdown, interpretation)
# with open(f"day_{day}.md", "w") as file:
#     file.write(markdown)

print(f"Day {day} instructions saved as Markdown!")
