package com.innomax.InnomaxBackend.goals.controller;

import com.innomax.InnomaxBackend.goals.entity.Goal;
import com.innomax.InnomaxBackend.goals.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/client/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    // ➕ Add new goal
    @PostMapping("/{userId}")
    public Goal addGoal(@PathVariable Long userId, @RequestBody Goal goal) {
        return goalService.addGoal(userId, goal);
    }

    // 📋 Get all goals for a user
    @GetMapping("/{userId}")
    public List<Goal> getGoals(@PathVariable Long userId) {
        return goalService.getGoalsByUser(userId);
    }

    // ✏️ Update a goal
    @PutMapping("/{userId}/{goalId}")
    public Goal updateGoal(@PathVariable Long userId, @PathVariable Long goalId, @RequestBody Goal updatedGoal) {
        return goalService.updateGoal(userId, goalId, updatedGoal);
    }

    // 🗑️ Delete a goal
    @DeleteMapping("/{userId}/{goalId}")
    public String deleteGoal(@PathVariable Long userId, @PathVariable Long goalId) {
        goalService.deleteGoal(userId, goalId);
        return "Goal deleted successfully!";
    }
}
