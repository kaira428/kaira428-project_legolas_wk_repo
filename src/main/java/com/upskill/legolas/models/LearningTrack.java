package com.upskill.legolas.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table (name = "learning_tracks")
public class LearningTrack {

    public LearningTrack() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "learning_track_id")
    private Long learning_track_id;

    @Column (name = "learning_track_name")
    @Size(max=50)
    private String learning_track_name;

    @OneToMany(mappedBy = "learningTrack", cascade=CascadeType.ALL)
    private List<LearningTrackUser> learningTrackUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "learningTrack", cascade=CascadeType.ALL)
    private List<Module> modules;

    public Long getLearning_track_id() {
        return learning_track_id;
    }

    public void setLearning_track_id(Long learning_track_id) {
        this.learning_track_id = learning_track_id;
    }

    public String getLearning_track_name() {
        return learning_track_name;
    }

    public void setLearning_track_name(String learning_track_name) {
        this.learning_track_name = learning_track_name;
    }

    public List<LearningTrackUser> getLearningTrackUser() {
        return learningTrackUser;
    }

    public void setLearningTrackUser(List<LearningTrackUser> learningTrackUser) {
        this.learningTrackUser = learningTrackUser;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "LearningTrack [learning_track_id=" + learning_track_id + ", learning_track_name=" + learning_track_name
                + "]";
    }
    
    
}
