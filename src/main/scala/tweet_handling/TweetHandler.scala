package tweet_handling

/**
 * Created by holydrinker on 11/05/2015.
 */

object TweetHandler {
  import twitter4j.TwitterFactory
  val twitter = new TwitterFactory().getInstance()

  /*
   * It allows to post a tweet on your timeline
   */
  def updateStatus() = {
    import java.util.Scanner

    print("Hi, what's up?: ")
    val statusText = new Scanner(System.in).nextLine()
    twitter.updateStatus(statusText)
  }

  /*
   * It allows to get a limited number of tweets from a specified account
   */
  def getSingleAccountTweet(account: String) = {
    import twitter4j.{ResponseList, Status}

    import collection.JavaConversions._     //without this import doesn't work. I didn't understand why, very well.

    val list:ResponseList[Status] = twitter.getUserTimeline(account)
    for (status:Status <- list) { println(s"${status.getUser().getName}: ${status.getText()}") }
  }

  /*
   * It allows to get a limited number of tweets from an account list
   */
  def getAccountListTweet(accountList: List[String]) = {
    for(account <- accountList){
      getSingleAccountTweet(account)
    }
  }

  /*
   * Print the last 100 tweets with the specified hashtag
   */
  def getHashtagTweets(hashtag: String): Array[String] = {
    import twitter4j.{Query, QueryResult}
    import collection.JavaConversions._

    //Create a query. Select language, number of tweets and exclude RT
    val query: Query = new Query("#" + hashtag + " lang:it -RT")
    query.setCount(100)

    //Execute the query and add all the tweets in a List
    val result: QueryResult = twitter.search(query)
    val allTweets = result.getTweets()
    val multiarray = for(tweet <- allTweets)yield Preprocessing.tokenizer(tweet.getText())

    //Ordering data structure descreasing


    //Return a superarray of strings
    return multiarray.toArray flatten
  }

}
