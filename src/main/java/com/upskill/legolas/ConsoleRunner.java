package com.upskill.legolas;

import java.util.List;

import com.upskill.legolas.models.User;
import com.upskill.legolas.models.LearningTrack;
import com.upskill.legolas.models.Module;
import com.upskill.legolas.models.Topic;
import com.upskill.legolas.repositories.LearningTrackRepository;
import com.upskill.legolas.repositories.ModuleRepository;
import com.upskill.legolas.repositories.TopicRepository;
import com.upskill.legolas.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// @Component
public class ConsoleRunner implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private LearningTrackRepository learningTrackRepository;
    
    @Override
    public void run(String... args) throws Exception {
        System.out.println("From ConsoleRunner");

        List<User> testUsers = userRepository.findAll();

        for (User user : testUsers) {
            System.out.printf("User Fullname : %s %s\n",user.getFirst_name(), user.getLast_name());
        }
        
        List<Module> modules = moduleRepository.findAll();

        for (Module module : modules) {

            System.out.printf("Module ID: %d, Name: %s\n", module.getModule_id(), module.getModule_name());

            Long moduleId = module.getModule_id();
            
            List<Topic> topics = topicRepository.getTopicsUnderModule(moduleId);
            
            System.out.println("Topics: " + topics.size());
            for (Topic topic : topics) {
                System.out.printf("%s\n", topic);
            }
            
        }

        List<LearningTrack> learningTracks = learningTrackRepository.findAll();

        for (LearningTrack learningTrack : learningTracks) {
            System.out.println(learningTrack);

            List<Module> theModules = learningTrack.getModules();

            for (Module module : theModules) {

                List<Topic> topics = module.getTopics();

                System.out.println(module);

                for (Topic theTopic : topics) {
                    System.out.println(theTopic);
                }
            }

        }
    }    
}
