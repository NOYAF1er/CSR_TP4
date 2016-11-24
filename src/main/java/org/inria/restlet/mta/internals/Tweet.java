package org.inria.restlet.mta.internals;

import java.util.Date;

public class Tweet {
	/** Content of the tweet */
	String content_;
	
	/** Date of the tweet */
	Date date_;
	
	public Tweet(String content){
		content_ = content;
		date_ = new Date(); //Take the current date
	}

	public String getContent() {
		return content_;
	}

	public Date getDate() {
		return date_;
	}
	
}
