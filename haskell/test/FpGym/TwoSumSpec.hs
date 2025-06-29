module FpGym.TwoSumSpec (spec) where

import Test.Hspec
import FpGym.TwoSum
import FpGym.Common.Solution
import qualified Data.Vector as V

spec :: Spec
spec = describe "TwoSum" $ do
  
  describe "twoSum" $ do
    it "finds correct indices for basic case" $ do
      let nums = V.fromList [2, 7, 11, 15]
          target = 9
      twoSum nums target `shouldBe` Right (0, 1)
    
    it "finds correct indices when solution is at the end" $ do
      let nums = V.fromList [3, 2, 4]
          target = 6
      twoSum nums target `shouldBe` Right (1, 2)
    
    it "handles same number used twice" $ do
      let nums = V.fromList [3, 3]
          target = 6
      twoSum nums target `shouldBe` Right (0, 1)
    
    it "returns NoPossibleSolution when no pair exists" $ do
      let nums = V.fromList [1, 2, 3, 4, 5]
          target = 100
      twoSum nums target `shouldBe` Left NoPossibleSolution
    
    it "handles empty array" $ do
      let nums = V.empty
          target = 5
      twoSum nums target `shouldBe` Left NoPossibleSolution
    
    it "handles single element array" $ do
      let nums = V.fromList [5]
          target = 5
      twoSum nums target `shouldBe` Left NoPossibleSolution
    
    it "handles negative numbers" $ do
      let nums = V.fromList [-1, -2, -3, -4, -5]
          target = -8
      twoSum nums target `shouldBe` Right (2, 4)
    
    it "handles zero target" $ do
      let nums = V.fromList [-1, 0, 1, 2]
          target = 0
      twoSum nums target `shouldBe` Right (0, 2)
