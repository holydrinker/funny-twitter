package twitter

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
    return removeStopwords(removeSymbols(removeLinks(goodSplit(tmp))))
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
  private def removeSymbols(arrayOfWords: Array[String]): Array[String] = {
    return (arrayOfWords filter (!_.contains('@'))) filter (!_.contains('#'))
  }

  /*
   * Remove italian stopwords
   */
  private def removeStopwords(arrayOfWords: Array[String]): Array[String] = {
    import scala.io.Source
    val stopwords  = Source.fromFile("src/main/scala//files/stopwords_italian.txt").getLines.toArray

    //Is my "isStopword" method efficient? mmm...
    def isStopword(word: String, source: Array[String]): Boolean = return source.filter(_.equalsIgnoreCase(word)).length > 0
    return arrayOfWords filter (!isStopword(_, stopwords))
  }

  def main(args: Array[String]) {
    val s = "ciao a tutti, luridi.vermi!!!"
    tokenizer(s) foreach println // ok
  }

}

