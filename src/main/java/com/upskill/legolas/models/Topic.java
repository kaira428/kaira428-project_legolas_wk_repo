package com.upskill.legolas.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table (name = "topics")
public class Topic {

    public Topic() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Long topic_id;

    @Column(name = "topic_name")
    @Size(max=50)
    private String topic_name;

    // @Column(name = "fk_module_id")
    // private Long fk_module_id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_module_id")
    private Module module;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<TopicUser> topicUsers;

    public Long getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Long topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    // public Long getFk_module_id() {
    //     return fk_module_id;
    // }

    // public void setFk_module_id(Long fk_module_id) {
    //     this.fk_module_id = fk_module_id;
    // }

    public List<TopicUser> getTopicUsers() {
        return topicUsers;
    }

    public void setTopicUsers(List<TopicUser> topicUsers) {
        this.topicUsers = topicUsers;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public String toString() {
        return "Topic [topic_id=" + topic_id + ", topic_name=" + topic_name + "]";
    }
    
    
}
