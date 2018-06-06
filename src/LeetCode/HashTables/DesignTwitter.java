package LeetCode.HashTables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DesignTwitter {
    class Twitter {
        class Tweet{
            int userId;
            int tweetId;

            public Tweet(int userId, int tweetId) {
                this.userId = userId;
                this.tweetId = tweetId;
            }
        }
        private ArrayList<Tweet> timeline;
        private HashMap<Integer, HashSet<Integer>> following;

        /** Initialize your data structure here. */
        public Twitter() {
            timeline = new ArrayList<>();
            following = new HashMap<>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            timeline.add(new Tweet(userId, tweetId));
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            if(timeline.size()==0) return new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            int counter = 0;
            int i  = timeline.size();
            while(counter<10&&i>0){
                Tweet temp = timeline.get(--i);
                if(temp.userId==userId||(following.containsKey(userId)&&following.get(userId).contains(temp.userId))){
                    result.add(temp.tweetId);
                    ++counter;
                }
            }
            return result;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(!following.containsKey(followerId)) following.put(followerId, new HashSet<>());
            following.get(followerId).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(!following.containsKey(followerId)) return;
            if(following.get(followerId).contains(followeeId))  following.get(followerId).remove(followeeId);
        }
    }
}
