module Main (main) where

import FpGym.TwoSum
import qualified Data.Vector as V

main :: IO ()
main = do
  putStrLn "🧮 FP-Gym Haskell Solutions"
  putStrLn "========================="
  
  -- Test Two Sum
  let testNums = V.fromList [2, 7, 11, 15]
      testTarget = 9
  
  putStrLn $ "Testing Two Sum with " ++ show (V.toList testNums) ++ " and target " ++ show testTarget
  
  case twoSum testNums testTarget of
    Right (i, j) -> putStrLn $ "✅ Found solution: indices " ++ show i ++ " and " ++ show j
    Left err -> putStrLn $ "❌ No solution: " ++ show err
  
  -- Performance test
  putStrLn "\n🏃 Performance Test"
  putStrLn "==================="
  
  let largeNums = V.fromList [1..1000]
      largeTarget = 1999  -- sum of last two elements
  
  case twoSum largeNums largeTarget of
    Right (i, j) -> putStrLn $ "✅ Large test passed: (" ++ show i ++ ", " ++ show j ++ ")"
    Left err -> putStrLn $ "❌ Large test failed: " ++ show err
