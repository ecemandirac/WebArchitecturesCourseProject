package com.carpooling.business;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.carpooling.business.bidding.Bid;
import com.carpooling.businessinterface.IBiddingBeanRemote;
import com.carpooling.entities.Bids;

/**
 * Session Bean implementation class BiddingBean
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class BiddingBean implements IBiddingBeanRemote {

	HashMap bidList = new HashMap();
	int latestBid = 0;
	
	@PersistenceContext(unitName = "JPADB")
    private EntityManager entityManager;
	
	@Resource
	private UserTransaction userTransaction;
	
    /**
     * Default constructor. 
     */
    public BiddingBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int bid(int offerID, int userID, int amount) throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		String bidTableName = "BID_" + String.valueOf(offerID);
		
		//select the highest bid inserted of this offer
		String getLatestQuery = "SELECT AMOUNT FROM Bids WHERE OfferID="+ offerID + " ORDER BY AMOUNT DESC";
		
		userTransaction.begin();
		
		
		List<Integer> bids = (List<Integer>) entityManager.createNativeQuery(getLatestQuery).getResultList();
		userTransaction.commit();
		int highestBid;
		
		
		
		if(bids.size() > 0){
		highestBid = bids.get(0).intValue();
		System.out.println("highest bid: " + highestBid);
		}
		else highestBid = 0;
		
		//check if the amount bid by the user is higher than the latest bid. If so, insert
		if(amount > highestBid){
			String insertBid = "INSERT INTO Bids VALUES (" +
					"null, " +
					String.valueOf(offerID) + ", " +
					String.valueOf(userID) + ", " +
					String.valueOf(amount) + 
					")";
			
			System.out.println(insertBid);
			
			userTransaction.begin();
			entityManager.createNativeQuery(insertBid).executeUpdate();
			latestBid = amount;
			userTransaction.commit();
			
			//update latest bid in the bids list
			
			Bid bid = new Bid(offerID, userID, amount);
			
			if( ((Bid) bidList.get(offerID)) == null)
				bidList.put(new Integer(offerID), bid);
			
			else bidList.put(offerID, bid);
			
			return 1;
		}
		else{
			
			return 0;
		}
		
	}
	
	@Override
	public List<Bids> getHighestBids(int freeSeats, int offerID){
		
		String bidTableName = "BID_" + String.valueOf(offerID);
		Bid bid = new Bid(offerID);
		
		String getHighestQuery = "SELECT * FROM (SELECT * FROM Bids WHERE ID IN (SELECT MAX(ID) FROM Bids WHERE OfferID=" + offerID + " GROUP BY UserID)) as T ORDER BY T.Amount DESC";
		
		List<Bids> bids = entityManager.createNativeQuery(getHighestQuery, Bids.class).getResultList();
		
		/*for(Bids b : bids)
			bid.getBidder().put(b.getUserID(), b.getAmount());
		*/
		return bids;
	}
	
	@Override
	public int getInstantHighestBid(int offerID){
		
		String getLatestQuery = "SELECT AMOUNT FROM Bids WHERE OfferID="+ offerID + " ORDER BY AMOUNT DESC";
		List<Integer> bids = (List<Integer>) entityManager.createNativeQuery(getLatestQuery).getResultList();
		int highest = bids.get(0).intValue();

		System.out.println("highest bid : " + highest);
		return highest;
	}
	
	
    
    

}


