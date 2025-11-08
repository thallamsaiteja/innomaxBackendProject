package com.innomax.InnomaxBackend.goals.service;

import com.innomax.InnomaxBackend.auth.entity.User;
import com.innomax.InnomaxBackend.auth.repository.UserRepository;
import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    // ➕ Add a new goal
    public Goal addGoal(Long userId, Goal goal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        goal.setUser(user);
        return goalRepository.save(goal);
    }

    // 📋 Get all goals of a specific user
    public List<Goal> getGoalsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return goalRepository.findByUser(user);
    }

    // 🗑️ Delete a goal
    public void deleteGoal(Long userId, Long goalId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = goalRepository.findByIdAndUser(goalId, user);
        if (goal == null)
            throw new RuntimeException("Goal not found for this user");

        goalRepository.delete(goal);
    }

    // ✏️ Update goal progress
    public Goal updateGoal(Long userId, Long goalId, Goal updatedGoal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Goal goal = goalRepository.findByIdAndUser(goalId, user);
        if (goal == null)
            throw new RuntimeException("Goal not found");

        goal.setGoalName(updatedGoal.getGoalName());
        goal.setTargetAmount(updatedGoal.getTargetAmount());
        goal.setAchievedAmount(updatedGoal.getAchievedAmount());
        goal.setTimeline(updatedGoal.getTimeline());

        return goalRepository.save(goal);
    }
}
