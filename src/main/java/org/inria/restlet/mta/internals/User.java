package org.inria.restlet.mta.internals;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * User
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class User
{

    /** Internal id of the user.*/
    private int id_;

    /** Name of the user.*/
    private String name_;

    /** Age of the user.*/
    private int age_;

    /** Tweet of the user*/
    private Collection<Tweet> tweets_;

    public User(String name, int age)
    {
        name_ = name;
        age_ = age;
        tweets_ = new ArrayList<Tweet>();
    }

    public String getName()
    {
        return name_;
    }

    public void setName(String name)
    {
        name_ = name;
    }

    public int getAge()
    {
        return age_;
    }

    public void setAge(int age)
    {
        age_ = age;
    }

    public int getId()
    {
        return id_;
    }

    public void setId(int id)
    {
        id_ = id;
    }
    
    /**
     * Add new tweet to the tweets' collection of the user
     * @param newTweet Tweet to add
     */
    public void addTweet(Tweet newTweet){
    	tweets_.add(newTweet);
    }
    
    /**
     * 
     * @return The collection of tweets of the user
     */
    public Collection<Tweet> getUserTweets(){
    	return tweets_;
    }
    

}
