Here is the single markdown file containing the generated `README.md` based on your project's structure and your request.

***

# FP-Gym: A Scala 3 Functional Programming Playground

[![CI](https://github.com/h0ffmann/fp-gym/actions/workflows/test.yml/badge.svg)](https://github.com/h0ffmann/fp-gym/actions/workflows/test.yml)
[![Scala Version](https://img.shields.io/badge/scala-3.7.1-blue.svg)](https://www.scala-lang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Welcome to FP-Gym! This repository is a personal collection of solutions to common programming problems and data structure challenges, implemented with a functional-first approach using **Scala 3**. The goal is to practice and demonstrate core FP concepts like immutability, recursion, higher-order functions, and type safety.

## ✨ Key Features

*   **Modern Scala 3:** Leverages new features like extension methods, enums, and simplified syntax.
*   **Functional First:** Solutions prioritize immutability, pure functions, and recursion (especially tail recursion) over imperative loops and state mutation.
*   **Custom Data Structures:** Includes a from-scratch implementation of a functional `RList` (recursive list) to demonstrate core principles.
*   **Comprehensive Testing:** All solutions are verified with a robust test suite using **MUnit**.
*   **CI/CD Pipeline:** Automated testing and formatting checks are run on every push and pull request using **GitHub Actions**.
*   **Developer-Friendly:** Includes a `justfile` for easy access to common commands like `test`, `fmt`, and `run`.

## 📚 Solved Problems

This table provides an overview of the challenges solved in this repository, along with a brief description and direct links to the implementation and test files.

| Problem | Description | Implementation | Tests |
| :--- | :--- | :--- | :--- |
| **Character Frequency** | Count the occurrences of each character in a string. | [Solution](src/main/scala/CharFrequencySolution.scala) | [Suite](src/test/scala/CharFrequencySuite.scala) |
| **Anagram Checker** | Check if two strings are anagrams of each other. | [Solution](src/main/scala/AnagramCheckerSolution.scala) | [Suite](src/test/scala/CharFrequencySuite.scala#L46) |
| **List Flattener** | Flatten a list of lists into a single list. | [Solution](src/main/scala/CustomFlattenerSolution.scala) | [Suite](src/test/scala/CustomFlattenerSuite.scala) |
| **List Deduplication** | Remove duplicate elements from a list while preserving order. | [Solution](src/main/scala/DuplicateRemovalSolution.scala) | [Suite](src/test/scala/DuplicateRemovalSuite.scala) |
| **Factorial** | Calculate the factorial of a number using tail recursion and `BigInt`. | [Solution](src/main/scala/FactorialSolution.scala) | [Suite](src/test/scala/FactorialSuite.scala) |
| **Fibonacci** | Generate the Nth Fibonacci number using tail recursion. | [Solution](src/main/scala/FibonacciSolution.scala) | _N/A_ |
| **Two Sum** | Find two numbers in an array that sum up to a specific target. | [FP](src/main/scala/TwoSumSolution.scala) / [Mutable](src/main/scala/TwoSumMutSolution.scala) | [Suite](src/test/scala/TwoSumSuite.scala) |
| **Quick Sort** | Sort an array of integers using the QuickSort algorithm. | [Solution](src/main/scala/QuickSortSolution.scala) | [Suite](src/test/scala/SortingSuite.scala) |
| **Safe Division** | Perform division that safely handles division-by-zero using `Either`. | [Solution](src/main/scala/SafeDivisionSolution.scala) | _N/A_ |
| **Config Parser** | Safely parse a `Map` into a `Config` case class using `Option` and `Either`. | [Solution](src/main/scala/CfgParserSolution.scala) | _N/A_ |
| **Async Operations** | Chain and combine `Future`s, including parallel execution and error recovery. | [Chain](src/main/scala/AsyncCombineSolution.scala) / [Parallel](src/main/scala/ParAsyncCombineSolution.scala) / [Recover](src/main/scala/ParAsyncCombineRecoverSolution.scala) | _N/A_ |

## 🔗 Custom Data Structures

A core part of this project is the implementation of fundamental data structures from scratch.

| Data Structure | Description | Implementation |
| :--- | :--- | :--- |
| **Recursive List (`RList`)** | A classic functional, singly-linked list (like `::` and `Nil`). | [Code](src/main/scala/lists/ListSolution.scala) |
| **Higher-Order Functions** | `map`, `flatMap`, and `filter` implemented for `RList` via extension methods. | [Code](src/main/scala/lists/HOF.scala) |
| **Run-Length Encoding** | An algorithm to compress consecutive duplicate elements in an `RList`. | [Code](src/main/scala/lists/RunLenghtEncoding.scala) |

## 🚀 Getting Started

### Prerequisites

*   JDK 17 or later
*   `sbt` (Simple Build Tool)
*   (Optional) `just` for using the command aliases in `justfile`.

### Setup

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/your-username/your-repo.git
    cd fp-gym
    ```

2.  **Compile the project:**
    This will download all necessary dependencies.
    ```sh
    sbt compile
    ```

### Common Commands

The `justfile` provides convenient shortcuts for common tasks.

*   **Run all tests:**
    ```sh
    just test
    ```

*   **Check code formatting:**
    ```sh
    just fmt-check
    ```

*   **Apply code formatting:**
    ```sh
    just fmt
    ```

*   **Run the full CI pipeline locally:**
    ```sh
    just ci
    ```

*   **See all available commands:**
    ```sh
    just --list
    ```

## ⚙️ CI/CD Pipeline

The project is equipped with a CI pipeline defined in [`.github/workflows/test.yml`](.github/workflows/test.yml). It triggers on every `push` and `pull_request` to the `main` branch and performs the following steps:
1.  Sets up JDK 17 and `sbt`.
2.  Caches dependencies for faster subsequent runs.
3.  Compiles the main and test source code.
4.  Verifies code formatting with `sbt scalafmtCheckAll`.
5.  Runs the entire MUnit test suite with `sbt test`.
6.  Uploads test results as build artifacts.

## License

This project is licensed under the MIT License.