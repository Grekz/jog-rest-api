package mx.grekz.jog.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jogs")
public class Jog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int jogId;

	@Column(name="date")
	private Date date;
	
	@Column(name="distance")
	private int distance;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="users_id")
	private int userId;
	
//	@ManyToOne
//	@JoinColumn(name="users_id", insertable=false, updatable=false,foreignKey=@ForeignKey(name="users_id_fk"))
//	private User user;

	@Column(name="create_date")
	private Timestamp createDate;
	
	@Column(name="update_date")
	private Timestamp updateDate;

	public int getJogId() {
		return jogId;
	}

	public void setJogId(int jogId) {
		this.jogId = jogId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	 @Override
	public String toString() {
		return "Jog={"+
				"/n jogId=" + this.jogId + 
				"/n distance=" + this.distance + 
				"/n duration="+ this.duration + 
				"/n userId=" + this.userId + 
				"/n date=" + this.date +  "}";
	}
}
