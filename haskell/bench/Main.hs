module Main (main) where

import Criterion.Main
import qualified FpGym.TwoSumBench as TwoSumBench

main :: IO ()
main = defaultMain TwoSumBench.benchmarks
