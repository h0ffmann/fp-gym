# Justfile for fp-gym Scala project
# Usage: just <command>

# Default recipe - show available commands
default:
    @just --list

# ============================================
# CORE DEVELOPMENT COMMANDS
# ============================================

# Compile the main source code
compile:
    sbt compile

# Compile both main and test sources
compile-all:
    sbt "compile; Test/compile"

# Run all tests
test:
    sbt test

# Run a specific test suite
test-suite name:
    sbt "testOnly {{name}}"

# Run tests with detailed output
test-verbose:
    sbt "testOnly * -- --reporter munit.DefaultReporter"

# Clean build artifacts
clean:
    sbt clean

# Full clean and compile
rebuild: clean compile-all

# ============================================
# CODE FORMATTING
# ============================================

# Check code formatting (without modifying files)
fmt-check:
    sbt scalafmtCheckAll

# Format all source code
fmt:
    sbt scalafmtAll

# Format only main source code
fmt-main:
    sbt scalafmtSbt scalafmt

# Format only test code  
fmt-test:
    sbt "Test/scalafmt"

# ============================================
# DEVELOPMENT WORKFLOW
# ============================================

# Quick development check - compile + test + format check
check: compile-all test fmt-check
    @echo "✅ All checks passed!"

# Fix formatting and run tests
fix: fmt test
    @echo "✅ Code formatted and tests passed!"

# Continuous testing (watch mode)
watch:
    sbt "~test"

# Continuous compilation (watch mode)
watch-compile:
    sbt "~compile"

# ============================================
# SPECIFIC TEST SUITES
# ============================================

# Run Character Frequency tests
test-char:
    sbt "testOnly CharFrequencySuite"

# Run Two Sum tests
test-twosum:
    sbt "testOnly TwoSumSuite"

# Run RLE tests
test-rle:
    sbt "testOnly RLESuite"

# Run Factorial tests  
test-factorial:
    sbt "testOnly FactorialSuite"

# Run Custom Flattener tests
test-flatten:
    sbt "testOnly CustomFlattenerSuite"

# Run Duplicate Removal tests
test-dedup:
    sbt "testOnly DuplicateRemovalSuite"

# Run Sorting tests
test-sort:
    sbt "testOnly SortingSuite"

# ============================================
# PROJECT MANAGEMENT
# ============================================

# Show project dependencies
deps:
    sbt dependencyTree

# Update dependencies
update:
    sbt update

# Show project info
info:
    sbt about

# Start SBT shell
shell:
    sbt

# Create JAR package
package:
    sbt package

# ============================================
# CI/CD SIMULATION
# ============================================

# Run the same checks as CI pipeline
ci: clean compile-all fmt-check test
    @echo "🚀 CI simulation completed successfully!"

# Pre-commit checks
pre-commit: fmt check
    @echo "✅ Ready to commit!"

# Pre-push checks (more thorough)
pre-push: clean ci
    @echo "🚀 Ready to push!"

# ============================================
# PERFORMANCE & BENCHMARKING
# ============================================

# Run main class for performance testing
perf:
    sbt "runMain Main"

# Run with JVM optimizations
perf-optimized:
    sbt "set ThisBuild / javaOptions += \"-server\"" "runMain Main"

# ============================================
# DEBUGGING & ANALYSIS
# ============================================

# Compile with verbose output
compile-verbose:
    sbt "set ThisBuild / scalacOptions += \"-verbose\"" compile

# Show compilation warnings
warnings:
    sbt "compile; Test/compile"

# Run tests with coverage (if scoverage plugin added)
coverage:
    sbt "coverage; test; coverageReport"

# ============================================
# PROJECT SETUP
# ============================================

# Setup project for first time development
setup:
    @echo "🔧 Setting up fp-gym project..."
    sbt update
    sbt compile
    sbt Test/compile
    @echo "✅ Project setup complete!"

# Verify project structure
verify:
    @echo "📁 Checking project structure..."
    @test -f build.sbt || (echo "❌ build.sbt missing" && exit 1)
    @test -f .scalafmt.conf || (echo "❌ .scalafmt.conf missing" && exit 1)
    @test -d src/main/scala || (echo "❌ src/main/scala missing" && exit 1)
    @test -d src/test/scala || (echo "❌ src/test/scala missing" && exit 1)
    @echo "✅ Project structure looks good!"

# ============================================
# GIT WORKFLOW HELPERS
# ============================================

# Run checks and commit (use with: just commit "your message")
commit message: pre-commit
    git add .
    git commit -m "{{message}}"

# Run full checks and push
push: pre-push
    git push

# ============================================
# UTILITY COMMANDS
# ============================================

# Show Scala and SBT versions
versions:
    @echo "Scala version:"
    @sbt "show scalaVersion"
    @echo "SBT version:"
    @sbt sbtVersion

# Show current test count
test-count:
    @find src/test/scala -name "*.scala" -exec grep -l "test(" {} \; | wc -l | xargs echo "Test files:"
    @find src/test/scala -name "*.scala" -exec grep -o "test(" {} \; | wc -l | xargs echo "Total tests:"

# Open project in VS Code
code:
    code .

# Generate project documentation
docs:
    sbt doc

# ============================================
# ADVANCED WORKFLOWS
# ============================================

# Development loop: watch tests and auto-format
dev:
    @echo "🔄 Starting development loop (Ctrl+C to stop)..."
    sbt "~; scalafmt; test"

# Release preparation
release: clean compile-all fmt test package
    @echo "📦 Release package ready!"

# Quick smoke test
smoke:
    sbt "testOnly *Suite -- --include-categories smoke"

# ============================================
# HELP & DOCUMENTATION
# ============================================

# Show detailed help for all commands
help:
    @echo "fp-gym Development Commands"
    @echo "=========================="
    @echo ""
    @echo "🔧 Core Development:"
    @echo "  compile      - Compile main source"  
    @echo "  test         - Run all tests"
    @echo "  check        - Full validation (compile + test + fmt)"
    @echo "  clean        - Clean build artifacts"
    @echo ""
    @echo "✨ Code Quality:"
    @echo "  fmt          - Format all code"
    @echo "  fmt-check    - Check formatting"
    @echo "  fix          - Format + test"
    @echo ""
    @echo "🧪 Testing:"
    @echo "  test-char    - Run CharFrequencySuite"
    @echo "  test-rle     - Run RLESuite"
    @echo "  test-verbose - Detailed test output"
    @echo ""
    @echo "🚀 Workflows:"
    @echo "  ci           - Run CI pipeline locally"
    @echo "  pre-commit   - Pre-commit checks"
    @echo "  dev          - Development watch mode"
    @echo ""
    @echo "📁 Project:"
    @echo "  setup        - First-time setup"
    @echo "  verify       - Check project structure"
    @echo "  versions     - Show versions"