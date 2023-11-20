package com.learningcenter.learning.domain.model.valueobjects;

import com.learningcenter.learning.domain.model.aggregates.Course;
import com.learningcenter.learning.domain.model.entities.LearningPathItem;
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
     * @param tutorialId the tutorial to add
     * @param nextItem the id of the item before which the new item should be added
     */
    public void addItem(Course course, Long tutorialId, LearningPathItem nextItem){
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, nextItem);
        learningPathItemList.add(learningPathItem);
    }
    public LearningPathItem getLastItemInLearningPathList(){
        return learningPathItemList.stream().filter(item-> item.getNextItem()==null)
                .findFirst().orElse(null);
    }

    /**
     * Adds the item at the end of the learning path
     * @param course the courses to add
     * @param tutorialId the tutorial to add
     */
    public void addItem(Course course, Long tutorialId){
        LearningPathItem originalLastItem = getLastItemInLearningPathList();
        LearningPathItem learningPathItem = new LearningPathItem(course, tutorialId, null);
        learningPathItemList.add(learningPathItem);
        if(originalLastItem != null) originalLastItem.updatedNextItem(learningPathItem);

    }

    public Long getFirstTutorialIdInLearningPathList(){
        return learningPathItemList.get(0).getTutorialId();
    }
    public Long getFirstTutorialInLearningPathList(){
        return learningPathItemList.get(0).getTutorialId();
    }
    private  LearningPathItem getLearningPathItemWithTutorial(Long tutorialId){
        return learningPathItemList.stream().filter(learningPathItem -> learningPathItem.getTutorialId().equals(tutorialId))
                .findFirst().orElse(null);
    }
    public Long getNextTutorialInLearningPathList(Long currentTutorialId){
        LearningPathItem item = getLearningPathItemWithTutorial(currentTutorialId);
        return item!=null ? item.getTutorialId() : null;
    }
    private LearningPathItem getLearningPathItemWithId(Long itemId){
        return learningPathItemList.stream().filter(learningPathItem -> learningPathItem.getId().equals(itemId))
                .findFirst().orElse(null);
    }

    public boolean isLastTutorialInLearningPathList(Long currentTutorialId){
        return getNextTutorialInLearningPathList(currentTutorialId) == null;
    }

    public boolean isEmpty(){
        return learningPathItemList.isEmpty();
    }
}
