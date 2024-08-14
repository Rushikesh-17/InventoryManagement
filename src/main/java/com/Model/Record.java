package com.Model;


import java.time.Instant;

import java.util.Date;

import javax.persistence.*;

@Entity
@SequenceGenerator(name="RECORD_SEQ", sequenceName="record_sequence")
public class Record {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "RECORD_SEQ")
	private int recordId;
	
	private String user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Item item;
	
	private int usedQuantity;

	private String action;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = Date.from(Instant.now());
	
	

	public Record() {
		super();
	}

	public Record(int recordId, String user, Item item, int usedQuantity, String action) {
		super();
		this.recordId = recordId;
		this.user = user;
		this.item = item;
		this.usedQuantity = usedQuantity;
		this.action = action;
	}
	

	public Record(String user, Item item, int usedQuantity, String action) {
		super();
		this.user = user;
		this.item = item;
		this.usedQuantity = usedQuantity;
		this.action = action;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getUsedQuantity() {
		return usedQuantity;
	}

	public void setUsedQuantity(int usedQuantity) {
		this.usedQuantity = usedQuantity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Record [recordId=" + recordId + ", user=" + user + ", item=" + item + ", usedQuantity=" + usedQuantity
				+ ", action=" + action + ", date=" + date + "]";
	}
	
	
	

	
	

}
