{-# LANGUAGE FunctionalDependencies #-}

module FpGym.Common.Solution
  ( Solution(..)
  , SolutionError(..)
  ) where

-- | Common interface for all algorithmic solutions
class Solution input output | input -> output where
  solve :: input -> output

-- | Common error types for solutions
data SolutionError
  = NoPossibleSolution
  | DivisionByZero
  | InvalidInput String
  deriving (Show, Eq)
