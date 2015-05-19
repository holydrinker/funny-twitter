package bag_of_words

/**
 * Created by holydrinker on 17/05/2015.
 */
object BagOfWord_Tester {

  def main(args: Array[String]) {
    val bow = new BagOfWords
    bow.addWord("a")
    bow.addWord("a")
    bow.addWord("b")
    //OK!

    bow.bag foreach println   //Make bow iterable

  }

}
