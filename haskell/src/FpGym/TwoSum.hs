module FpGym.TwoSum
  ( TwoSumInput(..)
  , TwoSumResult
  , twoSum
  , twoSumFunctional
  , twoSumImperative
  ) where

import FpGym.Common.Solution (SolutionError(..))
import qualified Data.Map.Strict as Map
import Data.Vector (Vector)
import qualified Data.Vector as V

-- | Input for the Two Sum problem
data TwoSumInput = TwoSumInput
  { nums :: Vector Int
  , target :: Int
  } deriving (Show, Eq)

-- | Result type for Two Sum - Either error or indices
type TwoSumResult = Either SolutionError (Int, Int)

-- | Main entry point for Two Sum problem
twoSum :: Vector Int -> Int -> TwoSumResult
twoSum nums target = twoSumFunctional (TwoSumInput nums target)

-- | Functional implementation using fold and Map
twoSumFunctional :: TwoSumInput -> TwoSumResult
twoSumFunctional (TwoSumInput nums target) = go 0 Map.empty
  where
    go :: Int -> Map.Map Int Int -> TwoSumResult
    go idx seen
      | idx >= V.length nums = Left NoPossibleSolution
      | otherwise =
          let currentNum = nums V.! idx
              complement = target - currentNum
          in case Map.lookup complement seen of
               Just foundIdx -> Right (foundIdx, idx)
               Nothing -> go (idx + 1) (Map.insert currentNum idx seen)

-- | Alternative imperative-style implementation for comparison
twoSumImperative :: TwoSumInput -> TwoSumResult
twoSumImperative = twoSumFunctional   
