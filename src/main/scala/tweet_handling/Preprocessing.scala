package tweet_handling

/**
 * Created by holydrinker on 15/05/2015.
 */
object Preprocessing {

  def tokenizer(text: String): Array[String] = {
    val marks = List(',', ':', '.', '?', '!', ';','-', '_', '(', ')', '"')
    var tmp = text toLowerCase

    for(i <- 0 to tmp.length-1){
      val c = tmp.charAt(i)
      for(j <- 0 to marks.length - 1){
        if(c == marks(j))
          tmp = tmp.replace(c, ' ')
      }
    }

    //Return an array of string with the words tokenized. First remove the links
    return removeStopwords(removeAtAndHash(removeLinks(goodSplit(tmp))))
  }

  /*
   * Split the statement in an array, removing blank spaces words
   */
  private def goodSplit(text: String): Array[String] = {
    text.split(" ") filter (_ != "")
  }

  /*
   * Remove links from the text
   */
  private def removeLinks(arrayOfWords: Array[String]): Array[String] = {
    return (arrayOfWords filter (!_.contains('/'))) filter (!_.contains("http"))
  }

  /*
   * Remove hashtags and cit
   */
  private def removeAtAndHash(arrayOfWords: Array[String]): Array[String] = {
    return (arrayOfWords filter (!_.contains('@'))) filter (!_.contains('#'))
  }

  /*
   * A crude method to remove the words that COULD BE stopwords.
   * I will remove all the words whoso length is less than 4
   */
  private def removeStopwords(arrayOfWords: Array[String]): Array[String] = {
    return arrayOfWords filter (_.length > 3)
  }

  def main(args: Array[String]) {
    val s = "ciao a tutti, luridi.vermi!!!"
    tokenizer(s) foreach println // ok
  }

}

