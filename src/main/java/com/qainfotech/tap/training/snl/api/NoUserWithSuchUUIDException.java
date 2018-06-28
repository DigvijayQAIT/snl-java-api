package com.qainfotech.tap.training.snl.api;

import java.util.UUID;

/**
 *
 * @author Ramandeep
 */
public class NoUserWithSuchUUIDException extends Exception{
    
    public NoUserWithSuchUUIDException(String uuid){
        super("No Player with uuid '"+uuid+"' on board");
    }
    
    public NoUserWithSuchUUIDException(UUID uuid) {
    	throw new UnsupportedOperationException("Not Supported Yet.");
	}
}
