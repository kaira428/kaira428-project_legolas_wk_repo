package com.upskill.legolas.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table (name = "modules")
public class Module {

    public Module() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Long module_id;

    @Column(name = "module_name")
    @Size(max=50)
    private String module_name;

    // @Column(name = "fk_learning_track_id")
    // private Long fk_learning_track_id;

    // @OneToMany(fetch = FetchType.EAGER)
    // @JoinColumn(name = "fk_module_id")
    // private List<Topic> topics;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<ModuleProgress> moduleProgresses;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "fk_learning_track_id")
    private LearningTrack learningTrack;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "module", cascade = CascadeType.ALL)
    private List<Topic> topics;

    public Long getModule_id() {
        return module_id;
    }

    public void setModule_id(Long module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    // public Long getFk_learning_track_id() {
    //     return fk_learning_track_id;
    // }

    // public void setFk_learning_track_id(Long fk_learning_track_id) {
    //     this.fk_learning_track_id = fk_learning_track_id;
    // }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<ModuleProgress> getModuleProgresses() {
        return moduleProgresses;
    }

    public void setModuleProgresses(List<ModuleProgress> moduleProgresses) {
        this.moduleProgresses = moduleProgresses;
    }

    public LearningTrack getLearningTrack() {
        return learningTrack;
    }

    public void setLearningTrack(LearningTrack learningTrack) {
        this.learningTrack = learningTrack;
    }

    @Override
    public String toString() {
        return "Module [fk_learning_track_id=" + ", module_id=" + module_id + ", module_name="
                + module_name + "]";
    }
       
}
