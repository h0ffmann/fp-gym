# FP-Gym Haskell Solutions

This directory contains Haskell implementations of the algorithmic challenges from the fp-gym project.

## Quick Start

```bash
# Build the project
stack build

# Run tests
stack test

# Run the demo
stack exec fp-gym-haskell-exe

# Start REPL
stack ghci
```

## Project Structure

- `src/FpGym/` - Source code
- `test/FpGym/` - Tests  
- `bench/FpGym/` - Benchmarks
- `app/` - Executable

## Solutions

### Two Sum

Find two numbers in an array that sum to a target value.

- **Time Complexity:** O(n)
- **Space Complexity:** O(n)
- **Implementation:** Uses HashMap for O(1) lookups
