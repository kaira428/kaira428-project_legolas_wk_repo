package com.upskill.legolas.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table (name = "learning_tracks_users")
public class LearningTrackUser {

    public LearningTrackUser() {}

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "row_id")
    private Long row_id;

    // @Column(name = "fk_learning_track_id")
    // private Long fk_learning_track_id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_learning_track_id")
    private LearningTrack learningTrack;

    // @Column(name = "fk_user_id")
    // private Long fk_user_id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Column(name = "user_lt_start_date")
    private LocalDate user_lt_start_date;

    @Column(name = "user_lt_end_date")
    private LocalDate user_lt_end_date;

    @Column(name = "user_lt_status")
    @Size(max=20)
    private String user_lt_status;

    @Column(name = "mentor_id")
    private Long mentor_id;

    @Column(name = "cohort_id")
    @Size(max=30)
    private String cohort_id;

    // public Long getFk_learning_track_id() {
    //     return fk_learning_track_id;
    // }

    // public void setFk_learning_track_id(Long fk_learning_track_id) {
    //     this.fk_learning_track_id = fk_learning_track_id;
    // }

    // public Long getFk_user_id() {
    //     return fk_user_id;
    // }

    // public void setFk_user_id(Long fk_user_id) {
    //     this.fk_user_id = fk_user_id;
    // }

    public LocalDate getUser_lt_start_date() {
        return user_lt_start_date;
    }

    public void setUser_lt_start_date(LocalDate user_lt_start_date) {
        this.user_lt_start_date = user_lt_start_date;
    }

    public LocalDate getUser_lt_end_date() {
        return user_lt_end_date;
    }

    public void setUser_lt_end_date(LocalDate user_lt_end_date) {
        this.user_lt_end_date = user_lt_end_date;
    }

    public String getUser_lt_status() {
        return user_lt_status;
    }

    public void setUser_lt_status(String user_lt_status) {
        this.user_lt_status = user_lt_status;
    }

    public Long getMentor_id() {
        return mentor_id;
    }

    public void setMentor_id(Long mentor_id) {
        this.mentor_id = mentor_id;
    }

    public String getCohort_id() {
        return cohort_id;
    }

    public void setCohort_id(String cohort_id) {
        this.cohort_id = cohort_id;
    }

    public LearningTrack getLearningTrack() {
        return learningTrack;
    }

    public void setLearningTrack(LearningTrack learningTrack) {
        this.learningTrack = learningTrack;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
