module FpGym.TwoSumBench (benchmarks) where

import Criterion
import FpGym.TwoSum
import qualified Data.Vector as V

benchmarks :: [Benchmark]
benchmarks = 
  [ bgroup "TwoSum"
    [ bench "small array (100 elements)" $ 
        nf (\(nums, target) -> twoSumFunctional (TwoSumInput nums target)) 
           (smallNums, smallTarget)
    , bench "medium array (1000 elements)" $ 
        nf (\(nums, target) -> twoSumFunctional (TwoSumInput nums target)) 
           (mediumNums, mediumTarget)
    ]
  ]
  where
    smallNums = V.fromList [1..100]
    smallTarget = 199  -- 99 + 100
    
    mediumNums = V.fromList [1..1000]
    mediumTarget = 1999  -- 999 + 1000
