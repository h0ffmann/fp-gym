import Timer.timeIt

object SolutionComparer {
  def compareAlgorithms[A, B](
      input: A,
      alg1: A => B,
      alg2: A => B,
      name1: String = "Algorithm 1",
      name2: String = "Algorithm 2"
  ): Unit = {

    println(
      s"Comparing algorithms with input size: ${input.toString.take(50)}..."
    )

    val (result1, time1) = timeIt(alg1(input))
    val (result2, time2) = timeIt(alg2(input))

    println(f"$name1: ${time1}%d ms")
    println(f"$name2: ${time2}%d ms")

    if (time1 < time2) {
      println(f"$name1 is ${time2.toDouble / time1}%.2fx faster")
    } else {
      println(f"$name2 is ${time1.toDouble / time2}%.2fx faster")
    }

    // Verify results are the same
    if (result1 == result2) {
      println("✓ Results match")
    } else {
      println("✗ Results differ!")
      println(s"$name1 result: $result1")
      println(s"$name2 result: $result2")
    }
    println("-" * 50)
  }
}
