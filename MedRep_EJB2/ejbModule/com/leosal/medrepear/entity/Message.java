package com.leosal.medrepear.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message extends com.leosal.util.jpa.Entity implements Serializable,
		Comparable<Message>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String attach;

	@Lob
	private String body;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mess_date")
	private Date messDate;

	private String theme;

	//bi-directional many-to-one association to MReceiver
	@OneToMany(mappedBy="message")
	private Set<MReceiver> MReceivers;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="mess_from")
	private Person sender;

	public Message() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAttach() {
		return this.attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getMessDate() {
		return this.messDate;
	}

	public void setMessDate(Date messDate) {
		this.messDate = messDate;
	}

	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Set<MReceiver> getMReceivers() {
		return this.MReceivers;
	}

	public void setMReceivers(Set<MReceiver> MReceivers) {
		this.MReceivers = MReceivers;
	}

	public MReceiver addMReceiver(MReceiver MReceiver) {
		getMReceivers().add(MReceiver);
		MReceiver.setMessage(this);

		return MReceiver;
	}

	public MReceiver removeMReceiver(MReceiver MReceiver) {
		getMReceivers().remove(MReceiver);
		MReceiver.setMessage(null);

		return MReceiver;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}
	
	@Override
	public int compareTo(Message o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId();
	    return result;
	 }
	
	@Override
	public boolean equals(Object o) {
        if(o == null)
        {
            return false;
        }
        if (o == this)
        {
           return true;
        }
        if (getClass() != o.getClass())
        {
            return false;
        }
        com.leosal.util.jpa.Entity e = (com.leosal.util.jpa.Entity) o;
        return (this.getId().equals(e.getId()));
	}

	

}