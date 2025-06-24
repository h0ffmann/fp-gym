//E4: Anagram checker

import common.Solution

case class AnagramCheckerInput(word1: String, word2: String)

object AnagramCheckerSolution extends Solution[AnagramCheckerInput, Boolean] {

  override def solve(input: AnagramCheckerInput): Boolean = {
    val charMap1 = CharFrequencySolution(input.word1.toLowerCase)
    val charMap2 = CharFrequencySolution(input.word2.toLowerCase)
    charMap1 == charMap2
  }

  def apply(word1: String, word2: String): Boolean = {
    solve(AnagramCheckerInput(word1, word2))
  }
}
