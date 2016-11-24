package org.inria.restlet.mta.resources;

import org.inria.restlet.mta.backend.Backend;
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
public class UserResource extends ServerResource
{

    /** Backend. */
    private Backend backend_;

    /** User handled by this resource. */
    private User user_;

    /**
     * Constructor.
     * Call for every single user request.
     */
    public UserResource()
    {
        backend_ = (Backend) getApplication().getContext().getAttributes()
                .get("backend");
    }

    /**
     * Returns the user matching the id given in the URI
     * 
     * @return JSON representation of a user
     * @throws JSONException
     */
    @Get("json")
    public Representation getUser() throws Exception
    {
        String userIdString = (String) getRequest().getAttributes().get("userId");
        int userId = Integer.valueOf(userIdString);
        user_ = backend_.getDatabase().getUser(userId);

        JSONObject userObject = new JSONObject();
        userObject.put("name", user_.getName());
        userObject.put("age", user_.getAge());
        userObject.put("id", user_.getId());
        userObject.put("tweet_url", getReference().toString() + "/tweets");

        JsonRepresentation result = new JsonRepresentation(userObject);
        result.setIndenting(true);
        return result;
    }
    
    @Delete("json")
    public Representation deleteUser() throws Exception
    {
    	String userIdString = (String) getRequest().getAttributes().get("userId");
    	int userId = Integer.valueOf(userIdString);
    	backend_.getDatabase().deleteUser(userId);
    	
    	JSONObject userObject = new JSONObject();
    	userObject.put("result", "Succesfull delete !");
    	
        JsonRepresentation result = new JsonRepresentation(userObject);
        result.setIndenting(true);
		return result;
    	
    }

}
