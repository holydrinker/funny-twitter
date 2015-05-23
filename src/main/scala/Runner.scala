/**
 * Created by holydrinker on 11/05/2015.
 */
object Runner {

  def main(args: Array[String]) {
    import java.util.Scanner
    import bagOfWords.BagOfWords
    import twitter.TweetHandler

    //Get input
    val scanner = new Scanner(System.in)
    print("Insert hashtag: ")
    val hashtag = scanner.nextLine()
    scanner.close()

    //Build a superarray with all the words
    val arrayOfWords= TweetHandler.getHashtagTweets(hashtag)

    //Instantiate a bag of words, populate it, and print the top n words
    val bow = new BagOfWords
    arrayOfWords.foreach(bow.addWord(_))
    bow.printTop(10)
  }
}
