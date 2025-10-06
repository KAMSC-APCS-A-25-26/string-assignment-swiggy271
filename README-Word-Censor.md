## WordCensor (Student Instructions)

### Goal
Write a program that reads a full sentence and censors these banned words by replacing them with exactly three asterisks:
- `dang`
- `nuts`
- `oops`
- `yikes`

Example:
```
Example Input:  oops that was nuts and dang yikes
Example Output: Censored: *** that was *** and *** ***
```

### Requirements
- Read a full line of input using `Scanner.nextLine()`.
- Replace every occurrence of the banned words with exactly `***`.
- Print the result prefixed with `Censored: `.
- Do not print the original banned word anywhere in the output.

### Hints
- Be careful to use exactly three asterisks for every replacement.
