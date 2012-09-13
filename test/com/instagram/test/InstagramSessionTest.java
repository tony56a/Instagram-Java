package com.instagram.test;

import static org.junit.Assert.*;

import java.util.List;

import com.instagram.InstagramSession;
import com.instagram.auth.AccessToken;
import com.instagram.exception.InstagramException;
import com.instagram.model.Comment;
import com.instagram.model.Relationship;

import org.json.JSONException;
import org.junit.Test;

public class InstagramSessionTest {

	private InstagramSession getNewSession() {
		return new InstagramSession(new AccessToken(Constants.ACCESS_TOKEN));
	}

	@Test
	public void testInstagramSession() {
		fail("Not yet implemented");
	}

	@Test
	public void testInstagramSessionAccessToken() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAccessToken() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAccessToken() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserById() throws InstagramException {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		getNewSession().getUserById(Constants.VALID_USER_ID);
	}

	@Test
	public void testGetRecentPublishedMedia() throws InstagramException {
		InstagramSession session = this.getNewSession();
		
		/*
		 * Test for an invalid page number it should throw an InstagramException
		 */
		try {
			session.getRecentPublishedMedia(Constants.VALID_USER_ID, 0);
			fail("No Exception thrown for out of bounds page");
		} catch (InstagramException e) {}
		
		/*
		 * Test that no Exception is thrown for a valid user id and page number
		 */
		session.getRecentPublishedMedia(Constants.VALID_USER_ID, 1);
	}

	@Test
	public void testGetFeed() throws InstagramException {
		InstagramSession session = this.getNewSession();
		
		/*
		 * Test for an invalid page number it should throw an InstagramException
		 */
		try {
			session.getFeed(0);
			fail("No Exception thrown for out of bounds page");
		} catch (InstagramException e) {}
		
		/*
		 * Test that no Exception is thrown for a valid page number
		 */
		session.getFeed(1);	
	}

	@Test
	public void testGetLikedMedia() throws InstagramException {
		InstagramSession session = this.getNewSession();
		
		/*
		 * Test for an invalid page number it should throw an InstagramException
		 */
		try {
			session.getLikedMedia(0);
			fail("No Exception thrown for out of bounds page");
		} catch (InstagramException e) {}
		
		/*
		 * Test that no Exception is thrown for a valid page number
		 */
		session.getLikedMedia(1);	
	}

	@Test
	public void testGetMedia() throws InstagramException {
		/*
		 * Test that no Exception is thrown for a valid media id
		 */
		getNewSession().getMedia(Constants.VALID_MEDIA_ID);
	}

	@Test
	public void testSearchMedia() throws InstagramException {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPopularMedia() throws InstagramException {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().getPopularMedia();	
	}

	@Test
	public void testSearchUsersByName() throws InstagramException {		
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().searchUsersByName("instagram");
	}

	@Test
	public void testGetFollows() throws InstagramException {
		InstagramSession session = this.getNewSession();
		
		/*
		 * Test for an invalid page number it should throw an InstagramException
		 */
		try {
			session.getFollows(Constants.VALID_USER_ID, 0);
			fail("No Exception thrown for out of bounds page");
		} catch (InstagramException e) {}
		
		/*
		 * Test that no Exception is thrown for a valid page number and user id
		 */
		session.getFollows(Constants.VALID_USER_ID, 1);
	}

	@Test
	public void testGetFollowers() throws InstagramException {
		InstagramSession session = this.getNewSession();
		
		/*
		 * Test for an invalid page number it should throw an InstagramException
		 */
		try {
			session.getFollowers(Constants.VALID_USER_ID, 0);
			fail("No Exception thrown for out of bounds page");
		} catch (InstagramException e) {}
		
		/*
		 * Test that no Exception is thrown for a valid page number and user id
		 */
		session.getFollowers(Constants.VALID_USER_ID, 1);
	}

	@Test
	public void testGetFollowRequests() throws InstagramException {
		/*
		 * Test that no Exception is thrown
		 */
		getNewSession().getFollowRequests();
	}

	@Test
	public void testGetRelationshipWith() throws InstagramException {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		getNewSession().getRelationshipWith(Constants.VALID_USER_ID);
	}

	@Test
	public void testModifyRelationship() throws InstagramException {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		InstagramSession session = getNewSession();
		Relationship.Action action = null;
		if(session.getRelationshipWith(Constants.VALID_USER_ID).getOutgoingStatus() == Relationship.OutgoingStatus.FOLLOWS)
			action = Relationship.Action.UNFOLLOW;
		else
			action = Relationship.Action.FOLLOW;
		assertTrue(session.modifyRelationship(Constants.VALID_USER_ID, action));
	}

	@Test
	public void testPostComment() throws InstagramException {
		/*
		 * Test that no Exception is thrown for a valid user id
		 */
		InstagramSession session = getNewSession();
		Comment comment = session.postComment(Constants.VALID_MEDIA_ID, "nice pic");
		session.removeComment(Constants.VALID_MEDIA_ID, comment.getId());
	}

	@Test
	public void testRemoveComment() throws InstagramException {
		InstagramSession session = getNewSession();
		Comment comment = session.postComment(Constants.VALID_MEDIA_ID, "nice pic");
		/*
		 * Test that no Exception is thrown for a valid comment id
		 */
		assertTrue(session.removeComment(Constants.VALID_MEDIA_ID, comment.getId()));
	}

	@Test
	public void testLikeMedia() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveMediaLike() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTag() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecentMediaForTag() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchTags() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLocation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecentMediaFromLocation() {
		fail("Not yet implemented");
	}


}
