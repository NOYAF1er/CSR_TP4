package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
import org.inria.restlet.mta.internals.Tweet;
import org.inria.restlet.mta.internals.User;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Resource exposing a user
 *
 * @author msimonin
 * @author ctedeschi
 *
 */
public class TweetsResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /** User handled by this resource. */
    private User user_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public TweetsResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }
    
    /**
     * Add a tweet of the user matching the id given in the URI
     * 
     * @return JSON representation of a user
     * @throws JSONException
     */
    @Post("json")
    public Representation addUserTweets(JsonRepresentation representation) throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        
        JSONObject object = representation.getJsonObject();
        String tweetContent = object.getString("tweetContent");
        
        user_ = backend_.getDatabase().getUser(userId);
        Tweet tweet = new Tweet(tweetContent);
        user_.addTweet(tweet);
        
        // generate result
        JSONObject resultObject = new JSONObject();
        resultObject.put("tweetContent", tweetContent);
        JsonRepresentation result = new JsonRepresentation(resultObject);
        result.setIndenting(true);
        return result;
   }
    
    /**
     * Returns the tweets of the user matching the id given in the URI
     * 
     * @return JSON representation of a user
     * @throws JSONException
     */
    @Get("json")
    public Representation getUserTweets() throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);
        
        JSONObject tweetsObject = new JSONObject();
        int cpt =  0;
        for(Tweet tweet: user_.getUserTweets()){
        	JSONObject tweetObject = new JSONObject();
        	tweetObject.put("content", tweet.getContent());
        	tweetObject.put("date", tweet.getDate());
        	tweetsObject.put("\""+cpt+"\"", tweetObject);
        }    

        JsonRepresentation result = new JsonRepresentation(tweetsObject);
        result.setIndenting(true);
        return result;
    }

}
