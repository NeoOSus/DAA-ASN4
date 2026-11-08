# DAA Assignment 4

A small Java project for edit distance (recursive, table and two-row DP) and string search (Naive and KMP).

## Build and tests

Requires JDK 17+ and Maven. Run `mvn test` from this directory. The JUnit 5 tests cover reference examples, empty strings, edge cases and 100 seeded random short string pairs/texts.

## Run experiment

Run `mvn -q -DskipTests package` and then `java -cp target/classes assignment.Experiment`. This overwrites `results/results.csv`. The generator uses fixed seed 424242; each recorded time is the median of three runs. The plots in `results/plots/` were generated from this CSV using Python and Matplotlib.

## Layout

- `src/main/java/assignment`: algorithms and experiment
- `src/test/java/assignment`: JUnit 5 tests
- `results/results.csv`: measurements
- `results/plots`: PNG plots
- `REPORT.md`: design card, results and conclusions

## AI use acknowledgement

AI assistance was used to help draft and review the code, experiment design and report. I reviewed the output and take responsibility for the submitted work.
