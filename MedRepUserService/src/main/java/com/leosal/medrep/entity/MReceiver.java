package com.leosal.medrep.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.leosal.dbutils.DBEntity;


/**
 * The persistent class for the m_receivers database table.
 * 
 */
@Entity
@Table(name="m_receivers")
@NamedQuery(name="MReceiver.findAll", query="SELECT m FROM MReceiver m")
public class MReceiver implements DBEntity, Serializable,
		Comparable<MReceiver>{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="mess_read")
	private byte messRead;

	//bi-directional many-to-one association to Message
	@ManyToOne
	@JoinColumn(name="mess_id")
	private Message message;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="pers_id")
	private Person receiver;

	public MReceiver() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getMessRead() {
		return this.messRead;
	}

	public void setMessRead(byte messRead) {
		this.messRead = messRead;
	}

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Person getReceiver() {
		return receiver;
	}

	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}
	
	@Override
	public int compareTo(MReceiver o) {
		
		return this.getId().compareTo(o.getId());
	}
	
	@Override
	 public int hashCode()
	 {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + getId().intValue();
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
        DBEntity e = (DBEntity) o;
        return (this.getId().equals(e.getId()));
	}

	

}