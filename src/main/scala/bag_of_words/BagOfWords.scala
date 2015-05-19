package bag_of_words

/**
 * Created by holydrinker on 17/05/2015.
 */
class BagOfWords {
  var bag = Map[String, Int]()

  /*
   * Add a word to bag. If the world is in yet, it increase the corresponding item
   */
  def addWord(word: String) = bag = bag updated (word, bag.getOrElse(word, 0) + 1 )

  /*
   * Print the bag in this format: "key:value"
   */
  def printBag() = for((k,v) <- bag) println(k + ":" + v)

  /*
   * Print the top n value in this format "key:value"
   */
  def printTop(n:Int) = {
    val list = bag.toList sortBy {_._2}
    val array = new Array[(String, Int)](n)
    for(i <- 0 to n-1){array(i) = list(list.size - i - 1)}
    for((k,v) <- array) println(k + ":" + v*100)
  }
}
