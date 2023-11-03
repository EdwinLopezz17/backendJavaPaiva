package com.learningcenter.learning.domain.model.valueobjects;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.entities.LearningPathItem;
import com.learningcenter.learning.domain.model.entities.Tutorial;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class LearningPath {
    @OneToMany(mappedBy = "course")
    private List<LearningPathItem> learningPathItemList;

    public LearningPath(){
        this.learningPathItemList = new ArrayList<>();
    }

    /**
     * Add the item before the item with given id
     * @param course the course to add
     * @param tutorial the tutorial to add
     * @param nextItem the id of the item before which the new item should be added
     */
    public void addItem(Course course, Tutorial tutorial, LearningPathItem nextItem){
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorial, nextItem);
        learningPathItemList.add(learningPathItem);
    }
    public LearningPathItem getLastItemInLearningPathList(){
        return learningPathItemList.stream().filter(item-> item.getNextItem()==null)
                .findFirst().orElse(null);
    }

    /**
     * Adds the item at the end of the learning path
     * @param course the courses to add
     * @param tutorial the tutorial to add
     */
    public void addItem(Course course, Tutorial tutorial){
        LearningPathItem originalLastItem = getLastItemInLearningPathList();
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorial, null);
        learningPathItemList.add(learningPathItem);
        if(originalLastItem != null) originalLastItem.updatedNextItem(learningPathItem);

    }

    public Long getFirstTutorialIdInLearningPathList(){
        return learningPathItemList.get(0).getTutorial().getId();
    }
    public Tutorial getFirstTutorialInLearningPathList(){
        return learningPathItemList.get(0).getTutorial();
    }
    private  LearningPathItem getLearningPathItemWithTutorial(Long tutorialId){
        return learningPathItemList.stream().filter(learningPathItem -> learningPathItem.getTutorial().getId().equals(tutorialId))
                .findFirst().orElse(null);
    }
    public Tutorial getNextTutorialInLearningPathList(Long currentTutorialId){
        LearningPathItem item = getLearningPathItemWithTutorial(currentTutorialId);
        return item!=null ? item.getTutorial() : null;
    }
    private LearningPathItem getLearningPathItemWithId(Long itemId){
        return learningPathItemList.stream().filter(learningPathItem -> learningPathItem.getId().equals(itemId))
                .findFirst().orElse(null);
    }

    public boolean isLastTutorialInLearningPathList(Long currentTutorialId){
        return getNextTutorialInLearningPathList(currentTutorialId) == null;
    }
}
