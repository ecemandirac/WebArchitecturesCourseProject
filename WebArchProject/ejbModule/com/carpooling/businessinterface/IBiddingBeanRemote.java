package com.carpooling.businessinterface;

import java.util.List;

import javax.ejb.Remote;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import com.carpooling.entities.Bids;

@Remote
public interface IBiddingBeanRemote {
	

	public List<Bids> getHighestBids(int freeSeats, int offerID);


	int bid(int offerID, int userID, int amount) throws NotSupportedException,
			SystemException, SecurityException, IllegalStateException,
			RollbackException, HeuristicMixedException,
			HeuristicRollbackException;


	int getInstantHighestBid(int offerID);
	

}
