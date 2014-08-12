/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 28, 2014
 */

package com.andrewsears.example.shoppingcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract POJO for all persistent objects.  It will require an ID that is an integer.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 */
@MappedSuperclass
public abstract class AbstractPersistentObject implements Serializable {

	private static final long serialVersionUID = 2251040542259357850L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false)
	private Long id;
	
	public AbstractPersistentObject() {
		super();
	}
	
	/**
	 * Returns the ID.
	 * @return the ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/** Sets the ID.
	 * @param id The ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
