package tweet_handling

/**
 * Created by holydrinker on 11/05/2015.
 */
object Runner {

  def main(args: Array[String]) {
    import bag_of_words.BagOfWords
    import java.util.Scanner

    //Get input
    val scanner = new Scanner(System.in)
    print("Insert hashtag: ")
    val hashtag = scanner.nextLine()
    scanner.close()

    //Get a superarray with all the words
    val arrayOfWords= TweetHandler.getHashtagTweets(hashtag)

    //Create a bag of words
    val bagOfWords = new BagOfWords

    //Update bag of words
    arrayOfWords.foreach(bagOfWords.addWord(_))

    //Print my bag of words
    bagOfWords.printTop(10)
  }
}
