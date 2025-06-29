# FP-Gym: A Multi-Language Functional Programming Playground

[![Scala CI](https://github.com/h0ffmann/fp-gym/actions/workflows/scala-ci.yml/badge.svg)](https://github.com/h0ffmann/fp-gym/actions/workflows/scala-ci.yml)
[![Haskell CI](https://github.com/h0ffmann/fp-gym/actions/workflows/haskell-ci.yml/badge.svg)](https://github.com/h0ffmann/fp-gym/actions/workflows/haskell-ci.yml)
[![Python CI](https://github.com/h0ffmann/fp-gym/actions/workflows/python-ci.yml/badge.svg)](https://github.com/h0ffmann/fp-gym/actions/workflows/python-ci.yml)
[![Scala Version](https://img.shields.io/badge/scala-3.7.1-blue.svg)](https://www.scala-lang.org/)
[![GHC Version](https://img.shields.io/badge/ghc-9.8.6-purple.svg)](https://www.haskell.org/ghc/)
[![Python Version](https://img.shields.io/badge/python-3.13-green.svg)](https://www.python.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Welcome to FP-Gym! This repository is a personal collection of solutions to common programming problems and data structure challenges, implemented with a functional-first approach using **Scala 3**, **Haskell**, and **Python**. The goal is to practice and demonstrate core FP concepts like immutability, recursion, higher-order functions, and type safety across different paradigms.

## ✨ Key Features

*   **Multi-Language Approach:** Solutions implemented in Scala 3, Haskell, and Python to compare functional programming approaches
*   **Modern Scala 3:** Leverages new features like extension methods, enums, and simplified syntax
*   **Haskell Purity:** Demonstrates pure functional programming with strong type safety
*   **Python FP:** Shows functional programming concepts using generators, Maybe monads, and immutable patterns
*   **Functional First:** Solutions prioritize immutability, pure functions, and recursion over imperative approaches
*   **Custom Data Structures:** Includes from-scratch implementations of functional data structures
*   **Comprehensive Testing:** All solutions verified with robust test suites (MUnit for Scala, HSpec/QuickCheck for Haskell, pytest/Hypothesis for Python)
*   **Property-Based Testing:** Extensive use of property-based testing to ensure correctness
*   **CI/CD Pipeline:** Automated testing and formatting checks for all languages
*   **Developer-Friendly:** Includes justfiles for easy access to common commands

## 📚 Solved Problems

This table provides an overview of the challenges solved in this repository across different languages.

| Problem | Description | Languages | Key Concepts |
| :--- | :--- | :--- | :--- |
| **Character Frequency** | Count occurrences of each character in a string | Scala | `groupMapReduce`, functional transformations |
| **Anagram Checker** | Check if two strings are anagrams | Scala | Character frequency analysis, case handling |
| **List Flattener** | Flatten nested lists into a single list | Scala | `foldLeft`, list concatenation |
| **List Deduplication** | Remove duplicates while preserving order | Scala | Tail recursion, mutable vs immutable approaches |
| **Factorial** | Calculate factorial using tail recursion | Scala | Tail recursion, `BigInt` for large numbers |
| **Fibonacci** | Generate Nth Fibonacci number | Scala | Tail recursion optimization |
| **Two Sum** | Find two numbers that sum to target | Scala, Haskell, Python | Hash maps, generators, Maybe/Either monads |
| **Quick Sort** | Sort array using QuickSort algorithm | Scala | Recursive partitioning, functional approach |
| **Safe Division** | Division with error handling | Scala | `Either` monad, error handling |
| **Config Parser** | Parse configuration safely | Scala | `Option` and `Either` chaining, for-comprehensions |
| **Async Operations** | Chain and combine Futures | Scala | `Future` composition, parallel execution, error recovery |
| **Run-Length Encoding** | Compress consecutive duplicates | Scala | Pattern matching, tail recursion |

## 🏗️ Project Structure

```
fp-gym/
├── src/main/scala/           # Scala implementations
├── src/test/scala/           # Scala tests (MUnit)
├── haskell/                  # Haskell implementations
│   ├── src/FpGym/           # Haskell source code
│   ├── test/FpGym/          # Haskell tests (HSpec + QuickCheck)
│   └── bench/FpGym/         # Haskell benchmarks
├── python/                   # Python implementations
│   ├── src/fp_gym/          # Python source code
│   └── tests/               # Python tests (pytest + Hypothesis)
└── .github/workflows/        # CI/CD pipelines
```

## 🔗 Custom Data Structures

A core part of this project is implementing fundamental data structures from scratch.

| Data Structure | Language | Description |
| :--- | :--- | :--- |
| **Recursive List (`RList`)** | Scala | Functional singly-linked list with custom operations |
| **Higher-Order Functions** | Scala | `map`, `flatMap`, `filter` via extension methods |
| **Vector-Based Solutions** | Haskell | Efficient array operations with Vector |
| **Generator-Based Structures** | Python | Memory-efficient lazy evaluation with generators |

## 🚀 Getting Started

### Prerequisites

*   **Scala:** JDK 17+, sbt
*   **Haskell:** GHC 9.8+, Stack
*   **Python:** Python 3.13+, uv (or pip)
*   (Optional) `just` for command aliases

### Quick Start

Choose your language of interest:

#### Scala
```sh
# Compile and test
sbt compile
sbt test

# Or use justfile
just test
just fmt
```

#### Haskell
```sh
cd haskell

# Setup and test
stack setup
stack test

# Or use justfile
just test
just docs
```

#### Python
```sh
cd python

# Setup and test
uv sync --dev
uv run pytest

# Or use justfile
just test
just test-property
```

## ⚙️ CI/CD Pipeline

Each language has its own CI pipeline that runs on every push and pull request:

*   **Scala CI:** Compilation, testing, formatting checks with sbt
*   **Haskell CI:** Stack build, HSpec tests, Haddock documentation
*   **Python CI:** Type checking, pytest, property-based testing with Hypothesis, code formatting with ruff

All pipelines include:
- Dependency caching for faster builds
- Test result artifacts
- Code quality checks
- Cross-platform compatibility testing

## 🧪 Testing Philosophy

This project emphasizes thorough testing with multiple approaches:

*   **Unit Tests:** Traditional example-based testing
*   **Property-Based Testing:** Hypothesis (Python) and QuickCheck (Haskell) to discover edge cases
*   **Performance Testing:** Benchmarks comparing different algorithmic approaches
*   **Integration Testing:** End-to-end testing of complete solutions

## 📊 Language Comparisons

The multi-language approach allows for interesting comparisons:

*   **Type Safety:** Haskell's compile-time guarantees vs Scala's balance vs Python's runtime checking
*   **Performance:** Compiled (Scala/Haskell) vs interpreted (Python) trade-offs
*   **Expressiveness:** Pattern matching, monadic composition, and functional idioms across languages
*   **Ecosystem:** Different approaches to dependency management and tooling

## License

This project is licensed under the MIT License.