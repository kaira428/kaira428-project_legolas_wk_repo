package com.upskill.legolas.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "topics_users")
public class TopicUser {

    public TopicUser() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Long row_id;

    // @Column(name = "fk_user_id")
    // private Long fk_user_id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_user_id")
    private User user;

    // @Column(name = "fk_topic_id")
    // private Long fk_topic_id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_topic_id")
    private Topic topic;

    @Column(name = "isCompleted")
    private boolean isCompleted;

    @Column(name = "isCertified")
    private boolean isCertified;

    // public Long getFk_user_id() {
    //     return fk_user_id;
    // }

    // public void setFk_user_id(Long fk_user_id) {
    //     this.fk_user_id = fk_user_id;
    // }

    // public Long getFk_topic_id() {
    //     return fk_topic_id;
    // }

    // public void setFk_topic_id(Long fk_topic_id) {
    //     this.fk_topic_id = fk_topic_id;
    // }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(boolean isCertified) {
        this.isCertified = isCertified;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
