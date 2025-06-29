module FpGym.TwoSumProperties (spec) where

import Test.Hspec
import Test.QuickCheck
import FpGym.TwoSum
import FpGym.Common.Solution
import qualified Data.Vector as V

spec :: Spec
spec = describe "TwoSum Properties" $ do
  
  describe "Property: Indices are valid when solution exists" $ do
    it "returns valid indices" $ property $ \nums target ->
      let result = twoSum (V.fromList nums) target
      in case result of
           Right (i, j) -> 
             let len = length nums
             in i >= 0 && j >= 0 && i < len && j < len && i /= j
           Left _ -> True  -- Error cases are fine for this property
